package dev.bacongubbe.wishlist.model.domain

import dev.bacongubbe.wishlist.Collection_entity
import dev.bacongubbe.wishlist.Wishlist_entity
import java.util.stream.Collectors

data class Collection(val id: String, val name: String, val wishlistSummaries: List<WishlistSummary>) {
     constructor(entity : Collection_entity, wishlists : List<Wishlist_entity>) : this(
        id = entity.id,
        name = entity.name,
        wishlistSummaries = wishlists.stream().map { it.toDomain() }.collect(Collectors.toList())
     )
}
