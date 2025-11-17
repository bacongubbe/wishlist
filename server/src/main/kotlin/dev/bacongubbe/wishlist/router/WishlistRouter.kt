package dev.bacongubbe.wishlist.router

import dev.bacongubbe.wishlist.model.dto.AddWishRequest
import dev.bacongubbe.wishlist.model.dto.AddWishlistRequest
import dev.bacongubbe.wishlist.service.WishlistService
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.wishlistRouter(service : WishlistService) {
    route("/wishlists") {
       post {
           val body = call.receive<AddWishlistRequest>()
           val userId = call.request.queryParameters["user_id"]?: return@post call.response.status(HttpStatusCode.BadRequest)// todo: get from auth token
           val created = service.addNewWishlist(userId, body)
           call.response.status(HttpStatusCode.Created)
           call.respond(created)
       }
        post("/{id}/wishes") {
            val wishlistId = call.parameters["id"] ?: return@post call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val body = call.receive<AddWishRequest>()
            val created = service.addWishToWishlist(wishlistId, body)
            call.response.status(HttpStatusCode.Created)
            call.respond(created)
        }

        get("/{id}"){
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val wishlist = service.getWishlistById(id)
            call.respond(wishlist)
        }
    }

}