package com.example.demo

import org.springframework.stereotype.Component

data class Student(
    val id: String? = null,
    val firstName: String,
    val lastName: String,
    var faculty: Faculty?
)