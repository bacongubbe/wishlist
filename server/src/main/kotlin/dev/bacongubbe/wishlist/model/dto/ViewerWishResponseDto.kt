package dev.bacongubbe.wishlist.model.dto

import dev.bacongubbe.wishlist.User_entity
import dev.bacongubbe.wishlist.model.domain.Wish
import dev.bacongubbe.wishlist.model.domain.WishList

data class ViewerWishResponseDto(
    override val id: String,
    override val name: String,
    override val description: String? = null,
    override val links: String? = null,
    val purchasedBy: User_entity?
) : WishResponseDto {
    constructor(wishList: Wish, purchasedBy: User_entity?) : this(
        id = wishList.id,
        name = wishList.name,
        description = wishList.description,
        links = wishList.links,
        purchasedBy = purchasedBy
    )
}