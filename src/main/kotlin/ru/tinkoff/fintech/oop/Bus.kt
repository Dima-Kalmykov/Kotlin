package ru.tinkoff.fintech.oop

class Bus(
    override var fuelAmount: Int,
    override val maxSpeed: Int,
    private var passengersAmount: Int,
    override var traveledDistance: Int = 0,
) : Car {

    override val carType: String
        get() = "Bus"

    override fun run(distance: Int) {
        if (fuelAmount >= 20) {
            fuelAmount -= 20
            traveledDistance += distance
        } else {
            println("Fuel ran out")
        }
    }

    fun dropPassengers() {
        if (passengersAmount > 5) {
            passengersAmount -= 5
        }
    }

    fun putPassengers() {
        passengersAmount += 5
    }

    override fun getInfo(): String {
        return super.getInfo() + ", type = $carType, passengers amount = $passengersAmount"
    }
}