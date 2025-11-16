package dev.bacongubbe.wishlist.router

import dev.bacongubbe.wishlist.service.CollectionService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.collectionRoutes(collectionService : CollectionService) {
    post("/collections") {
        val body = call.receive<CreateCollectionRequest>()
        collectionService.createNewCollectionForUser(body.userId, body.name)
        call.response.status(HttpStatusCode.Created)
        call.respondText("Collection created")
    }
    get("/collections") {
        val userId = call.queryParameters["user-id"] ?: return@get call.respondText(
            "Missing userId",
            status = HttpStatusCode.BadRequest
        )
        val collections = collectionService.getCollectionsForUser(userId)
        call.respond(collections)
    }
}

private data class CreateCollectionRequest(val name: String, val userId: String)