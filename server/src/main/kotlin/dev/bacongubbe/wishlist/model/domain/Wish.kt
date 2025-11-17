package dev.bacongubbe.wishlist.model.domain

data class Wish(val id : String, val name : String, val description : String?, val links : String?, val purchasedBy : String?) {
}

fun dev.bacongubbe.wishlist.Wish_entity.toDomain() : Wish {
    return Wish(
        id = this.id,
        name = this.name,
        description = this.description,
        links = this.links,
        purchasedBy = this.purchased_by
    )
}