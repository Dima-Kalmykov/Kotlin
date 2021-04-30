package ru.tinkoff.kotlin.group

import kotlinx.serialization.Serializable

@Serializable
data class Group(val id: Int, val number: Int)