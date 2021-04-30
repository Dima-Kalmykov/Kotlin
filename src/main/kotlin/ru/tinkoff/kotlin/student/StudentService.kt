package ru.tinkoff.kotlin.student

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

class StudentService(
    private val database: Database,
    private val dao: StudentDao
) {
    fun findAll(): List<Student> = dao.findAll()

    fun create(name: String, groupNumber: Int): Student = transaction(database) {
        dao.create(name, groupNumber)
    }

    fun update(id: Int, name: String, groupNumber: Int): Student = transaction(database) {
        dao.update(id, name, groupNumber)
    }

    fun delete(id: Int) = transaction(database) {
        dao.delete(id)
    }
}