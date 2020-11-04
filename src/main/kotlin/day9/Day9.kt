package day9

import Solution

class Day9(private val input: List<String>): Solution() {
    /**
     * Recursive function 1
     */
    private fun findRemainingDistance(destinations: List<Destination>, visitedPlaces: List<Destination> = emptyList(), currentDistance: Int = 0): Int {
        return if (visitedPlaces.size == destinations.size) {
            currentDistance
        } else {
            destinations.filter{it !in visitedPlaces}.map{
                findRemainingDistance(destinations, visitedPlaces + it, if (visitedPlaces.isEmpty()) 0 else currentDistance + (visitedPlaces.last().targets[it.name] ?: error("JOOZD ERROR 1")))
            }.minOrNull() ?: Int.MAX_VALUE
        }
    }

    /**
     * Recursive function 2
     */
    private fun findLongestDistance(destinations: List<Destination>, visitedPlaces: List<Destination> = emptyList(), currentDistance: Int = 0): Int {
        return if (visitedPlaces.size == destinations.size) {
            currentDistance
        } else {
            destinations.filter{it !in visitedPlaces}.map{
                findLongestDistance(destinations, visitedPlaces + it, if (visitedPlaces.isEmpty()) 0 else currentDistance + (visitedPlaces.last().targets[it.name] ?: error("JOOZD ERROR 1")))
            }.maxOrNull() ?: Int.MIN_VALUE
        }
    }

    /**
     * Data to be used. Lazy initialized for better timing
     */
    private val locations: List<String> by lazy {
        (input.map { it.split(" ").first() }
                + input.map { it.split(" ")[2] })
            .distinct()
    }

    private val destinations: List<Destination> by lazy {
        locations.map { loc ->
            (input.filter { it.startsWith(loc) }.map { destFromLoc ->
                destFromLoc.split(' ').let {
                    it[2] to it[4].toInt()
                }
            } + input.filter { it.split(' ')[2] == loc }.map { destToLoc ->
                destToLoc.split(' ').let {
                    it[0] to it[4].toInt()
                }
            }).toMap().let {
                Destination(loc, it)
            }
        }
    }


    override fun first() {
        println("lalala antwoord ${findRemainingDistance(destinations)}")
    }

    override fun second() {
        println("lalala antwoord ${findLongestDistance(destinations)}")
    }
}