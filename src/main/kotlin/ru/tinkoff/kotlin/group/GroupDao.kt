package ru.tinkoff.kotlin.group

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.tinkoff.kotlin.student.Student
import ru.tinkoff.kotlin.student.Students

class GroupDao(private val database: Database) {
    fun findAll() = transaction(database) {
        Groups.selectAll().map(::extractGroup)
    }

    fun findAllStudentsInGroup(groupNumber: Int): List<Student> = transaction(database) {
        Students.select { Students.groupNumber eq groupNumber }.map(::extractStudent)
    }

    fun create(number: Int): Group = transaction(database) {
        val id = Groups.insertAndGetId {
            it[Groups.number] = number
        }

        Group(id.value, number)
    }

    fun update(id: Int, number: Int): Group = transaction(database) {
        Groups.update({ Groups.id eq id }) {
            it[Groups.number] = number
        }

        Group(id, number)
    }

    fun delete(id: Int) = transaction(database) {
        Groups.deleteWhere { Groups.id eq id }
    }

    private fun extractGroup(row: ResultRow): Group = Group(
        row[Groups.id].value,
        row[Groups.number],
    )

    private fun extractStudent(row: ResultRow): Student = Student(
        row[Students.id].value,
        row[Students.name],
        row[Students.groupNumber],
    )
}