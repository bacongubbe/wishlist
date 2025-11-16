package dev.bacongubbe.wishlist.service

import dev.bacongubbe.wishlist.User
import dev.bacongubbe.wishlist.WishlistDatabase
import dev.bacongubbe.wishlist.repo.UserRepo

class UserService(private val repo : UserRepo) {

    fun getUsers(): List<User> = repo.getUsers()

    fun addUser(name : String, email: String) = repo.addUser(name, email)

    fun deleteUser(id: String) = repo.deleteUser(id)

//    fun createNewCollectionForUser(userId: String, collectionName: String) {
//        db.transaction {
//            val collectionId = db.collectionQueries.insertCollection(collectionName).executeAsOne()
//            db.collectionQueries.insertCollectionUserRelation(collectionId, userId)
//        }
//    }
//
//    fun getCollectionsForUser(userId: String) = db.collectionQueries.selectCollectionsForUser(userId).executeAsList()
}