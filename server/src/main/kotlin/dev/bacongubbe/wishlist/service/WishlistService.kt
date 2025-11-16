package dev.bacongubbe.wishlist.service

import dev.bacongubbe.wishlist.Wishlist_entity
import dev.bacongubbe.wishlist.model.AddWishlistRequest
import dev.bacongubbe.wishlist.repo.WishlistRepo

class WishlistService(private val repo: WishlistRepo) {

    fun addNewWishlist(userId: String, request: AddWishlistRequest) : Wishlist_entity {
        return repo.createNewWishlist(userId, request.collectionId, request.name)
    }

}