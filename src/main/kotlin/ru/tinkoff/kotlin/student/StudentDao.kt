package ru.tinkoff.kotlin.student

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class StudentDao(private val database: Database) {
    fun findAll() = transaction(database) {
        Students.selectAll().map(::extractStudent)
    }

    fun create(name: String, groupNumber: Int): Student = transaction(database) {
        val id = Students.insertAndGetId {
            it[Students.name] = name
            it[Students.groupNumber] = groupNumber
        }

        Student(id.value, name, groupNumber)
    }

    fun update(id: Int, newName: String, groupNumber: Int): Student = transaction(database) {
        Students.update({ Students.id eq id }) {
            it[name] = newName
            it[Students.groupNumber] = groupNumber
        }

        Student(id, newName, groupNumber)
    }

    fun delete(id: Int) = transaction(database) {
        Students.deleteWhere { Students.id eq id }
    }

    private fun extractStudent(row: ResultRow): Student = Student(
        row[Students.id].value,
        row[Students.name],
        row[Students.groupNumber],
    )
}