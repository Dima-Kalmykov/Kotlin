package ru.tinkoff.fintech.coroutines.task1.dao

import ru.tinkoff.fintech.coroutines.task1.book_info.AdditionalBookInfo

data class AdditionalInfoDAO(
    val booksInfo: List<AdditionalBookInfo> = listOf(
        AdditionalBookInfo("100 rings", 20, "20.07.1999"),
        AdditionalBookInfo("War and peace", 3000, "01.01.1865")
    )
) {

    fun getByTitle(title: String): AdditionalBookInfo? {
        return booksInfo.firstOrNull { it.title == title }
    }
}
