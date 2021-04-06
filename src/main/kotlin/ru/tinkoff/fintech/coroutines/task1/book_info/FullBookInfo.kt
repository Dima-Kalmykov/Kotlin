package ru.tinkoff.fintech.coroutines.task1.book_info

data class FullBookInfo(
    val title: String, val author: String,
    val pageAmount: Int?, val releaseDate: String?
)
