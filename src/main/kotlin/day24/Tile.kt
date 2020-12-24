package day24

import utils.extensions.abs
import utils.gameOfLife.GameOfLifeNode

class Tile(val x: Int, val y: Int){
    val black
        get() = state

    private var state = false
    private var nextState = false

    fun synchronizeNextState(force: Boolean? = null){
        force?.let{
            state = force
        }
        nextState = state
    }

    fun next(dir: String): Tile = when (dir){
        "nw" -> Tile((x - 1) + (y%2).abs(), y-1)
        "ne" -> Tile(x + (y%2).abs(), y-1)
        "e" -> Tile(x+1, y)
        "se" -> Tile(x + (y%2).abs(), y+1)
        "sw" -> Tile((x - 1) + (y%2).abs(), y+1)
        "w" -> Tile(x-1, y)
        else -> error("ERROR 1")
    }
    var neighbours = HashSet<Tile>()

    val allNeighboursKnown
        get() = neighbours.size == 6

    fun generateNeighbours() = listOf("nw", "ne", "e", "se", "sw", "w").map {next(it)}

    private var alreadyGeneratedOnce = false
    fun generateNeighboursOnce() = if (alreadyGeneratedOnce) null else {
        alreadyGeneratedOnce = true
        listOf("nw", "ne", "e", "se", "sw", "w").map {next(it)}
    }

    fun tick(): Tile{
        if (black && neighbours.count { it.black } !in listOf(1,2)) nextState = false
        if (!black && neighbours.count { it.black } == 2) nextState = true
        return this
    }

    fun execute(){
        state = nextState
    }

    fun flip(): Tile {
        state = !state
        return this
    }

    override fun toString() = "Tile ($x,$y) - ${if (black) "black" else "white"}"

    fun debugString(): String = "($x,$y) - ${neighbours.count { it.black }}(${neighbours.size})"

    override fun equals(other: Any?): Boolean =
        other is Tile && other.x == x && other.y == y

    override fun hashCode(): Int = x.shl(16) + y
}