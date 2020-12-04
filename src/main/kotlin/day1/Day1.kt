package day1

import Solution
import utils.MultiThreader
import utils.PairMaker

class Day1(day: Int): Solution(day) {
    private val entries = inputLines.map{it.toInt()}.sorted()

    override val first: String
        get() {
            entries.forEach { first ->
                (entries.indices.last downTo 0).forEach Inner@{ index ->
                    if (first + entries[index] < 2020) return@Inner
                    if (first + entries[index] == 2020) return (first * entries[index]).toString()
                }


            }
            return "None found"
        }

    override val second: String
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