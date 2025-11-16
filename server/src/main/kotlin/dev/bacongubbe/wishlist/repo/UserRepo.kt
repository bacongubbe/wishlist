package dev.bacongubbe.wishlist.repo

import dev.bacongubbe.wishlist.User
import dev.bacongubbe.wishlist.WishlistDatabase

class UserRepo(db : WishlistDatabase) {
    private val userQueries = db.userQueries

    fun getUsers(): List<User> = userQueries.getAllUsers().executeAsList()

    fun addUser(name : String, email: String) {
        userQueries.insertUser(name, email)
    }

    fun deleteUser(id: String) {
        userQueries.deeteUserById(id)
    }
}