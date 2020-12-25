package day25

import Solution // Contains functions for reading input file and timing how long it takes to get the answers

class Day25(day: Int): Solution(day) {
    //override val inputLines = test1

    val input = inputLines.map { it.toLong() }

    override val first: String
        get() = one()
    override val second = "No question 2"

    private fun one(): String {
        var door = 0
        var value = 1L
        while (value != input.first()){
            value = (value*7)%20201227
            door++
        }
        value = 1
        repeat(door){
            value = (value*input.last())%20201227
        }
        return value.toString()
    }
}