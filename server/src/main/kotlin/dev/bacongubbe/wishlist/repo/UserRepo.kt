package dev.bacongubbe.wishlist.repo

import dev.bacongubbe.wishlist.User_entity
import dev.bacongubbe.wishlist.WishlistDatabase
import dev.bacongubbe.wishlist.db.dbQuery
import dev.bacongubbe.wishlist.exception.UserAlreadyExistException

class UserRepo(db: WishlistDatabase) {
    private val userQueries = db.userQueries

    suspend fun getUsers(): List<User_entity> = dbQuery { userQueries.getAllUsers().executeAsList() }

    suspend fun addUser(name: String, email: String) =
        dbQuery {
            userQueries.findUserByEmail(email).executeAsOneOrNull()
                ?.let { throw UserAlreadyExistException("User with email $email already exists") }
            userQueries.insertUser(name, email)
        }


    suspend fun deleteUser(id: String) =
        dbQuery { userQueries.deleteUserById(id) }


    suspend fun getUserById(id: String): User_entity = dbQuery { userQueries.findUserById(id).executeAsOne() }

    suspend fun getUsersByIds(ids: List<String>): List<User_entity> =
        dbQuery {
            userQueries.getUsersByIds(ids).executeAsList()
        }
}