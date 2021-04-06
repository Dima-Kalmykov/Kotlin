package ru.tinkoff.fintech.coroutines.task1.utils

import ru.tinkoff.fintech.coroutines.task1.book_info.AdditionalBookInfo
import ru.tinkoff.fintech.coroutines.task1.book_info.FullBookInfo
import ru.tinkoff.fintech.coroutines.task1.book_info.MainBookInfo

class MainService {

    fun getFullInfo(
        mainInfo: MainBookInfo,
        additionalInfo: AdditionalBookInfo?
    ): FullBookInfo {
        val title = mainInfo.title
        val author = mainInfo.author
        val pageAmount = additionalInfo?.pageAmount
        val releaseDate = additionalInfo?.releaseDate
        return FullBookInfo(title, author, pageAmount, releaseDate)
    }
}
