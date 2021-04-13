package com.example.demo

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.reflect.KClass

@RestController
@RequestMapping("/student")
class StudentController {

    private val studentDao: StudentDao = StudentDao()

    @GetMapping
    @ApiOperation("Get all students")
    @ApiResponse(code = 200, message = "OK")
    fun getStudents(): List<Student> {
        return studentDao.getAll()
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiResponses(
        ApiResponse(code = 200, message = "OK", response = Student::class),
        ApiResponse(code = 404, message = "Not found", response = Student::class),
    )
    @ApiOperation("Get student by id")
    fun getStudentById(
        @ApiParam(name = "id", type = "String", value = "student id", example = "1", required = true)
        @PathVariable id: String
    ): ResponseEntity<Student> {
        val student = studentDao.getById(id)

        return if (student == null) {
            ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            ResponseEntity(student, HttpStatus.OK)
        }
    }

    @PostMapping
    @ApiOperation("Add new student")
    @ApiResponse(code = 200, message = "OK")
    fun addStudent(
        @ApiParam(
            name = "student",
            type = "Student",
            value = "student entity",
            required = true
        ) @RequestBody student: Student
    ) {
        println("New student was added: $student")
    }

    @PutMapping("/{id}")
    @ApiOperation("Update student by id")
    @ApiResponse(code = 200, message = "OK")
    fun updateStudent(
        @ApiParam(name = "id", type = "String", value = "student id", example = "1", required = true)
        @PathVariable id: String,
        @ApiParam(name = "student", type = "Student", value = "student entity", required = true)
        @RequestBody student: Student
    ) {
        println("Student with id = $id was updated: $student")
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete student by id")
    @ApiResponse(code = 200, message = "OK")
    fun deleteStudent(
        @ApiParam(name = "id", type = "String", value = "student id", example = "1", required = true)
        @PathVariable id: String
    ) {
        println("Student with id = $id was deleted")
    }
}