package dev.bacongubbe.wishlist.service

import dev.bacongubbe.wishlist.repo.CollectionRepo

class CollectionService(private val repo : CollectionRepo) {

    suspend fun createNewCollectionForUser(userId: String, collectionName: String) =
        repo.createNewCollectionForUser(userId, collectionName)

    suspend fun getCollectionsForUser(userId: String) = repo.getCollectionsForUser(userId)

    suspend fun getCollectionById(collectionId: String) =
        repo.getCollectionById(collectionId)
}