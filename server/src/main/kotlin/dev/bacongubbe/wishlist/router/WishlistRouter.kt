package dev.bacongubbe.wishlist.router

import dev.bacongubbe.wishlist.config.UserIdKey
import dev.bacongubbe.wishlist.model.dto.AddWishlistRequest
import dev.bacongubbe.wishlist.service.WishlistService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.wishlistRouter(service: WishlistService) {
    route("/wishlists") {
        post {
            val body = call.receive<AddWishlistRequest>()
            val userId = call.attributes[UserIdKey]
            val created = service.addNewWishlist(userId, body)
            call.response.status(HttpStatusCode.Created)
            call.respond(created)
        }
        get("/{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val userId = call.attributes[UserIdKey]
            val wishlist = service.getWishlistById(userId,id)
            call.respond(wishlist)
        }
    }
}