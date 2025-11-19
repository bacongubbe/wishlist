package dev.bacongubbe.wishlist.service

import dev.bacongubbe.wishlist.model.dto.AddWishRequest
import dev.bacongubbe.wishlist.repo.WishRepo

class WishService(private val repo : WishRepo, private val userService: UserService) {

    suspend fun addWishToWishlist(userId : String, request : AddWishRequest) =
        repo.addWish(userId, request)

    suspend fun deleteWish(userId : String, wishId : String) =
        repo.deleteWish(userId, wishId)

}