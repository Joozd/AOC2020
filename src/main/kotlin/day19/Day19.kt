package day19

import Solution
import java.util.NoSuchElementException

class Day19(val input: List<String>): Solution() {
    private val conversions = input.dropLast(2).map{Conversion.of(it)}
    private val molecule = input.last()

    /**
     * Try random orders of Conversions until one works (standard order ends in a dead end
     */
    private fun findSolution(l: List<Conversion>): Int {
        try {
            val shuffled = l.shuffled()
            var iterations = 0
            val wantedResult = "e"
            var current = molecule
            while (current != wantedResult) {
                shuffled.first {
                    it.reduceOnce(current)?.let { n ->
                        current = n
                        iterations++
                    } != null
                }
            }
            return iterations
        } catch (e: NoSuchElementException){
            return findSolution(l.shuffled())
        }
    }


    override fun first() {
        val results = conversions.map{ it.possibleReplacements(molecule) }.flatten().distinct()
        println("found ${results.size} possible results.")
    }

    override fun second() {

        println("Found a solution after ${findSolution(conversions)} iterations")
    }
}