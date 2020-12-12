package day12

import Solution // Contains functions for reading input file and timing how long it takes to get the answers
import utils.extensions.abs

/**
 * Happiest with part 1, part 2 has this big `when` part that could probably be 2 lines, but I was too lazy to think about that.
 */
class Day12(day: Int): Solution(day) {
    override val first: String
        get() = one().toString()
    override val second: String
        get() = two().toString()

    private fun one(): Int {
        val ns = inputLines.filter{it.startsWith("S")}.sumBy { it.drop(1).toInt() } - inputLines.filter{it.startsWith("N")}.sumBy { it.drop(1).toInt() }
        val ew = inputLines.filter{it.startsWith("E")}.sumBy { it.drop(1).toInt() } - inputLines.filter{it.startsWith("W")}.sumBy { it.drop(1).toInt() }
        val boatPos = Boat().apply {
            inputLines.filter { it.first() in "LRF" }.forEach {
                move(it)
            }
        }
        return (boatPos.x + ew).abs() + (boatPos.y + ns).abs()
    }

    private fun two(): Int {
        val waypoint = Waypoint()
        var x = 0
        var y = 0
        inputLines.forEach {
            if (it.first() in ("NESWLR")) waypoint.execute(it)
            else {
                x += waypoint.x * it.drop(1).toInt()
                y += waypoint.y * it.drop(1).toInt()
            }
        }
        println("x: $x, y: $y")
        return x.abs() + y.abs()
    }
}