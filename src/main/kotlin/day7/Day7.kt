package day7

import Solution // Contains functions for reading input file and timing how long it takes to get the answers
import utils.extensions.splitToWords

class Day7(day: Int): Solution(day) {
    /**
     * Commented out sections are OOP solution, wanted to try how a functional one did (functional was faster by a third)
     * I think OOP version is easier to understand though, if I would look at this a week from now.
     * So: I can't really say one is better than the other.
     */

    /*
    private val allBags by lazy { // lazy for timing purposes
        inputLines.map { Bag.of(it) }.apply{
            forEach { it.addBags(this) }
        }
    }
    */

    override val first: String
        get() = oneFunctional().size.toString()
        // get() = allBags.filter { it.containsShinyGold }.size.toString()
    override val second: String
        get() = twoFunctional().toString()
        // get() = allBags.first { it.name == "shiny gold" }.bagsInside.toString()

    fun oneFunctional(lookingFor: String = "shiny gold"): Set<String> {
        val bagsHolding = inputLines.filter { lookingFor in it.drop(1) }.toSet()
        return bagsHolding + bagsHolding.map{it.splitToWords().take(2).joinToString(" ")}.map{oneFunctional(it)}.flatten()
    }

    fun twoFunctional(lookingFor: String = "shiny gold", multiplier: Int = 1): Int =
        inputLines.first { it.startsWith(lookingFor) }.dropLast(1).split(" contain ", ", ").drop(1).let { bags ->
            // <<DROPPED: shiny gold bags-|->>3 light green bags-|-5 striped brown bags-|-3 faded fuchsia bags.
            if (bags.first() == "no other bags") 0
            else bags.map { it.first() - '0' }.sum() * multiplier +
                    bags.map { twoFunctional(it.splitToWords().slice(1..2).joinToString(" "), (it.first() - '0') * multiplier)
            }.sum()
        }.also{
            println("$lookingFor gave $it bags")
        }
}