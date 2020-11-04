package day3

import Solution
import java.io.BufferedReader


/**
 * 1:
 * Santa is delivering presents to an infinite two-dimensional grid of houses.

He begins by delivering a present to the house at his starting location, and then an elf at the North Pole
calls him via radio and tells him where to move next.

Moves are always exactly one house to the north (^), south (v), east (>), or west (<).
After each move, he delivers another present to the house at his new location.

However, the elf back at the north pole has had a little too much eggnog, and so his directions are a little off,
and Santa ends up visiting some houses more than once.


How many houses receive at least one present?
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

For example:

> delivers presents to 2 houses: one at the starting location, and one to the east.
^>v< delivers presents to 4 houses in a square, including twice to the house at his starting/ending location.
^v^v^v^v^v delivers a bunch of presents to some very lucky children at only 2 houses.
 *
 * 2:
 * --- Part Two ---
The next year, to speed up the process, Santa creates a robot version of himself, Robo-Santa, to deliver presents with him.

Santa and Robo-Santa start at the same location (delivering two presents to the same starting house), then take turns moving based on instructions from the elf, who is eggnoggedly reading from the same script as the previous year.

This year, how many houses receive at least one present?

For example:

^v delivers presents to 3 houses, because Santa goes north, and then Robo-Santa goes south.
^>v< now delivers presents to 3 houses, and Santa and Robo-Santa end up back where they started.
^v^v^v^v^v now delivers presents to 11 houses, with Santa going one direction and Robo-Santa going the other.
 *
 */
class Day3(private val input: String): Solution() {
    private val currentLocation = Location(0,0)
    private val c: Coordinate
        get() = currentLocation.coordinate

    private val santaLocation = Location(0,0)
    private val s: Coordinate
        get() = santaLocation.coordinate

    private val robotLocation = Location(0,0)
    private val r: Coordinate
        get() = robotLocation.coordinate


    /**
     * Make map and pass out all presents
     */
    private val visitedMap = HashMap<Coordinate, Int>().apply{
        set(c, 1)
        input.forEach { direction ->
            currentLocation.move(direction)
            set(c, (get(c) ?: 0) + 1)
        }
    }

    /**
     * Make map and pass out all presents but with robot
     */
    private val robotMap = HashMap<Coordinate, Int>().apply {
        set(s, 2)
        input.chunked(2).forEach{
            santaLocation.move(it[0])
            robotLocation.move(it[1])
            set(s, (get(s) ?: 0) + 1)
            set(r, (get(r) ?: 0) + 1)
        }
    }


    override fun first() {
        println("Total presents: ${input.length}\n")
        println("Houses visited: ${visitedMap.keys.size} ")
    }

    override fun second() {
        println("Houses visited with robot: ${robotMap.keys.size} ")

    }

    companion object{
        const val test1 = "^v"
        const val test2 = "^>v<"
        const val test3 = "^v^v^v^v^v"
    }
}