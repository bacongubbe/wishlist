package dev.bacongubbe.wishlist.service

import dev.bacongubbe.wishlist.User_entity
import dev.bacongubbe.wishlist.db.runConcurrently
import dev.bacongubbe.wishlist.repo.CollectionRepo
import dev.bacongubbe.wishlist.repo.UserRepo

class UserService(private val repo : UserRepo, private val collectionRepo : CollectionRepo) {

    suspend fun getUsers(): List<User_entity> = repo.getUsers()

    suspend fun addUser(name : String, email: String) = repo.addUser(name, email)

    suspend fun deleteUser(id: String) {
        runConcurrently (
            { collectionRepo.deleteCollectionsWhereUserIsSoleMember(id) },
            { repo.deleteUser(id) }
        )
    }

    suspend fun getUserById(id: String): User_entity = repo.getUserById(id)

}