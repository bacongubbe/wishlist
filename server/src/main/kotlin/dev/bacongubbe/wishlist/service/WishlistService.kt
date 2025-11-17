package dev.bacongubbe.wishlist.service

import dev.bacongubbe.wishlist.Wishlist_entity
import dev.bacongubbe.wishlist.model.dto.AddWishRequest
import dev.bacongubbe.wishlist.model.dto.AddWishlistRequest
import dev.bacongubbe.wishlist.model.dto.WishlistResponseDto
import dev.bacongubbe.wishlist.repo.WishlistRepo

class WishlistService(private val repo: WishlistRepo, private val userService: UserService) {

    fun addNewWishlist(userId: String, request: AddWishlistRequest) : Wishlist_entity {
        return repo.createNewWishlist(userId, request.collectionId, request.name)
    }

    fun addWishToWishlist(wishlistId : String, request : AddWishRequest) =
        repo.addWish(wishlistId, request)

    fun getWishlistById(wishlistId: String) : WishlistResponseDto {
        val wishlist = repo.getWishlistById(wishlistId)
        return WishlistResponseDto(wishlist, userService.getUserById(wishlist.ownerId))
    }

}

