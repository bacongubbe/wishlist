package dev.bacongubbe.wishlist.service

import dev.bacongubbe.wishlist.model.dto.CollectionResponseDto
import dev.bacongubbe.wishlist.repo.CollectionRepo

class CollectionService(private val repo: CollectionRepo, private val userService: UserService) {

    suspend fun createNewCollectionForUser(userId: String, collectionName: String) =
        repo.createNewCollectionForUser(userId, collectionName)

    suspend fun getCollectionsForUser(userId: String) = repo.getCollectionsForUser(userId)

    suspend fun getCollectionById(collectionId: String) =
        repo.getCollectionById(collectionId).let { collection ->
            val members = userService.getUsersByIds(collection.membersIds)
            CollectionResponseDto(collection, members)
        }

    suspend fun addUserToCollection(collectionId: String, userId: String) {
        val user = userService.getUserById(userId)
        repo.addUserToCollection(collectionId, user.id)
    }
}