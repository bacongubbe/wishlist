package dev.bacongubbe.wishlist.service

import dev.bacongubbe.wishlist.repo.Repositories

class Services(repos : Repositories) {
    val userService = UserService(repos.userRepository)
    val collectionService = CollectionService(repos.collectionRepository)
}