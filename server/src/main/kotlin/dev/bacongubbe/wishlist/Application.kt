package dev.bacongubbe.wishlist

import com.fasterxml.jackson.databind.SerializationFeature
import dev.bacongubbe.wishlist.config.installStatusPages
import dev.bacongubbe.wishlist.db.DatabaseProvider
import dev.bacongubbe.wishlist.repo.Repositories
import dev.bacongubbe.wishlist.router.collectionRoutes
import dev.bacongubbe.wishlist.router.userRoutes
import dev.bacongubbe.wishlist.router.wishlistRouter
import dev.bacongubbe.wishlist.service.Services
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {

    installStatusPages()
    install(ContentNegotiation) {
        jackson {
             enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    DatabaseProvider.init(environment.config)
    val repos = Repositories(DatabaseProvider.db)
    val services = Services(repos)

    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
        userRoutes(services.userService)
        collectionRoutes(services.collectionService)
        wishlistRouter(services.wishlistService)
    }
}
