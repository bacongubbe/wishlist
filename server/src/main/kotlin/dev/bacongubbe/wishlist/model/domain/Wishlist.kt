package dev.bacongubbe.wishlist.model.domain

import dev.bacongubbe.wishlist.Wishlist_entity

data class Wishlist(val id : String, val ownerId : String, val name : String){
}

fun Wishlist_entity.toDomain() : Wishlist {
    return Wishlist(
        id = this.id,
        ownerId = this.owner_id,
        name = this.name,
    )
}