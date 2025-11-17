package dev.bacongubbe.wishlist.repo

import dev.bacongubbe.wishlist.WishlistDatabase
import dev.bacongubbe.wishlist.model.domain.Collection

class CollectionRepo(private val db: WishlistDatabase) {

    private val collections = db.collectionQueries

    fun createNewCollectionForUser(userId: String, collectionName: String) {
        collections.transaction {
            val collectionId = collections.insertCollection(collectionName).executeAsOne()
            collections.insertCollectionUserRelation(collectionId, userId)
        }
    }

    fun getCollectionsForUser(userId: String) = collections.selectCollectionsForUser(userId).executeAsList()

    fun getCollectionById(collectionId: String) : Collection {
        return db.transactionWithResult {
            val collection = collections.getCollectionById(collectionId).executeAsOne()
            val wishlists = db.wishlistQueries.getListsForCollection(collectionId).executeAsList()
            Collection(collection, wishlists)
        }
    }
}