package dev.bacongubbe.wishlist

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@ExperimentalUuidApi
class UserService(private val db: WishlistDatabase) {

    fun getUsers(): List<User> = db.wishlistQueries.getAllUsers().executeAsList()

    fun addUser(name : String, email: String) {
        println("Adding user: $name with email: $email")
        db.wishlistQueries.insertUser(name, email)
    }
}