package day23

import Solution // Contains functions for reading input file and timing how long it takes to get the answers
import shared.CircleLinkedSet

/**
 * I could use something like a LinkedHashSet, or I could make a whole Collection myself!
 *
 */
class Day23(day: Int): Solution(day) {
    // override val inputLines = test1

    val input = inputLines.first().map{it.toString().toInt()}

    override val first: String
        get() = one()
    override val second: String
        get() = two().toString()

    private fun one(): String {
        var circle = input

        repeat(100){
            //println(circle)
            val current = circle.take(1)
            val remaining = circle.drop(4)
            val movedCups = circle.slice(1..3)
            circle = (remaining+current).let{ smallCirc ->
                val destination = smallCirc.filter{it < current.first()}.maxOrNull() ?: smallCirc.maxOrNull()!!
                //println("pick up: $movedCups")
                //println("destination: $destination\n")
                val marker = smallCirc.indexOf(destination)
                (smallCirc.slice(0..marker)) + movedCups + smallCirc.drop(marker+1)
            }

        }
        return circle.rotateTo(1).drop(1).joinToString(""){ it.toString()}
    }

    private fun <T> List<T>.rotateTo(n: T): List<T> {
        val i = indexOf(n)
        return drop(i) + take(i)
    }


    private fun two(): Long {
        val largeList = input + (1..1000000).toList().drop(input.size)
        val circle = CircleLinkedSet(largeList)
        val max = circle.size

        repeat(10000000){
            val current = circle.first()
            val popped = circle.rotate().pop(3)
            val destination = makeDest(current, popped, max)
            circle.insertAfter(destination, popped)
        }
        val answer = circle.takeFrom(1, 3).drop(1)
        println(circle.indices)
        return answer.first().toLong() * answer.last()

    }

    private fun makeDest(current: Int, popped: List<Int>, max: Int): Int {
        var c = current-1
        while (c in popped || c < 1) {
            if (c<1) c = max
            else c--
        }
        return c
    }

    companion object{

        val test1 = ("389125467").lines()
    }
}
