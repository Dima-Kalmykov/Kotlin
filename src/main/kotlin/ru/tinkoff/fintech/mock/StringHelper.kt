package ru.tinkoff.fintech.mock

class StringHelper {

    fun containsOnlyDigits(text: String): Boolean {
        for (letter in text) {
            if (!letter.isDigit()) {
                return false
            }
        }

        return true
    }

    fun getSpaceAmount(text: String): Int {
        var spaceAmount = 0
        for (letter in text) {
            if (letter == ' ') {
                spaceAmount++
            }
        }

        return spaceAmount
    }
}