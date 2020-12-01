package utils.dijkstra

interface BasicNode<T: Number> {
    /**
     * Initialize [distanceToStart] as distance to first node (usually zero if starting there)
     */
    var distanceToStart: T

    /**
     * Should start as null
     */
    var previousNode: BasicNode<T>?

    /**
     * This will be called when the shortest route to this DijkstraNode has been found
     * This should mark this node so [isUnvisited] will return false
     */
    fun visited()

    /**
     * should be true is this DijkstraNode has not yet been visited (eg. if visit() has not yet been called)
     */
    fun isUnvisited(): Boolean

    /**
     * reset all pathfinding values to start so we can find another path
     */
    fun reset()
}