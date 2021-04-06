package ru.tinkoff.fintech.coroutines.task1.dao

import ru.tinkoff.fintech.coroutines.task1.book_info.MainBookInfo

data class MainInfoDAO(
    val booksInfo: List<MainBookInfo> = listOf(
        MainBookInfo("100 rings", "Dima Koval"),
        MainBookInfo("War and peace", "Lev Tolstoy"),
        MainBookInfo("A passage to India", "Forster")
    )
) {

    fun getByTitle(title: String): MainBookInfo {
        return booksInfo.first {it.title == title}
    }
}
