package dev.bacongubbe.wishlist.config

import dev.bacongubbe.wishlist.exception.UserAlreadyExistException
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respondText

fun Application.installStatusPages() {
    install(StatusPages) {

        exception<UserAlreadyExistException> { call, cause ->
            call.respondText(text = "409: ${cause.message}" , status = HttpStatusCode.Conflict)
        }

        exception<Throwable> { call, cause ->
            call.respondText(text = "500: ${cause.message}" , status = HttpStatusCode.InternalServerError)
        }
    }
}