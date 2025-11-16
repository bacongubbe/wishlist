package dev.bacongubbe.wishlist.router

import dev.bacongubbe.wishlist.service.UserService
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route


fun Route.userRoutes(userService : UserService){
    route("/users") {
        post { // TODO make this depend on auth token
            val body = call.receive<UserCreateRequest>();
            userService.addUser(body.name, body.email)
            call.response.status(HttpStatusCode.Created)
            call.respondText("User added")
        }
        get {
            val users = userService.getUsers()
            call.respond(users)
        }

        delete("/{id}") { // TODO make this depend on auth token
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            userService.deleteUser(id)
            call.respondText("User deleted", status = HttpStatusCode.NoContent)
        }
    }
}

data class UserCreateRequest(val name: String, val email: String)
