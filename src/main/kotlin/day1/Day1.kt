package day1

import Solution
import utils.PairMaker

class Day1(day: Int): Solution(day) {
    private val entries = inputLines.map{it.toInt()}.sorted()

    override val first: String
        get() = PairMaker(entries).firstOrNull{ it.first + it.second == 2020}?.let{ it.first * it.second}.toString()
    override val second
        get() = two()

    private fun two(): String{
        val pairs = PairMaker(entries)
        entries.forEach { third ->
            pairs.firstOrNull { it.first + it.second + third == 2020 }?.let{
                return("${it.first} * ${it.second} * $third = ${it.first * it.second * third} (sum is ${it.first + it.second + third})")
            }
        }
        return "none found"
    }
}