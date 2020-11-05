package day10

import Solution
import java.io.File

class Day10(private val input: String): Solution() {
    private val elementLines = File("inputs\\day10a.txt").readLines() // from http://www.se16.info/js/lands2.htm

    fun one(str: String): String{
        var result = ""
        var s = str // make mutable
        while (s.isNotEmpty()){
            s.charsRepeatingAtStart().let{
                result += it.toString()
                result += s.first()
                s = s.drop(it)
            }
        }
        return result
    }

    private fun String.charsRepeatingAtStart(): Int{
        if (isEmpty()) return 0
        val firstChar = first()
        var count = 1
        while(count < length && this[count] == firstChar) count++
        return count
    }

    fun iterateOne(initial: String, cycles: Int): String =
            when (cycles) {
                0 -> initial
                1 -> one(initial)
                else -> iterateOne(one(initial), cycles-1)
            }

    override fun first() {
        println("Brute-force: ${iterateOne(input, 40)}") // takes about 2 minutes to run

    }
}
