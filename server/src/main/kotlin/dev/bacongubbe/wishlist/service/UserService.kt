package dev.bacongubbe.wishlist.service

import dev.bacongubbe.wishlist.User_entity
import dev.bacongubbe.wishlist.repo.UserRepo

class UserService(private val repo : UserRepo) {

    fun getUsers(): List<User_entity> = repo.getUsers()

    fun addUser(name : String, email: String) = repo.addUser(name, email)

    fun deleteUser(id: String) = repo.deleteUser(id)

}