package dev.bacongubbe.wishlist.router

import dev.bacongubbe.wishlist.config.UserIdKey
import dev.bacongubbe.wishlist.model.dto.AddWishRequest
import dev.bacongubbe.wishlist.service.WishService
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.wishRouter(service : WishService)  {

    route("/wishes") {
        post {
            val userId = call.attributes[UserIdKey]
            val body = call.receive<AddWishRequest>()
            val created = service.addWishToWishlist(userId, body)
            call.response.status(HttpStatusCode.Created)
            call.respond(created)
        }

        delete("/{wishId}") {
            val wishId = call.parameters["wishId"] ?: return@delete call.respondText(
                "Missing wishId",
                status = HttpStatusCode.BadRequest
            )
            val userId = call.attributes[UserIdKey]
            service.deleteWish(userId, wishId)
            call.respondText("Wish deleted", status = HttpStatusCode.NoContent)
        }
    }

}