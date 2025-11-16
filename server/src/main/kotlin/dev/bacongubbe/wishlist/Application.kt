package dev.bacongubbe.wishlist

import com.fasterxml.jackson.databind.SerializationFeature
import dev.bacongubbe.wishlist.db.Database
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.jackson.jackson
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.openapi.openAPI
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import jdk.jfr.internal.jfc.model.SettingsLog.enable
import kotlin.uuid.ExperimentalUuidApi

@ExperimentalUuidApi
fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}


@ExperimentalUuidApi
fun Application.module() {

    install(ContentNegotiation) {
        jackson {
             enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    val userService = UserService(Database.db)

    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
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
    }
}

data class UserCreateRequest(val name: String, val email: String)
