package dev.bacongubbe.wishlist.model.dto

import dev.bacongubbe.wishlist.User_entity
import dev.bacongubbe.wishlist.model.domain.Wish
import dev.bacongubbe.wishlist.model.domain.WishList

data class WishlistResponseDto(
    val id: String,
    val owner: User_entity,
    val name: String,
    val wishes: List<WishResponseDto>
) {
    constructor(wishList: WishList, userEntity: User_entity, wishes : List<WishResponseDto>) : this(
        id = wishList.id,
        owner = userEntity,
        name = wishList.name,
        wishes = wishes
    )
}
