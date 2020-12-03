package day3

import Solution

/**
 * Fun fact: Even in the example, there is no angle within the first 15485863 (one millionth prime) that doesn't hit any trees
 */
class Day3(day: Int): Solution(day) {

    override val first: String
        get() = inputLines.mapIndexed { index, s ->
            s[(index*3)%s.length]
        }.count { it == '#'}.toString()

    /**
     * This is probably not THE fastest way, but takes a negligible amount of time so meh. I think it's nice and clean.
     */
    override val second: String
        get() = listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)        // list of all [right] to [down] possibilities
            .map {count(inputLines, it.first, it.second) }
            .fold(1L) { acc, i -> i * acc }             // needed to be a Long because too big for Int
            .toString()


    private fun count(forest: List<String>, right: Int, down: Int): Int =
        forest.filterIndexed { i, _ -> i % down == 0 }          // in case [down] is not 1
            .mapIndexed { index, s ->
                s[((index*right.toLong())%s.length).toInt()]
            }
            .count { it == '#'}
}