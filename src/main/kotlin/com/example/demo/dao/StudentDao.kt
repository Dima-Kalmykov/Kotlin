package com.example.demo.dao

import com.example.demo.models.Student

data class StudentDao(
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