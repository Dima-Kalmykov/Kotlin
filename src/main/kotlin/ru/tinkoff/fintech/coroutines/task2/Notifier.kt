package ru.tinkoff.fintech.coroutines.task2

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel

class Notifier {

    fun start() {
        runBlocking {
            val channel = Channel<String>()
            launch { subscribe(channel, 0) }
            launch { subscribe(channel, 1) }
            launch { subscribe(channel, 2) }
            repeat(10) {
                println(channel.receive())
            }
        }
    }

    private suspend fun subscribe(channel: SendChannel<String>, id: Int) {
        val delay = 1000L
        while (true) {
            channel.send("User with id = $id has got notification :)")
            delay(delay)
        }
    }
}