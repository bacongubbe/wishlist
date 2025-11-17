package dev.bacongubbe.wishlist.model.dto

import dev.bacongubbe.wishlist.User_entity
import dev.bacongubbe.wishlist.model.domain.Collection
import dev.bacongubbe.wishlist.model.domain.WishlistSummary

data class CollectionResponseDto(
    val id: String,
    val name: String,
    val members: List<User_entity>,
    val wishlistSummaries: List<WishlistSummary>
) {
    constructor(collection: Collection, members: List<User_entity>) : this(
        id = collection.id,
        name = collection.name,
        members = members,
        wishlistSummaries = collection.wishlistSummaries
    )
}