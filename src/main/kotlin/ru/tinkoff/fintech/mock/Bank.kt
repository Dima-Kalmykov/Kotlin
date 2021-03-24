package ru.tinkoff.fintech.mock

import java.time.LocalDateTime

class Bank(
    var name: String = "",
    var rating: Int = 0,
    var foundationDate: LocalDateTime = LocalDateTime.now(),
    var employees: List<Employee> = listOf()
) {
    fun getEmployeeByName(employeeName: String): Employee? {
        for (employee in employees) {
            if (employee.name == employeeName) {
                return employee
            }
        }

        return null
    }
}

fun bank(block: Bank.() -> Unit): Bank = Bank().apply(block)
