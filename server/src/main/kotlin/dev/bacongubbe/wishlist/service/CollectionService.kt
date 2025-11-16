package dev.bacongubbe.wishlist.service

import dev.bacongubbe.wishlist.repo.CollectionRepo

class CollectionService(private val repo : CollectionRepo) {

    fun createNewCollectionForUser(userId: String, collectionName: String) =
        repo.createNewCollectionForUser(userId, collectionName)

    fun getCollectionsForUser(userId: String) = repo.getCollectionsForUser(userId)

    fun getCollectionById(collectionId: String) =
        repo.getCollectionById(collectionId)
}