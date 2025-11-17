package dev.bacongubbe.wishlist.repo

import dev.bacongubbe.wishlist.WishlistDatabase
import dev.bacongubbe.wishlist.db.dbQuery
import dev.bacongubbe.wishlist.model.domain.Collection

class CollectionRepo(private val db: WishlistDatabase) {

    private val collections = db.collectionQueries

    suspend fun createNewCollectionForUser(userId: String, collectionName: String) {
        dbQuery {
            collections.transaction {
                val collectionId = collections.insertCollection(collectionName).executeAsOne()
                collections.insertCollectionUserRelation(collectionId, userId)
            }
        }
    }

    suspend fun getCollectionsForUser(userId: String) = dbQuery { collections.selectCollectionsForUser(userId).executeAsList() }

    suspend fun getCollectionById(collectionId: String) : Collection {
        return dbQuery {
                db.transactionWithResult {
                val collection = collections.getCollectionById(collectionId).executeAsOne()
                val wishlists = db.wishlistQueries.getListsForCollection(collectionId).executeAsList()
                Collection(collection, wishlists)
            }
        }
    }
}