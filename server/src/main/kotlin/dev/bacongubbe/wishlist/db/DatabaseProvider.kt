package dev.bacongubbe.wishlist.db

import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dev.bacongubbe.wishlist.WishlistDatabase
import io.ktor.server.config.*

object DatabaseProvider {
    private lateinit var _db: WishlistDatabase

    val db: WishlistDatabase
        get() {
            check(::_db.isInitialized) { "Database has not been initialized yet!" }
            return _db
        }

    fun init(config: ApplicationConfig) {
        check(!::_db.isInitialized) { "Database is already initialized!" }

        val hikariConfig = HikariConfig().apply {
            jdbcUrl = config.property("ktor.database.url").getString()
            driverClassName = config.property("ktor.database.driver").getString()
            username = config.property("ktor.database.user").getString()
            password = config.property("ktor.database.password").getString()
        }

        val dataSource = HikariDataSource(hikariConfig)
        val driver = dataSource.asJdbcDriver()

        WishlistDatabase.Schema.create(driver)
        _db = WishlistDatabase(driver)
    }
}