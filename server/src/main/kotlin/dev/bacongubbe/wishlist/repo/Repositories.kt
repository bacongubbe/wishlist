package dev.bacongubbe.wishlist.repo

import dev.bacongubbe.wishlist.WishlistDatabase

class Repositories(database: WishlistDatabase) {
    val userRepository = UserRepo(database)
    val collectionRepository = CollectionRepo(database)
    val wishlistRepository = WishlistRepo(database)
    val wishRepository = WishRepo(database)
}