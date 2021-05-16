package com.example.demo2.controller

import org.springframework.web.bind.annotation.*
import javax.sql.DataSource

@RestController
class StudentController(
    private val dataSource: DataSource
) {
    @GetMapping("/student")
    fun addStudent(@RequestParam grade: Int) {
        dataSource.connection.use {
            val statement = it.prepareStatement("INSERT INTO students(grade) VALUES (?)")
            statement.setInt(1, grade)
            statement.execute()
        }
    }

    @GetMapping("/studentsInfo/{id}")
    @ResponseBody
    fun getStudents(@PathVariable id: Int): Student {
        val student: Student
        dataSource.connection.use {
            val statement = it.prepareStatement("SELECT * FROM students WHERE id = ?")
            statement.setInt(1, id)
            val result = statement.executeQuery()
            result.next()
            val grade = result.getInt("grade")
            student = Student(grade)
        }

        return student
    }
}