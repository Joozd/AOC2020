package day8

import Solution // Contains functions for reading input file and timing how long it takes to get the answers
import utils.extensions.splitToWords
import utils.extensions.startsWithAny

class Day8(day: Int): Solution(day) {
    override val first: String
        get() = runProg().toString()
    override val second: String
        get() = two().toString()

    private fun runProg(input: List<String> = inputLines, debug: Boolean = true): Int? {
        var acc = 0
        var pointer = 0
        val foundInstructions = HashSet<Int>()
        while (true) {
            val i = input[pointer].splitToWords()
            when (i.first()){
                "acc" -> acc += i.last().toInt()
                "jmp" -> pointer += i.last().toInt() - 1
                else -> {}
            }
            // Raise pointer by one and check for terminating condicitons:
            if (++pointer in foundInstructions) return if (debug) acc else null
            if (pointer == input.size) return if (!debug) acc else null
            if (pointer !in input.indices) return null

            foundInstructions.add(pointer)
        }
    }

    private fun two(): Int? {
        inputLines.indices.filter { inputLines[it].startsWithAny("nop", "jmp") }.forEach{ i ->
            runProg(swapOneNup(inputLines, i), false)?.let{ return it}
        }
        return null
    }

    private fun swapNop(s: String): String =
        if (s.startsWith ("nop")) s.replace("nop", "jmp")
        else s.replace( "jmp", "nop")

    private fun swapOneNup(input: List<String>, indexToSwap: Int): List<String> = input.toMutableList().apply{ set(indexToSwap, swapNop(input[indexToSwap]))}
}