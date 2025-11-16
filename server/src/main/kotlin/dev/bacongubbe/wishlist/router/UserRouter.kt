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


fun Route.userRoutes(userService : UserService){
    post("/users") {
        val body = call.receive<UserCreateRequest>();
        userService.addUser(body.name, body.email)
        call.response.status(HttpStatusCode.Created)
        call.respondText("User added")
    }
    get("/users") {
        val users = userService.getUsers()
        call.respond(users)
    }
    delete("/users/{id}") {
        val id = call.parameters["id"] ?: return@delete call.respondText(
            "Missing id",
            status = HttpStatusCode.BadRequest
        )
        userService.deleteUser(id)
        call.respondText("User deleted", status = HttpStatusCode.NoContent)
    }

}

data class UserCreateRequest(val name: String, val email: String)
