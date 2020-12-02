package day2

import Solution
import utils.extensions.splitToWords

class Day2(day: Int): Solution(day) {

    // 15ms
    override val first: String
        get() {
            var counter = 0
            inputLines.forEach{ line ->
                line.splitToWords().let{ words ->
                    val n1 = words[0].take(line.indexOf('-')).toInt()
                    val n2 = words[0].drop(line.indexOf('-') + 1).toInt()
                    val char = words[1].first()
                    if (words[2].filter {it == char}.length in (n1..n2)) counter++
                }
            }
            return counter.toString()
        }

    // 4ms
    override val second: String
        get() {
            var counter = 0
            inputLines.forEach{ line ->
                line.splitToWords().let{ words ->
                    val n1 = words[0].take(line.indexOf('-')).toInt() -1
                    val n2 = words[0].drop(line.indexOf('-') + 1).toInt() -1
                    val char = words[1].first()
                    if ((words[2][n1] == char) xor (words[2][n2] == char)) counter++
                }
            }
            return counter.toString()
        }




/*

    //22ms
    override val first
        get() = one().toString()

    //14ms
    override val second
        get() = two().toString()

    private var pl: List<Password>? = null

    private fun one() = inputLines.map { Password.of(it)}.also{
        pl = it}.filter { it.isValidOne }.size

        private fun two() = pl!!.filter { it.isValidTwo }.size



    // 41 ms
    override val first: String
        get() {
            var counter = 0
            inputLines.forEach{ line ->
                line.splitToWords().let{ words ->
                    val n1 = words[0].take(line.indexOf('-')).toInt()
                    val n2 = words[0].drop(line.indexOf('-') + 1).toInt()
                    val char = words[1].dropLast(1).toRegex()
                    if (char.findAll(words[2]).count() in (n1..n2)) counter++
                }
            }
            return counter.toString()
        }

    */
}