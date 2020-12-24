package day24

import Solution // Contains functions for reading input file and timing how long it takes to get the answers
import shared.AccessibleHashSet
import utils.extensions.getOrAdd

class Day24(day: Int): Solution(day) {
    private val directions by lazy { inputLines.map { Directions(it)}}
    private val visitedLocations = AccessibleHashSet<Tile>()
    private val blackTiles
        get() = visitedLocations.filter { it.black }

    override val first: String
        get() = one().toString()
    override val second: String
        get() = two().toString()

    private fun one(): Int {
        val center = Tile(0,0)
        directions.forEach { dirs ->
            var currentTile = center
            dirs.forEach {  currentTile = currentTile.next(it)  }
            visitedLocations.getOrAdd(currentTile).flip()
        }
        return blackTiles.size
    }

    private fun two(): Int {
        visitedLocations.forEach { it.synchronizeNextState() } // make sure next state is current state so nothing changes f nothing should change
        repeat(100) {
            blackTiles.forEach { blackTile ->
                blackTile.generateNeighboursOnce()?.let { generatedNeighbours ->
                    visitedLocations.getOrAdd(generatedNeighbours).let { foundNeighbours ->
                        blackTile.neighbours.addAll(foundNeighbours)
                        foundNeighbours.forEach { it.neighbours.add(blackTile) }
                    }
                }
            }
            visitedLocations.map{it.tick()}.forEach { it.execute() }
        }
        return blackTiles.size
    }
}