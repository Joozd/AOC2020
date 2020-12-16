package day16

import Solution // Contains functions for reading input file and timing how long it takes to get the answers
import utils.extensions.multiIntersect
import utils.extensions.splitToWords

class Day16(day: Int): Solution(day) {
    //rules is a list of all rules per line
    private val rules by lazy { inputGroups.first().map { it.splitToWords().takeLast(3).mapNotNull { w -> w.takeIf { '-' in it }?.split('-')?.let { r -> r.first().toInt()..r.last().toInt() }}}}

    private val ticketLines by lazy { inputGroups.last().drop(1).map { it.split(',').map { it.toInt() }}}

    override val first: String
        get() = one().toString()
    override val second: String
        get() = two().toString()

    private fun one(): Int = rules.flatten().let { rr -> ticketLines.flatten().filter { v -> rr.none { r -> v in r }}.sum() }

    private fun two(): Long {
        //map of fields (ticket positions) to all matching rules for that value
        val tickets = ticketLines.map { values -> values.mapIndexed { index, i -> index to rules.mapIndexedNotNull { rule, ranges -> rule.takeIf { ranges.any { i in it }}} }.toMap() }.filter{it.values.none{ mf -> mf.isEmpty()}}

        // ticket positions to all possible rules for that position
        val possibilities = rules.indices.map{ rule-> rule to tickets.map{it.filterValues { rules -> rule in rules }.keys}.multiIntersect()}

        val rules = HashMap<Int, Int>() // rule number (line number) to ticket position

        // i know this is ugly but it works. Leave me alone.
        while (rules.keys.size != possibilities.size){
            val next = possibilities.first { it.second.filter{it !in rules.keys}.size == 1 }
            rules[next.second.first{it !in rules.keys}] = next.first
        }
        val myTicket = inputGroups[1].drop(1).first().split(',').map{it.toInt()}
        return (0 until 6).fold(1L) { acc, i -> acc * myTicket[rules[i]!!] }
    }
}