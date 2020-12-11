package day11

import utils.gameOfLife.GameOfLifeNode

class Node(x: Int, y: Int, val hasChair: Boolean): GameOfLifeNode<Boolean>(x,y) {
    var nullIfQuestion2: Boolean? = false
    var occupied: Boolean
        get() = state
        set(it) {
            state = it
        }

    override var state: Boolean = false

    /**
     * Run this only on subset with chairs aub
     */
    override fun onTick() = when (countNeighbours { it }) {
        0 -> true
        4 -> nullIfQuestion2
        in (5..100) -> false
        else -> null
    }

    fun question2(map: List<List<Node>>) {
        nullIfQuestion2 = null
        state = false
        nextState = false
        neighbours = ("UDLR".map{it.toString()} + "ULURDLDR".chunked(2)).mapNotNull{
            findSeatInDirection(map, it)
        }
    }

    /**
     * Direction can be "UDLR" or a combination of two (eg. UL or DR or RD)
     * It does not check for incorrect input.
     */
    private fun findSeatInDirection(map: List<List<Node>>, direction: String, distance: Int = 1): Node? {
        val nextPos = when (direction) {
            "U" -> map.getOrNull(y - distance)?.getOrNull(x)
            "D" -> map.getOrNull(y + distance)?.getOrNull(x)
            "L" -> map.getOrNull(y)?.getOrNull(x - distance)
            "R" -> map.getOrNull(y)?.getOrNull(x + distance)
            else -> {
                val up = "U" in direction
                val left = "L" in direction
                map.getOrNull(if (up) y - distance else (y + distance))
                    ?.getOrNull(if (left) x - distance else (x + distance))
            }
        }
        return when {
            nextPos == null -> null
            nextPos.hasChair -> nextPos
            else -> findSeatInDirection(map, direction, distance + 1)
        }
    }
}