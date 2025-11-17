package dev.bacongubbe.wishlist.model.domain

data class WishList(val id : String, val ownerId : String, val name : String, val wishes : List<Wish>) {
}
