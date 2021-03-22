package ru.tinkoff.fintech.database

import ru.tinkoff.fintech.database.utils.Client
import ru.tinkoff.fintech.database.utils.DatabaseInitializer
import ru.tinkoff.fintech.database.utils.Service
import java.sql.DriverManager
import java.sql.SQLException

fun main() {
    var client: Client? = null
    try {
        val connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/postgres", "postgres", "password"
        )
        val initializer = DatabaseInitializer()
        initializer.createOneToManyTable(connection, "book")
        initializer.createManyToManyTable(connection, "supplier")

        client = Client(connection)
        client.fillOneToManyTable("INSERT INTO book VALUES (?, ?, ?, ?)")
        client.fillManyToManyTable("INSERT INTO supplier VALUES (?, ?, ?, ?, ?)")

        makeSqlQueries(client)
    } catch (ex: SQLException) {
        println("Can't connect to database. Reason: ${ex.message}")
    } finally {
        close(client)
    }
}

/**
 * Example of some sql queries.
 */
fun makeSqlQueries(client: Client) {
    val service = Service(client)
    service.findOneItem()
    service.findByPredicate()
    service.join()
    service.groupBy()
    service.orderBy()
}

/**
 * Close connection.
 */
fun close(client: Client?) {
    println()
    if (client != null) {
        val initializer = DatabaseInitializer()
        initializer.deleteTable(client.connection, "book")
        initializer.deleteTable(client.connection, "supplier")
        client.connection.close()
    }
}
