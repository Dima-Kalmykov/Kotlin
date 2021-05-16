package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import javax.sql.DataSource

//import javax.sql.DataSource


@RestController
class StudentController(
    private val dataSource: DataSource
) {

    @GetMapping("/student")
    fun addStudent(@RequestParam name: String, @RequestParam age: Int) {
        dataSource.connection.use {
            val statement = it.prepareStatement("INSERT INTO logs(name, age) VALUES (?, ?)")
            statement.setString(1, name)
            statement.setInt(2, age)
            statement.execute()
        }
    }

    @GetMapping("/students/{id}")
    fun getStudent(@PathVariable id: Int): Student {
        var student: Student
        dataSource.connection.use {
            val statement = it.prepareStatement("SELECT * FROM logs WHERE id = ?")
            statement.setInt(1, id)
            val result = statement.executeQuery()
            result.next()
            val name = result.getString("name")
            val age = result.getInt("age")
            student = Student(name, age)
        }

        return Student(student.name, student.age, getGrade(id))
    }


    private fun getGrade(id: Int): Int? {
        val uri = "http://localhost:8085/studentsInfo/$id"

        val studentInfo = RestTemplate().getForObject(uri, StudentInfo::class.java)
        return studentInfo?.grade
    }
}