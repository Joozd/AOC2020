package day16

import Solution // Contains functions for reading input file and timing how long it takes to get the answers
import utils.extensions.multiIntersect
import utils.extensions.splitToWords

/**
 * Least amount of lines for extra unreadibility!
 */
class Day16(day: Int): Solution(day) {
    private val rules by lazy { inputGroups.first().map { it.splitToWords().takeLast(3).mapNotNull { w -> w.takeIf { '-' in it }?.split('-')?.let { r -> r.first().toInt()..r.last().toInt() }}}}
    private val ticketLines by lazy { inputGroups.last().drop(1).map { it.split(',').map { it.toInt() }}}

    override val first: String
        get() = one().toString()
    override val second: String
        get() = two().toString()

    private fun one(): Int = rules.flatten().let { rr -> ticketLines.flatten().filter { v -> rr.none { r -> v in r }}.sum() }

    private fun two(): Long {
        val tickets = ticketLines.map { values -> values.mapIndexed { index, i -> index to rules.mapIndexedNotNull { rule, ranges -> rule.takeIf { ranges.any { i in it }}} }.toMap() }.filter{it.values.none{ mf -> mf.isEmpty()}}
        val possibilities = rules.indices.map{ rule-> rule to tickets.map{it.filterValues { rules -> rule in rules }.keys}.multiIntersect()}
        val rules = HashMap<Int, Int>()
        while (rules.keys.size != possibilities.size){ possibilities.first { it.second.filter{it !in rules.keys}.size == 1 }.let { next -> rules[next.second.first{it !in rules.keys}] = next.first } }
        inputGroups[1].drop(1).first().split(',').map{it.toInt()}.let { myTicket -> return (0 until 6).fold(1L) { acc, i -> acc * myTicket[rules[i]!!] } }
    }
}