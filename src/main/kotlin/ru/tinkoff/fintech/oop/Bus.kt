package ru.tinkoff.fintech.oop

class Bus(
    override var fuelAmount: Int,
    override val maxSpeed: Int,
    override var traveledDistance: Int,
    private var passengersAmount: Int
) : Car {

    override val carType: String
        get() = "Bus"

    override fun run(distance: Int) {
        fuelAmount -= 20
        traveledDistance += distance
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