package dev.bacongubbe.wishlist.model.dto

data class AddWishRequest(val wishlistId: String, val name : String, val description : String?, val links : String?) {
}