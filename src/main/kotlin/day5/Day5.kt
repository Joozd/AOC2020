package day5

import Solution

class Day5(day: Int): Solution(day) {
    val seatIds by lazy {inputLines.map {it.seatId()}}

    override val first: String
        get() = seatIds.maxOrNull().toString()
    override val second: String
        get() = (0.."1111111111".toInt(2)).filter {it !in seatIds}.let{missing ->
            missing.filter { it-1 !in missing && it+1 !in missing }
        }.toString()



    private fun String.seatId() = replace("[BR]".toRegex(), "1").replace("[FL]".toRegex(), "0").toInt(2)
}