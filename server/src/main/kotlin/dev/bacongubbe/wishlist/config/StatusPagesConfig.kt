package dev.bacongubbe.wishlist.config

import dev.bacongubbe.wishlist.exception.AlreadyPurchasedException
import dev.bacongubbe.wishlist.exception.UserAlreadyExistException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.installStatusPages() {
    install(StatusPages) {

        exception<UserAlreadyExistException> { call, cause ->
            call.respondText(text = "409: ${cause.message}", status = HttpStatusCode.Conflict)
        }

        exception<AlreadyPurchasedException> { call, cause ->
            call.respondText(text = "409: ${cause.message}", status = HttpStatusCode.Conflict)
        }

        exception<IllegalArgumentException> { call, cause ->
            call.respondText(text = "400: ${cause.message}", status = HttpStatusCode.BadRequest)
        }

        exception<NoSuchElementException> { call, cause ->
            call.respondText(text = "404: ${cause.message}", status = HttpStatusCode.NotFound)
        }

        exception<Throwable> { call, cause ->
            call.respondText(text = "500: ${cause.message}", status = HttpStatusCode.InternalServerError)
        }
    }
}