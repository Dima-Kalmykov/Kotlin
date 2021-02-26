package ru.tinkoff.fintech.collections

fun main() {
    demonstrateStackFunctionality()
    println("-".repeat(50))
    demonstrateQueueFunctionality()
}

fun demonstrateStackFunctionality() {
    println("STACK\n")
    val stack = stackOf(1, 2, 3)
    println("Current stack: $stack\n")
    println("Remove top element (3)")
    stack.pop()
    println("Current stack: $stack\n")
    println("Add 4 to stack")
    stack += 4 // equivalent for stack.push(4)
    println("Current stack: $stack\n")
    println("Get top value of stack: ${stack.peek()}\n")
    println("Let's double all values")
    stack.map { it * 2 }
    println("Current stack: $stack\n")
}

fun demonstrateQueueFunctionality() {
    println("\nQUEUE\n")
    val queue = queueOf(1, 2, 3)
    println("Current queue: $queue\n")
    println("Remove front element (1)")
    queue.dequeue()
    println("Current queue: $queue\n")
    println("Add 4 to queue")
    queue += 4 // equivalent for queue.push(4)
    println("Current queue: $queue\n")
    println("Get front value of queue: ${queue.peek()}\n")
    println("Let's double all values")
    queue.map { it * 2 }
    println("Current queue: $queue\n")
}