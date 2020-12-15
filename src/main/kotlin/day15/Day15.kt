package day15

import Solution // Contains functions for reading input file and timing how long it takes to get the answers

class Day15(day: Int): Solution(day) {
    private val initialCalls by lazy {
        inputLines.first().split(',').mapIndexed { index, s -> s.toInt() to index + 1 }.toMap()
    }

    override val first: String
        get() = calculate(2020).toString()
    override val second: String
        get() = calculate(30000000).toString()

    private fun calculate(target: Int): Int {
        val database = initialCalls.toMutableMap()
        var counter = initialCalls.values.maxOrNull()!! + 1
        var next = 0
        while (counter < target){
            val nextNext = counter - (database[next] ?: counter)
            database[next] = counter++
            next = nextNext
        }
        return next
    }
}