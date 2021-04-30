package ru.tinkoff.kotlin.group

import org.jetbrains.exposed.dao.id.IntIdTable

object Groups : IntIdTable() {
    val number = integer("number")
}