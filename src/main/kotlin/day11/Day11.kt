package day11

import Solution // Contains functions for reading input file and timing how long it takes to get the answers

class Day11(day: Int): Solution(day) {
    private val grid by lazy{
        inputLines.mapIndexed{ y, line ->
            line.mapIndexed {x, c ->
                Position(x,y, c == 'L')
            }
        }
    }

    private val allPositions by lazy { grid.flatten() }

    override val first: String
        get() = one().toString()
    override val second: String
        get() = two().toString()

    private fun one(): Int {
        allPositions.forEach {
            it.fillNeighbours(grid)
        }
        val chairs = allPositions.filter { it.hasChair }
        do{
            chairs.forEach { it.execute() }
            chairs.forEach { it.tick() }
        } while (chairs.any { it.changing })
        return chairs.count { it.occupied }
    }

    private fun two(): Int {
        allPositions.forEach {
            it.reset()
            it.fillNeighbours2(grid)
        }
        val chairs = allPositions.filter { it.hasChair }
        do{
            chairs.forEach { it.execute() }
            chairs.forEach { it.tick2() }
        } while (chairs.any { it.changing })
        return chairs.count { it.occupied }
    }
}