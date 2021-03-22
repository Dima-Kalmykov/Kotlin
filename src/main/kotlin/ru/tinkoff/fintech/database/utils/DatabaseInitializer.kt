package ru.tinkoff.fintech.database.utils

import java.sql.Connection
import java.sql.SQLException

class DatabaseInitializer {

    fun createOneToManyTable(connection: Connection, tableName: String) {
        try {
            connection.createStatement().execute(
                """CREATE TABLE $tableName(
                    | book_id SERIAL PRIMARY KEY,
                    | person_id INTEGER,
                    | page_amount INTEGER,
                    | rating INTEGER
                    |);""".trimMargin()
            )

            println("Table \"$tableName\" successfully created!")
        } catch (ex: SQLException) {
            println("Can't create \"$tableName\" table. Reason: ${ex.message}")
        }
    }

    fun createManyToManyTable(connection: Connection, tableName: String) {
        try {
            connection.createStatement().execute(
                """CREATE TABLE $tableName(
                    | supplier_id SERIAL PRIMARY KEY,
                    | person_id INTEGER,
                    | shop_id INTEGER,
                    | company_name TEXT,
                    | age INTEGER
                    |);""".trimMargin()
            )

            println("Table $tableName successfully created!\n")
        } catch (ex: SQLException) {
            println("Can't create \"$tableName\" table. Reason: ${ex.message}")
        }
    }

    fun deleteTable(connection: Connection, tableName: String) {
        try {
            connection.createStatement().execute("DROP TABLE $tableName")
            println("Table \"$tableName\" removed successfully!")
        } catch (ex: SQLException) {
            println("Table \"$tableName\" can't be removed. Reason: ${ex.message}")
        }
    }
}