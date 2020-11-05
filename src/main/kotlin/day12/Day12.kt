package day12

import Solution

/**
 * Probably not the fastest or shortest solution, but in the end it's pretty neat I guess.
 */
class Day12(private val input: String): Solution() {
    override fun first() {
        "-?[0-9]+".toRegex().findAll(input).sumBy{it.value.toInt()}.let {
            println("answer 1: $it}")
        }
    }

    override fun second() {
        Obj(Obj.ARRAY).run{
            addJson(input)
            println("answer 2: $sum")
        }
    }
}