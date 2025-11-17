package dev.bacongubbe.wishlist.model.domain

import dev.bacongubbe.wishlist.Collection_entity
import dev.bacongubbe.wishlist.Wishlist_entity
import java.util.stream.Collectors

data class Collection(
    val id: String,
    val name: String,
    val membersIds: List<String>,
    val wishlistSummaries: List<WishlistSummary>
) {
    constructor(entity: Collection_entity, membersIds: List<String>, wishlists: List<Wishlist_entity>) : this(
        id = entity.id,
        membersIds = membersIds,
        name = entity.name,
        wishlistSummaries = wishlists.stream().map { it.toDomain() }.collect(Collectors.toList())
    )
}
