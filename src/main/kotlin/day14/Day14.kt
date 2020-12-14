package day14

import Solution // Contains functions for reading input file and timing how long it takes to get the answers
import utils.extensions.getDigits
import utils.extensions.splitToWords

class Day14(day: Int): Solution(day) {
    // val extraInput = getExtraInputLinesForDay(dayNumber, "a")

    override val first: String
        get() = one().toString()
    override val second: String
        get() = two().toString()

    private fun one(): Long {
        var currentMask = ""
        val memory = HashMap<Int, Long>()
        inputLines.forEach{ line ->
            val w = line.splitToWords()
            if (w[0]. startsWith("ma")) currentMask = w.last()
            else memory[w[0].getDigits().toInt()] = mask(w.last().toLong(), currentMask)
        }
        return memory.values.sum()
    }

    private fun two(): Long {
        val memory = HashMap<Long, Long>()
        var mask = ""
        inputLines.forEach{ line ->
            val w = line.splitToWords()
            if (w[0]. startsWith("ma")) mask = w.last()
            else {
                val valueToSet = w.last().toLong()
                addressalize(mask, w[0].getDigits().toLong()).forEach { a ->
                    memory[a] = valueToSet
                }
            }
        }
        return memory.values.sum()
    }

    /**
     * The transformation function of question 1
     */
    private fun mask(initialValue: Long, mask: String): Long{
        val setOnes = mask.replace('X', '0').toLong(2) // leaves a mask with the ones as 1 and the rest as 0
        val setZeroes = mask.replace('X', '1').toLong(2) // leaves a mask with the ones as 1 and the rest as 0
        return initialValue.and(setZeroes).or(setOnes)
    }

    /**
     * The transformation function of question 2
     */
    private fun addressalize (mask: String, originalAddress: Long): List<Long>{
        val initial = originalAddress.or(mask.replace('X', '0').toLong(2)).toString(2).padStart(36, '0') // address with hard 1's set
        val xs = mask.mapIndexedNotNull { index, c -> if (c == 'X') index else null }
        val size = xs.joinToString("") { "1" }.toInt(2) + 1
        return (0 until size).map{ bits ->
            val bitsString = bits.toString(2).padStart(xs.size, '0')

            val m = initial.toCharArray()
            xs.forEachIndexed { index, pos -> m[pos] = bitsString[index] }

            m.joinToString("").toLong(2)
        }
    }
}