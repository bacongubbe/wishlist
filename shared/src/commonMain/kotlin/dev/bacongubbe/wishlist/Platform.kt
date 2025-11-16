package dev.bacongubbe.wishlist

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform