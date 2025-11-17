package dev.bacongubbe.wishlist.db

import kotlinx.coroutines.launch

suspend fun runConcurrently(vararg blocks: suspend () -> Unit) = kotlinx.coroutines.coroutineScope {
    blocks.forEach { block ->
        launch { block() }
    }
}