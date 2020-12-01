package utils.dijkstra

interface GridNode: BasicNode<Int> {
    /**
     * x-coordinate of this node
     */
    val x: Int

    /**
     * y-coordinate of this node
     */
    val y: Int

    /**
     * provide the shortest distance over the grid to [target]
     */
    fun getMinimumDistance(target: GridNode): Int = (x - target.x).abs() + (y-target.y).abs()

    /**
     * Return all neighbours of this node
     */
    fun getNeighbours(): List<GridNode>
}