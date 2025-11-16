package dev.bacongubbe.wishlist.router

import dev.bacongubbe.wishlist.service.CollectionService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.collectionRoutes(collectionService : CollectionService) {
    route("/collections") {
        post {
            val body = call.receive<CreateCollectionRequest>()
            collectionService.createNewCollectionForUser(body.userId, body.name)
            call.response.status(HttpStatusCode.Created)
            call.respondText("Collection created")
        }
        get {
            val userId = call.queryParameters["user_id"] ?: return@get call.respondText(
                "Missing userId",
                status = HttpStatusCode.BadRequest
            )
            val collections = collectionService.getCollectionsForUser(userId)
            call.respond(collections)
        }
        get("/{id}"){
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val collections = collectionService.getCollectionById(id)
            call.respond(collections)
        }
    }
}

private data class CreateCollectionRequest(val name: String, val userId: String)