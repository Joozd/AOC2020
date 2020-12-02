package day2

import Solution

class Day2(day: Int): Solution(day) {
    override val first
        get() = one().toString()
    override val second
        get() = two().toString()

    private var pl: List<Password>? = null

    private fun one() = inputLines.map { Password.of(it)}.also{
        pl = it}.filter { it.isValidOne }.size

        private fun two() = pl!!.filter { it.isValidTwo }.size
}