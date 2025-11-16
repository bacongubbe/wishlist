package dev.bacongubbe.wishlist.repo

import dev.bacongubbe.wishlist.WishlistDatabase
import dev.bacongubbe.wishlist.Wishlist_entity

class WishlistRepo(db : WishlistDatabase) {

    val wishlistQueries = db.wishlistQueries

    fun createNewWishlist(userId : String, collectionId: String, wishlistName : String) : Wishlist_entity {
        return wishlistQueries.transactionWithResult {
            val wishlistId = wishlistQueries.insertWishlist(userId, wishlistName, collectionId).executeAsOne()
            wishlistQueries.getListById(wishlistId).executeAsOne()
        }
    }

    fun addWish(wishlistId: String, name : String, description : String?, links : String?) {
        wishlistQueries.insertWish(wishlistId, name, description, links)
    }

    fun getWishlistsForCollection(collectionId: String) =
        wishlistQueries.getListsForCollection(collectionId).executeAsList()

    fun getWishlistById (wishlistId: String) =
        wishlistQueries.getWishlistandWishesByWishlistId(wishlistId).executeAsOneOrNull()

}