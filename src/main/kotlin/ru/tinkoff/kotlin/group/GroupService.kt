package ru.tinkoff.kotlin.group

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import ru.tinkoff.kotlin.student.Student

class GroupService(
    private val database: Database,
    private val dao: GroupDao
) {
    fun findAll(): List<Group> = dao.findAll()

    fun findStudentsInGroup(groupNumber: Int): List<Student> = dao.findAllStudentsInGroup(groupNumber)

    fun create(number: Int): Group = transaction(database) {
        dao.create(number)
    }

    fun update(id: Int, number: Int): Group = transaction(database) {
        dao.update(id, number)
    }

    fun delete(id: Int) = transaction(database) {
        dao.delete(id)
    }
}