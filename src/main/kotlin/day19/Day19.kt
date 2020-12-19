package day19

import Solution // Contains functions for reading input file and timing how long it takes to get the answers

class Day19(day: Int): Solution(day) {
    private val rulesInput = inputGroups.first()
    private val dataInput = inputGroups.last()
    private val allRules = HashMap<Int, Rule>()

    override val first: String
        get() = one().toString()
    override val second: String
        get() = two().toString()

    private fun one(): Int {
        rulesInput.map{Rule.parseToMap(allRules, it)}
        Rule.finalRules = allRules.filterValues { it.hardValue != null }.keys // probably bad form to do it like this, but easiest / fastest way to do it
        return dataInput.filter {allRules[0]!!.isValidFor(it, false)}.size
    }

    private fun two(): Int {
        Rule.FORTY_TWO = allRules[42]!!.allPossibilitiesForThisRule()
        Rule.THIRTY_ONE = allRules[31]!!.allPossibilitiesForThisRule()
        Rule.specialRules = listOf(allRules[8]!!, allRules[11]!!)
        return dataInput.filter {allRules[0]!!.isValidFor(it, true)}.size
    }
}