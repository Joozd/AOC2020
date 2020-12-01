package utils.dijkstra

abstract class BasicMazeNode: BasicGridNode(), MazeNode{
    protected var _deadEnd = false
    override val deadEnd
        get() = _deadEnd

    /**
     * This will run when clearing dead ends.
     * You could override this to move anything in this node to its only neighbour (it will have only one or it won't run)
     * so you can check if objects are on the way to another object, to reduce complexity of the maze
     * (in case you are looking for a shortest route through multiple points)
     */
    override fun markDeadEnd() {
        _deadEnd = true
    }
}