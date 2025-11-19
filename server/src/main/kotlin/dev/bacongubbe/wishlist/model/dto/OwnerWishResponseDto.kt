package dev.bacongubbe.wishlist.model.dto

import dev.bacongubbe.wishlist.User_entity
import dev.bacongubbe.wishlist.model.domain.Wish
import dev.bacongubbe.wishlist.model.dto.ViewerWishResponseDto

data class OwnerWishResponseDto(
    override val id: String,
    override val name: String,
    override val description: String? = null,
    override val links: String? = null
) : WishResponseDto {
    constructor(wishList: Wish) : this(
        id = wishList.id,
        name = wishList.name,
        description = wishList.description,
        links = wishList.links
    )
}