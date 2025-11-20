package dev.bacongubbe.wishlist.repo

import dev.bacongubbe.wishlist.Wish_entity
import dev.bacongubbe.wishlist.WishlistDatabase
import dev.bacongubbe.wishlist.db.dbQuery
import dev.bacongubbe.wishlist.exception.AlreadyPurchasedException
import dev.bacongubbe.wishlist.model.dto.AddWishRequest
import kotlin.IllegalArgumentException

class WishRepo(db: WishlistDatabase) {

    private val wishQueries = db.wishQueries
    private val wishlistQueries = db.wishlistQueries

    suspend fun addWish(userId: String, request: AddWishRequest): Wish_entity =
        dbQuery {
            wishlistQueries.transactionWithResult {
                val wishlist = wishlistQueries.getListByIdAndOwner(request.wishlistId, userId).executeAsOneOrNull()
                    ?.let { wishlist ->
                        val result =
                            wishQueries.insertWish(wishlist.id, request.name, request.description, request.links)
                                .executeAsOne()
                        wishQueries.getWishById(result).executeAsOne()
                    }
                wishlist
                    ?: throw NoSuchElementException("wishlist ${request.wishlistId} not found, or you are not it's owner")
            }
        }

    suspend fun markWishAsPurchased(userId: String, wishId: String) =
        dbQuery {
            val wish = wishlistQueries.getListByAndPurchasedByWishId(wishId).executeAsOne()
            if (wish.owner_id == userId) {
                throw IllegalArgumentException("You cannot mark your own wishes as purchased")
            }
            if (wish.purchased_by != null) {
                throw AlreadyPurchasedException()
            }
            wishQueries.markWishAsPurchased(userId, wishId) // TODO refactor, need to conflict check
        }

    suspend fun deleteWish(userId: String, wishId: String) =
        dbQuery {
            wishQueries.deleteWishByIdAndOwner(wishId, userId)
        }
}