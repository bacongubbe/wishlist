package dev.bacongubbe.wishlist.util

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun runConcurrently(vararg blocks: suspend () -> Unit) = coroutineScope {
    blocks.forEach { block ->
        launch { block() }
    }
}