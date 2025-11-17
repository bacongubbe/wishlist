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
                val members = collections.getAllMembersForCollection(collection.id).executeAsList()
                val wishlists = db.wishlistQueries.getListsForCollection(collectionId).executeAsList()
                Collection(collection, members, wishlists)
            }
        }
    }

    suspend fun addUserToCollection(collectionId: String, userId: String) {
        dbQuery {
            collections.insertCollectionUserRelation(collectionId, userId)
        }
    }

    suspend fun deleteCollectionsWhereUserIsSoleMember(userId: String) {
        dbQuery {
            collections.transaction {
                val collectionIds = collections.getCollectionsWhereUserIsOnlyMember(userId).executeAsList()
                collectionIds.takeIf { it.isNotEmpty() }?.let { collections.deleteCollectionsByIds(it)}
            }
        }
    }

}