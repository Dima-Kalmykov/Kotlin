package ru.tinkoff.fintech.coroutines

import ru.tinkoff.fintech.coroutines.task1.utils.DataGetter
import ru.tinkoff.fintech.coroutines.task2.Notifier


fun main() {
    val dataGetter = DataGetter()
    dataGetter.start()

    println()

    val notifier = Notifier()
    notifier.start()
}
