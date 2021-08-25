
/*
Imagine you are the developer that needs
to create the same logic using a function.
The function should take parameters:
    how old a car is,
    how many kilometers this car passed,
    the maximum speed,
    and whether this car has automatic transmission.

The default arguments for this model are:
    5 years old,
    ran 100000 km,
    have a maximum speed of 120 kilometers per hour (km/h),
    no automatic transmission.
    The initial price of a new car with the default equipment is 20000$.

The function should work like this:
    1. Every year the price of the car decreases by 2000$.
    2. The price goes down by 100$ for every kilometer per
    hour less than 120 km/h and increases by 100$ for every
    kilometer per hour greater than 120 km/h.
    3. The price lowers by 200$ for every 10000 kilometers
    that the car passed. For example, for 19999 km, the price
    decreases by 200 dollars, but for 20000 km the price lowers
    by 400 dollars.
    4. If the car has automatic transmission, the price of the
    car goes up by 1500$, otherwise, it remains the same.
*/
fun calculatePrice(
    yearsOld: Int = 5,
    ranKilometers: Int = 100_000,
    maximumSpeedInKilometersPerHour: Int = 120,
    hasAutomaticTransmission: Boolean = false
): Int {
    val initialPrice = 20_000
    val compareSpeedInKilometersPerHour = 120
    val decreasePerYear = 2_000
    val speedUnitChange = 100
    val distanceUnitDecrease = 200
    val decreaseDistanceUnit = 10_000
    val automaticTransmissionIncrease = 1_500
    
    var price = initialPrice
    price -= decreasePerYear * yearsOld
    price += (maximumSpeedInKilometersPerHour - compareSpeedInKilometersPerHour) * speedUnitChange
    price -= ranKilometers / decreaseDistanceUnit * distanceUnitDecrease
    if (hasAutomaticTransmission) {
        price += automaticTransmissionIncrease
    }
    return price
}

fun main(args: Array<String>) {
    val choice = readLine()!!
    val value = readLine()!!.toInt()
    val price = when (choice) {
        "old" -> calculatePrice(yearsOld = value)
        "passed" -> calculatePrice(ranKilometers = value)
        "speed" -> calculatePrice(maximumSpeedInKilometersPerHour = value)
        "auto" -> calculatePrice(hasAutomaticTransmission = value == 1)
        else -> 0
    }
    println(price)
}
