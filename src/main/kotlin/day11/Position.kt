package day11

class Position(val x: Int, val y: Int, val hasChair: Boolean) {
    var occupied = false
    val changing: Boolean
        get() = occupied != nextState // check this before executing

    var nextState: Boolean = false

    var neighbours: List<Position> = emptyList()

    /**
     * Run this only on subset with chairs aub
     */
    fun tick() {
        when(neighbours.count { it.occupied }) {
            0 -> nextState = true
            in(4..100) -> nextState = false
        }
    }

    /**
     * Run this only on subset with chairs aub
     */
    fun tick2() {
        when(neighbours.count { it.occupied }) {
            0 -> nextState = true
            in(5..100) -> nextState = false
        }
    }

    fun execute() { occupied = nextState }

    fun fillNeighbours(map: List<List<Position>>) {
        neighbours = listOfNotNull(map.getOrNull(y-1)?.getOrNull(x-1),
            map.getOrNull(y-1)?.getOrNull(x),
            map.getOrNull(y-1)?.getOrNull(x+1),
            map.getOrNull(y)?.getOrNull(x-1),
            map.getOrNull(y)?.getOrNull(x+1),
            map.getOrNull(y+1)?.getOrNull(x-1),
            map.getOrNull(y+1)?.getOrNull(x),
            map.getOrNull(y+1)?.getOrNull(x+1))
    }

    fun fillNeighbours2(map: List<List<Position>>) {
        neighbours = ("UDLR".map{it.toString()} + "ULURDLDR".chunked(2)).mapNotNull{
            findSeatInDirection(map, it)
        }
    }

    fun reset(){
        neighbours = emptyList()
        occupied = false
        nextState = false
    }

    /**
     * Direction can be "UDLR" or a combination of two (eg. UL or DR or RD)
     * It does not check for incorrect input.
     */
    private fun findSeatInDirection(map: List<List<Position>>, direction: String, distance: Int = 1): Position?{
        val nextPos = when(direction){
            "U" -> map.getOrNull(y-distance)?.getOrNull(x)
            "D" -> map.getOrNull(y+distance)?.getOrNull(x)
            "L" -> map.getOrNull(y)?.getOrNull(x-distance)
            "R" -> map.getOrNull(y)?.getOrNull(x+distance)
            else -> {
                val up = "U" in direction
                val left = "L" in direction
                map.getOrNull(if (up)y-distance else (y + distance))?.getOrNull(if (left)x-distance else (x + distance))
            }
        }
        return when {
            nextPos == null -> null
            nextPos.hasChair -> nextPos
            else -> findSeatInDirection(map, direction, distance + 1)
        }
    }
}