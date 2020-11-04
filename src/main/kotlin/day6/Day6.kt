package day6

import Solution
import shared.Coordinate

class Day6(private val input: List<String>): Solution() {
    private fun one(i: Instruction, grid: Array<IntArray>){
        when(i.operation){
            Instruction.TURN_OFF -> turnOff(grid, i.bottomLeft, i.topRight)
            Instruction.TURN_ON -> turnOn(grid, i.bottomLeft, i.topRight)
            Instruction.TOGGLE -> toggle(grid, i.bottomLeft, i.topRight)
        }
    }

    private fun two(i: Instruction, grid: Array<IntArray>){
        when(i.operation){
            Instruction.TURN_OFF -> decrease(grid, i.bottomLeft, i.topRight)
            Instruction.TURN_ON -> increase(1, grid, i.bottomLeft, i.topRight)
            Instruction.TOGGLE -> increase(2, grid, i.bottomLeft, i.topRight)
        }
    }

    private fun turnOff(g: Array<IntArray>, bottomLeft: Coordinate, topRight: Coordinate) {
        val columns =  (bottomLeft.y..topRight.y)
        (bottomLeft.x..topRight.x).forEach{row ->
            columns.forEach{col ->
                g[row][col] = 0
            }
        }
    }

    private fun turnOn(g: Array<IntArray>, bottomLeft: Coordinate, topRight: Coordinate) {
        val columns =  (bottomLeft.y..topRight.y)
        (bottomLeft.x..topRight.x).forEach{row ->
            columns.forEach{col ->
                g[row][col] = 1
            }
        }
    }

    private fun toggle(g: Array<IntArray>, bottomLeft: Coordinate, topRight: Coordinate) {
        val columns =  (bottomLeft.y..topRight.y)
        (bottomLeft.x..topRight.x).forEach{row ->
            columns.forEach{col ->
                g[row][col]++
            }
        }
    }

    private fun increase(by: Int, g: Array<IntArray>, bottomLeft: Coordinate, topRight: Coordinate) {
        val columns =  (bottomLeft.y..topRight.y)
        (bottomLeft.x..topRight.x).forEach{row ->
            columns.forEach{col ->
                g[row][col] += by
            }
        }
    }

    private fun decrease(g: Array<IntArray>, bottomLeft: Coordinate, topRight: Coordinate) {
        val columns =  (bottomLeft.y..topRight.y)
        (bottomLeft.x..topRight.x).forEach{row ->
            columns.forEach{col ->
                if (g[row][col] > 0)
                    g[row][col]--
            }
        }
    }

    override fun first() {
        val grid = Array(1000){ IntArray(1000) { 0 } }
        input.forEach {
            one(Instruction.parse(it), grid)
        }
        val turnedOn = grid.sumBy { r -> r.count{ it%2 == 1} }
        println("lights turned on: $turnedOn")
    }

    override fun second() {
        val grid = Array(1000){ IntArray(1000) { 0 } }
        input.forEach {
            two(Instruction.parse(it), grid)
        }
        val brightness = grid.sumBy{ it.sum() }
        println("brightness: $brightness")
    }
}