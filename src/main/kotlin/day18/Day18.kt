package day18

import Solution // Contains functions for reading input file and timing how long it takes to get the answers

class Day18(day: Int): Solution(day) {
    override val inputLines = test11
    val problems by lazy { inputLines.map {MathProblem.of(it)} }

    override val first: String
        get() = one().toString()
    override val second: String
        get() = two()

    // 1208361047 too low
    // 98721031659607 too high
    // 98202646080000 too low
    private fun one(): Long {
        problems.forEach{
            // println("${it.value.toString().padEnd(15)}:  ${it.string}")
            println(it.value)
        }
        return problems.map{it.value}.sum()
    }

    private fun two(): String {
        return "Answer to question 2"
    }

    companion object{
        val test1 = ("1 + 2 * 3 + 4 * 5 + 6").lines()
        val test2 = "1 + (2 * 3) + (4 * (5 + 6))".lines()
        val test3 = "2 * 3 + (4 * 5)".lines()
        val test4 = "5 + (8 * 3 + 9 + 3 * 4 * 3)".lines()
        val test5 = "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))".lines()
        val test6 = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2".lines()
        val test10 = "4 + (7 + 7 * 6 + 3 * 9) * (111104) + 3".lines()
        val test11 = "4 + (7 + 7 * 6 + 3 * 9) * ((4 + 2 * 4 + 4 + 9 + 7) * 5 * 9 * (3 + 7 + 4 * 4) + (4 * 8 * 7)) + 3".lines()
    }
}