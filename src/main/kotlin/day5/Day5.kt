package day5

import Solution

class Day5(day: Int): Solution(day) {
    //lazy function for my private timing needs.
    private val seatIds by lazy {
        inputLines.map {l -> l.map{(it.toInt()%7%2).toString()}.joinToString("").toInt(2)}
    }

    // get the highest seatID
    override val first: String
        get() = seatIds.maxOrNull().toString()

    // get the first seatId that has an empty one after it, and a filled one after that. The one after it is the answer.
    override val second: String
        get() = (seatIds.first { it+1 !in seatIds && it+2 in seatIds }+1).toString()

}