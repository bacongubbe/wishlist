package dev.bacongubbe.wishlist.repo

import dev.bacongubbe.wishlist.WishlistDatabase

class CollectionRepo(private val db: WishlistDatabase) {

    private val collections = db.collectionQueries

    fun createNewCollectionForUser(userId: String, collectionName: String) {
        collections.transaction {
            val collectionId = collections.insertCollection(collectionName).executeAsOne()
            collections.insertCollectionUserRelation(collectionId, userId)
        }
    }

    fun getCollectionsForUser(userId: String) = collections.selectCollectionsForUser(userId).executeAsList()
}