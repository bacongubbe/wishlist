package dev.bacongubbe.wishlist.repo

import dev.bacongubbe.wishlist.WishlistDatabase
import dev.bacongubbe.wishlist.Wishlist_entity
import dev.bacongubbe.wishlist.db.dbQuery
import dev.bacongubbe.wishlist.model.domain.WishList
import dev.bacongubbe.wishlist.model.domain.toDomain

class WishlistRepo(db : WishlistDatabase) {

    private val wishlistQueries = db.wishlistQueries
    private val wishQueries = db.wishQueries

    suspend fun createNewWishlist(userId : String, collectionId: String, wishlistName : String) : Wishlist_entity =
        dbQuery {
            wishlistQueries.transactionWithResult {
                val wishlistId = wishlistQueries.insertWishlist(userId, wishlistName, collectionId).executeAsOne()
                wishlistQueries.getListById(wishlistId).executeAsOne()
            }
        }

    suspend fun getWishlistById (wishlistId: String) : WishList =
        dbQuery {
         wishlistQueries.transactionWithResult {
            val wishlist = wishlistQueries.getListById(wishlistId).executeAsOne()
            val wishes = wishQueries.getWishesForWishlist(wishlistId).executeAsList()
            WishList(wishlistId, wishlist.owner_id, wishlist.name, wishes.map { it.toDomain() })
        }
    }

}