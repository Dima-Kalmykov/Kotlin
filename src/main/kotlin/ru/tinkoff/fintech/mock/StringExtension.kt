package ru.tinkoff.fintech.mock

import java.lang.StringBuilder
import java.security.InvalidParameterException

fun String.toUpperAll(): String {
    val builder = StringBuilder()

    for (letter in this) {
        if (!letter.isLetter() && letter != ' ') {
            throw InvalidParameterException("String \"$this\" must contain only letters or spaces")
        }

        builder.append(letter.toUpperCase())
    }

    return builder.toString()
}