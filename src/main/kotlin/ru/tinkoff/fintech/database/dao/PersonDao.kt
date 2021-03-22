package ru.tinkoff.fintech.database.dao

import ru.tinkoff.fintech.database.entities.Person

data class PersonDao(
    val persons: List<Person> = listOf(
        Person(1, "Dima", listOf(BookDao().getById(1))),
        Person(2, "Nastya", listOf(BookDao().getById(2), BookDao().getById(3))),
        Person(3, "Ivan", listOf(BookDao().getById(4))),
    )
) {

    fun getAll(): List<Person> {
        return persons
    }

    fun getById(personId: Int): Person {
        return persons.firstOrNull() { it.personId == personId }
            ?: throw NullPointerException("Invalid person id")
    }
}