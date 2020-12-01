package utils.dijkstra

interface DijkstraNode<T: Number>: BasicNode<T> {
    /**
     * Return all of this nodes neighbours
     */
    fun getNeighbours(): List<Neighbour<T>>


    class Neighbour<T: Number>(val node: DijkstraNode<T>, val distance: T){
        override operator fun equals(other: Any?) =
            if (other !is Neighbour<*>) false
            else other.node == node && other.distance == distance

        override fun hashCode(): Int =
            31 * node.hashCode() + distance.hashCode()
    }
}