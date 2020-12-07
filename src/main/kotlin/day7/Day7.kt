package day7

import Solution

class Day7(day: Int): Solution(day) {
    private val allBags by lazy { // lazy for timing purposes
        inputLines.map { Bag.of(it) }.apply{
            forEach { it.addBags(this) }
        }
    }

    override val first: String
        get() = allBags.filter { it.containsShinyGold }.size.toString()
    override val second: String
        get() = allBags.first { it.name == "shiny gold" }.bagsInside.toString()
}