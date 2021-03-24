package ru.tinkoff.fintech.mock

interface EmployeeDao {

    fun getAll(): List<Employee>

    fun getById(id: Int): Employee?
}