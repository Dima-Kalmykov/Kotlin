package ru.tinkoff.fintech.database.utils

import ru.tinkoff.fintech.database.dao.PersonDao
import ru.tinkoff.fintech.database.dao.ShopDao
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException

class Client(val connection: Connection) {

    fun fillOneToManyTable(sql: String) {
        val persons = PersonDao().getAll()
        for (person in persons) {
            for (book in person.readBooks) {
                try {
                    val preparedStatement = connection.prepareStatement(sql)
                    preparedStatement.setInt(1, book.bookId)
                    preparedStatement.setInt(2, person.personId)
                    preparedStatement.setInt(3, book.pageAmount)
                    preparedStatement.setInt(4, book.rating)
                    preparedStatement.executeUpdate()
                } catch (ex: SQLException) {
                    println("Can't insert value to the \"book\" table. Reason: ${ex.message}")
                }
            }
        }
    }

    fun fillManyToManyTable(sql: String) {
        val shops = ShopDao().getAll()
        for (shop in shops) {
            for (id in 0..1) {
                val person = shop.visitors[id]
                val supplier = shop.suppliers[id]
                try {
                    val preparedStatement = connection.prepareStatement(sql)
                    preparedStatement.setInt(1, supplier.supplierId)
                    preparedStatement.setInt(2, person.personId)
                    preparedStatement.setInt(3, shop.shopId)
                    preparedStatement.setString(4, supplier.companyName)
                    preparedStatement.setInt(5, supplier.age)
                    preparedStatement.executeUpdate()
                } catch (ex: SQLException) {
                    println("Can't insert value to the \"supplier\" table. Reason: ${ex.message}")
                }
            }
        }
    }

    fun getById(sql: String, id: Int): ResultSet? {
        return executeSql(sql + id)
    }

    fun executeSql(sql: String): ResultSet? {
        try {
            return connection.createStatement().executeQuery(sql)
        } catch (ex: SQLException) {
            println("Invalid sql query: ${ex.message}")
        }

        return null
    }
}