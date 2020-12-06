package day3a

import Solution
import kotlin.random.Random


/**
 * Which direction misses all trees?
 */
class Day3a(day: Int): Solution(day) {

    override val first: String
        get() = one()
    override val second: String
        get() {
            println("Input solution:")
            val solution = readLine()!!.toLong()
        return generate(solution, 101)
    }

        private fun one(): String {
            var counter = 0
            while (inputLines.mapIndexed { index, s ->
                s[(index*counter)%s.length]
            }.count { it == '#'} != 0){
                counter ++
                print("$counter: \t")
                println(inputLines.mapIndexed { index, s ->
                    s[(index*counter)%s.length]
                }.count { it == '#'})

            }
            return counter.toString()
        }

        private fun generate(solution: Long, width: Int): String =
            (0..1000).joinToString("\n") { line ->
                (0L until width).map { index -> if (Random.Default.nextBoolean() || (((line * solution) % width) == index)) '.' else '#' }
                    .joinToString("")
            }
}