package day11

import Solution // Contains functions for reading input file and timing how long it takes to get the answers

class Day11(day: Int): Solution(day) {
    private val grid by lazy {
        inputLines.mapIndexed { y, line ->
            line.mapIndexed { x, c ->
                Node(x, y, c == 'L')
            }
        }
    }

    private val allPositions by lazy { grid.flatten() }

    override val first: String
        get() = doIt().toString()
    override val second: String
        get() = doIt(true).toString()


    private fun doIt(two: Boolean = false): Int {
        allPositions.forEach {
            if (two) it.question2(grid) else it.fillNeighbours(grid)
        }
        val chairs = allPositions.filter { it.hasChair }
        do {
            chairs.forEach { it.execute() }
            chairs.forEach { it.tick() }
        } while (chairs.any { it.changing })
        return chairs.count { it.occupied }
    }
}