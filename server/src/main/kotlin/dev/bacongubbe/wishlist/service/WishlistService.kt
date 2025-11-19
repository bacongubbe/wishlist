package dev.bacongubbe.wishlist.service

import dev.bacongubbe.wishlist.Wishlist_entity
import dev.bacongubbe.wishlist.model.dto.AddWishlistRequest
import dev.bacongubbe.wishlist.model.dto.OwnerWishResponseDto
import dev.bacongubbe.wishlist.model.dto.ViewerWishResponseDto
import dev.bacongubbe.wishlist.model.dto.WishlistResponseDto
import dev.bacongubbe.wishlist.repo.WishlistRepo

class WishlistService(private val repo: WishlistRepo, private val userService: UserService) {

    suspend fun addNewWishlist(userId: String, request: AddWishlistRequest): Wishlist_entity {
        return repo.createNewWishlist(userId, request.collectionId, request.name)
    }

    suspend fun getWishlistById(userId: String, wishlistId: String): WishlistResponseDto {
        val wishlist = repo.getWishlistById(wishlistId)
        val wishes =
            if (wishlist.ownerId == userId) {
                wishlist.wishes.map { OwnerWishResponseDto(it) }
            }
            else  {
                val users = wishlist.wishes.mapNotNull { it.purchasedBy }
                    .distinct().takeIf { it.isNotEmpty() }?.let { userService.getUsersByIds(it) }
                wishlist.wishes.map {wish -> ViewerWishResponseDto(wish, users?.find { it.id == wish.purchasedBy }) }
            }
        return WishlistResponseDto(wishlist, userService.getUserById(wishlist.ownerId), wishes)
    }


}

