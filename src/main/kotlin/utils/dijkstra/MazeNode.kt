package utils.dijkstra

interface MazeNode: GridNode {
    /**
     * If a node is closed, this should be true.
     * This can change to close off dead ends. (use custom getter I guess)
     * [getNeighbours] should look at this.
     */
    val deadEnd: Boolean

    /**
     * Close this node
     */
    fun markDeadEnd()

    /**
     * If true, this node can be closed in case it is a dead end
     */
    val canBeDead: Boolean
}