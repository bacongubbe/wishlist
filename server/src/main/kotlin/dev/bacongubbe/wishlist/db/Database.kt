package dev.bacongubbe.wishlist.db

import app.cash.sqldelight.db.SqlDriver
import org.postgresql.ds.PGSimpleDataSource
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dev.bacongubbe.wishlist.WishlistDatabase
import javax.sql.DataSource

object Database {
    private fun dataSource(): DataSource  {
        val hikariConfig = HikariConfig()
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/wishlist_db")
        hikariConfig.driverClassName = "org.postgresql.Driver"
        hikariConfig.username = "user"
        hikariConfig.password = "pass"
        return HikariDataSource(hikariConfig)
    }

    val db: WishlistDatabase by lazy {
        val driver = dataSource().asJdbcDriver();
        WishlistDatabase.Schema.create(driver)
        WishlistDatabase(dataSource().asJdbcDriver())
    }
}
