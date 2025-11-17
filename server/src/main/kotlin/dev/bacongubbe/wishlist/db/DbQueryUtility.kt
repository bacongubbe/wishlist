package dev.bacongubbe.wishlist.db

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> dbQuery(block: () -> T): T =
    withContext(Dispatchers.IO) { block() }