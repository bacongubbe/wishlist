package dev.bacongubbe.wishlist.repo

import dev.bacongubbe.wishlist.Wish_entity
import dev.bacongubbe.wishlist.WishlistDatabase
import dev.bacongubbe.wishlist.db.dbQuery
import dev.bacongubbe.wishlist.model.dto.AddWishRequest

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
            wishQueries.markWishAsPurchased(userId, wishId)
        }

    suspend fun deleteWish(userId: String, wishId: String) =
        dbQuery {
            wishQueries.deleteWishByIdAndOwner(wishId, userId)
        }
}