package dev.bacongubbe.wishlist.service

import dev.bacongubbe.wishlist.repo.Repositories

class Services(repos: Repositories) {
    val userService = UserService(repos.userRepository, repos.collectionRepository)
    val collectionService = CollectionService(repos.collectionRepository, userService)
    val wishlistService = WishlistService(repos.wishlistRepository, userService)
    val wishService = WishService(repos.wishRepository, userService)
}