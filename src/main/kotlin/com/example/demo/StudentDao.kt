package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

class StudentDao(
    private val students: List<Student> = listOf(
        Student("1", "Dima", "Kalmykov", null),
        Student("2", "Nastya", "Baranova", null),
        Student("3", "Kolya", "Ivanov", null),
    )
) {

    fun getById(id: String): Student? {
        val faculty = FacultyDao().getById(id)
        val student = students.firstOrNull { it.id == id }
        student?.faculty = faculty
        return student
    }

    fun getAll(): List<Student> {
        return students
    }
}