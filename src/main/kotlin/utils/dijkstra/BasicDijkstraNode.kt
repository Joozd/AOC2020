package utils.dijkstra

/**
 * Basic Dijkstra implementation with distance type Int
 */
abstract class BasicDijkstraNode: DijkstraNode<Int> {
    override var distanceToStart: Int = 0
    override var previousNode: BasicNode<Int>? = null

    private var unvisited = true

    override fun visited() {
        unvisited = false
    }

    override fun isUnvisited(): Boolean = unvisited

    /**
     * Return all of this nodes neighbours
     */
    abstract override fun getNeighbours(): List<DijkstraNode.Neighbour<Int>>

    override fun reset() {
        distanceToStart = 0
        previousNode = null
        unvisited = true
    }

}