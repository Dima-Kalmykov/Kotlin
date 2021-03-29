package ru.tinkoff.fintech.threads

class ThreadCreator() {

    fun createThreadsInDifferentWays() {
        val thread = SimpleThread()
        thread.start()

        val threadWithRunnable = Thread(SimpleThreadWithRunnable())
        threadWithRunnable.start()

        val dslThread = Thread {
            println("DSL thread has started")
        }

        dslThread.start()

        val daemonThread = Thread {
            println("Daemon thread has started")
        }.apply { isDaemon = true }

        daemonThread.start()

        createThreadsWithDifferentPriorities()
    }

    private fun createThreadsWithDifferentPriorities() {
        val minPriorityThread = Thread {
            println("Oh, I have min priority :(")
        }.apply { priority = 1 }

        val middlePriorityThread = Thread {
            println("Hm, I have medium priority :|")
        }.apply { priority = 5 }

        val highPriorityThread = Thread {
            println("Oh yes, I have the highest priority :)")
        }.apply { priority = 10 }

        minPriorityThread.start()
        middlePriorityThread.start()
        highPriorityThread.start()
    }
}

class SimpleThread: Thread() {
    override fun run() = println("Simple thread has started!")
}

class SimpleThreadWithRunnable: Runnable {
    override fun run() = println("Simple thread with runnable has started!")
}
