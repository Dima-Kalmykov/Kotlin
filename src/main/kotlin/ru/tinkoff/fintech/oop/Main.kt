package ru.tinkoff.fintech.oop

fun main() {
    val truck = Truck(150, 140, 3000, 17)
    val bus = Bus(140, 160, 7000, 20)
    val racer = Racer(70, 250, 10000, 0)

    val cars = listOf(truck, bus, racer)

    demonstrateGeneralFunctionality(cars)
    println("-".repeat(110))
    demonstrateTruckFunctionality(truck)
    println("-".repeat(110))
    demonstrateBusFunctionality(bus)
    println("-".repeat(110))
    demonstrateRacerFunctionality(racer)
}

fun demonstrateGeneralFunctionality(cars: List<Car>) {
    cars.forEach { car ->
        println(car.getInfo())
    }

    println("\nEach car drive 10 km\n")
    cars.forEach { car ->
        car.run(10)
    }

    cars.forEach { car ->
        println(car.getInfo())
    }
}

fun demonstrateTruckFunctionality(truck: Truck) {
    truck.deliverThings()
    truck.deliverThings("boxes")
}

fun demonstrateBusFunctionality(bus: Bus) {
    println(bus.getInfo())
    println("\nBus put passengers\n")
    bus.putPassengers()
    bus.putPassengers()
    println(bus.getInfo())
    println("\nBus drop passengers\n")
    bus.dropPassengers()
    println(bus.getInfo())
}

fun demonstrateRacerFunctionality(racer: Racer) {
    println(racer.getInfo())
    println("\nAn accident happened\n")
    racer.makeAccident()
    println(racer.getInfo())
}
