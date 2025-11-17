package dev.bacongubbe.wishlist.config

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.*

val UserIdHeaderInterceptorPlugin = createApplicationPlugin("UserIdHeaderInterceptorPlugin") {
    onCall { call ->
        if (call.request.path().startsWith("/users")) {
            return@onCall
        }
        val header = call.request.header("X-User-ID")
            ?: throw IllegalArgumentException("Missing X-User-ID header")
        call.attributes.put(UserIdKey, header)
    }
}
val UserIdKey = AttributeKey<String>("UserId")