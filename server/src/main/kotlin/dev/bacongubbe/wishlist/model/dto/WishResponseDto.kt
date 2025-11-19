package dev.bacongubbe.wishlist.model.dto

interface WishResponseDto {
    val id: String
    val name: String
    val description: String?
    val links: String?
}