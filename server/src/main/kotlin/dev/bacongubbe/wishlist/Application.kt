package dev.bacongubbe.wishlist

import com.fasterxml.jackson.databind.SerializationFeature
import dev.bacongubbe.wishlist.db.Database
import dev.bacongubbe.wishlist.repo.Repositories
import dev.bacongubbe.wishlist.router.collectionRoutes
import dev.bacongubbe.wishlist.router.userRoutes
import dev.bacongubbe.wishlist.service.Services
import dev.bacongubbe.wishlist.service.UserService
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

val db = Database.db
val repos = Repositories(db)
val services = Services(repos)

fun Application.module() {


    install(ContentNegotiation) {
        jackson {
             enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
        userRoutes(services.userService)
        collectionRoutes(services.collectionService)
    }
}
