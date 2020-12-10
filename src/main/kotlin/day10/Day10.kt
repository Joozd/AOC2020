package day10

import Solution // Contains functions for reading input file and timing how long it takes to get the answers

/**
 * Calculating things is overrated :)
 */
class Day10(day: Int): Solution(day) {
    private val list by lazy { inputLines.map {it.toInt()}.sorted() }
    private val refList by lazy { listOf(0) + list }
    private val diffs by lazy {
        list.mapIndexed { i, v ->
            v - refList[i]
        }
    }

    override val first: String
        get() = ((diffs.filter{ it == 3 }.size + 1) * diffs.filter { it == 1 }.size).toString()
    override val second: String
        get() = two()


    private fun two(): String {
        println(diffs)
        val subLists = diffs.joinToString("") { it.toString() }.split('3').map{
            when(it.length){
                0 -> ZERO
                1 -> ONE
                2 -> TWO
                3 -> THREE
                4 -> FOUR
                else -> error ("Not supported!")
            }
        }
        return subLists.reduce { acc, i -> acc * i }.toString()
    }

    companion object {
        /**
         * 1 = [(1)] = 1
         * 1,1 = [(1, 1), (2)] = 2
         * 1,1,1 = [(1,1,1), (2,1), (1,2), (3)] = 4
         * 1,1,1,1 = [(1,1,1,1), (2,1,1), (1,2,1), (1,1,2), (2,2), (3,1), (1,3)] = 7
         */
        const val ZERO = 1L
        const val ONE = 1L
        const val TWO = 2L
        const val THREE = 4L
        const val FOUR = 7L
    }
}