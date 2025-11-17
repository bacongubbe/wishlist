package dev.bacongubbe.wishlist.repo

import dev.bacongubbe.wishlist.Wish_entity
import dev.bacongubbe.wishlist.WishlistDatabase
import dev.bacongubbe.wishlist.Wishlist_entity
import dev.bacongubbe.wishlist.model.domain.WishList
import dev.bacongubbe.wishlist.model.domain.toDomain
import dev.bacongubbe.wishlist.model.dto.AddWishRequest

class WishlistRepo(db : WishlistDatabase) {

    val wishlistQueries = db.wishlistQueries

    fun createNewWishlist(userId : String, collectionId: String, wishlistName : String) : Wishlist_entity {
        return wishlistQueries.transactionWithResult {
            val wishlistId = wishlistQueries.insertWishlist(userId, wishlistName, collectionId).executeAsOne()
            wishlistQueries.getListById(wishlistId).executeAsOne()
        }
    }

    fun addWish(wishlistId : String, request: AddWishRequest) : Wish_entity {
       return wishlistQueries.transactionWithResult {
            val result = wishlistQueries.insertWish(wishlistId, request.name, request.description, request.links).executeAsOne()
            wishlistQueries.getWishById(result).executeAsOne()
        }
    }



    fun getWishlistById (wishlistId: String) : WishList {
        return wishlistQueries.transactionWithResult {
            val wishlist = wishlistQueries.getListById(wishlistId).executeAsOne()
            val wishes = wishlistQueries.getWishesForWishlist(wishlistId).executeAsList()
            WishList(wishlistId, wishlist.owner_id, wishlist.name, wishes.map { it.toDomain() })
        }
    }

}