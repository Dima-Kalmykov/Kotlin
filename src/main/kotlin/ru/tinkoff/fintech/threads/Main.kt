package ru.tinkoff.fintech.threads

fun main() {
    val threadCreator = ThreadCreator()
    threadCreator.createThreadsInDifferentWays()

    separateProgramExecution(1000)

    val threadReadWriter = ThreadReadWriter()
    threadReadWriter.startTest()

    separateProgramExecution(6000)

    val threadPoolContainer = ThreadPoolContainer()
    threadPoolContainer.startTest()
    Thread.sleep(1000)
    threadPoolContainer.printResult()
}

/**
 * Separate result of each program execution.
 */
fun separateProgramExecution(time: Long) {
    Thread.sleep(time)
    println("\n" + "-".repeat(50) + "\n")
}
