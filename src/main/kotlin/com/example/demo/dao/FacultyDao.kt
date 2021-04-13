package com.example.demo.dao

import com.example.demo.models.Faculty

data class FacultyDao(
    private val faculties: List<Faculty> = listOf(
    Faculty("1", 10, "HSE"),
    Faculty("2", 5, "MSU"),
    Faculty("3", 7, "MIPT")
)) {

    fun getById(id: String): Faculty? {
        return faculties.firstOrNull { it.id == id }
    }
}