package com.example.demo.models

data class Student(
    val id: String? = null,
    val firstName: String,
    val lastName: String,
    var faculty: Faculty?
)