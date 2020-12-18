package day18

import Solution // Contains functions for reading input file and timing how long it takes to get the answers

class Day18(day: Int): Solution(day) {
    private val problems by lazy { inputLines.map {MathProblem.of(it)} }

    override val first: String
        get() = problems.map{it.value}.sum().toString()
    override val second: String
        get() = problems.map{it.value2}.sum().toString().toString()

}