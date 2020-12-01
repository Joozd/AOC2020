package utils.dijkstra

object Pathfinding{
    /**
     * Get a route from [start] to [end]
     * if [nodesAreGenerated] is false this wil need to be fed existing nodes that stay the same object every time they are found.
     * and they will be reset after search
     *
     * Dijkstra is an algorithm that guarantees the shortest route, and supports different distances between each node-pair.
     * Does a lot of searches in the wrong direction.
     */
    fun <T: Number> dijkstraRoute(start: DijkstraNode<T>, end: DijkstraNode<T>, nodesAreGenerated: Boolean = true): List<DijkstraNode<T>>? =
            dijkstraRoute(start, nodesAreGenerated) { it == end }


    /**
     * Get a list from start to the first place that satisfies [endCondition]
     * if [nodesAreGenerated] is false this wil need to be fed existing nodes that stay the same object every time they are found.
     * and they will be reset after search
     *
     * Dijkstra is an algorithm that guarantees the shortest route, and supports different distances between each node-pair.
     * Does a lot of searches in the wrong direction.
     */
    fun <T: Number> dijkstraRoute(start: DijkstraNode<T>, nodesAreGenerated: Boolean = true, endCondition: (DijkstraNode<T>) -> Boolean): List<DijkstraNode<T>>?{
        var activeNode: DijkstraNode<T>? = start
        val visitingList = hashSetOf<DijkstraNode<T>>()
        val knownNodes = hashSetOf(start)
        while(activeNode != null && !endCondition(activeNode)){

            //print("looking at $activeNode, knownNodes has ${knownNodes.size}, visitingList has ${visitingList.size}\n")

            //print("Neighbours: ${activeNode.getNeighbours().map{it.node}}\n")

            activeNode.getNeighbours().forEach { neighbour ->
                val node = if (nodesAreGenerated)
                    knownNodes.firstOrNull { it == neighbour.node }
                            ?: neighbour.node.also { knownNodes.add(it) } // Use the version that is in knownNodes
                else neighbour.node.also { knownNodes.add(it) }
                if (node.isUnvisited()) {
                    visitingList.add(node)
                    if (node.distanceToStart == 0 || node.distanceToStart.toDouble() > activeNode!!.distanceToStart.toDouble() + neighbour.distance.toDouble()) {
                        node.distanceToStart = activeNode!!.distanceToStart + neighbour.distance
                        node.previousNode = activeNode
                    }
                }
            }
            activeNode.visited()
            visitingList.remove(activeNode)
            activeNode = visitingList.minByOrNull { it.distanceToStart.toDouble() }
        }

        //Once we are here, searching is done.
        return activeNode?.let{
            val result = arrayListOf(it)
            var parent = it.previousNode
            while (parent != null){
                result.add(parent as DijkstraNode<T>)
                parent = parent.previousNode
            }
            result.reversed()
        }.also {
            if (it == null) println("No results found")
            knownNodes.forEach {
                it.reset()
            }
        }
    }

    /**
     * Get a list from [start] to [end]
     * if [nodesAreGenerated] is false this wil need to be fed existing nodes that stay the same object every time they are found.
     *  If unsure, leave true (only an unknown performance penalty and uses more memory)
     *
     * Grid is an algorithm that will only work in a grid where start and end position are known.
     * It will always work from the node that has the lowest no-obstacle distance to the target,
     * so probably will be more efficient than a complete Dijkstra search
     */
    fun gridRoute(start: GridNode, end: GridNode, nodesAreGenerated: Boolean = true): List<GridNode>?{
        var activeNode: GridNode? = start
        val visitingList = hashSetOf<GridNode>()
        val knownNodes = hashSetOf(start)
        while(activeNode != null && activeNode != end){


            //print("\nlooking at $activeNode, knownNodes has ${knownNodes.size}, visitingList has ${visitingList.size}\n")

            //print("Neighbours: ${activeNode.getNeighbours().map{it}}\n")

            activeNode.getNeighbours().forEach { neighbour ->
                val node = if (nodesAreGenerated)
                    knownNodes.firstOrNull { it == neighbour }
                            ?: neighbour.also { knownNodes.add(it) } // Use the version that is in knownNodes
                else neighbour.also { knownNodes.add(it) } // Use the version that is in knownNodes
                if (node.isUnvisited()) {
                    visitingList.add(node)
                    // Not sure if this part is needed
                    if (node.distanceToStart == 0 || node.distanceToStart > activeNode!!.distanceToStart + 1) {
                        node.distanceToStart = activeNode!!.distanceToStart + 1
                        node.previousNode = activeNode
                    }
                }
            }
            activeNode.visited()
            visitingList.remove(activeNode)
            activeNode = visitingList.minByOrNull { it.distanceToStart + it.getMinimumDistance(end) }
        }

        //Once we are here, searching is done.
        return activeNode?.let{
            val result = arrayListOf(it)
            var parent = it.previousNode
            while (parent != null){
                result.add(parent as GridNode)
                parent = parent.previousNode
            }
            result.reversed()
        }.also{
            if (it == null) println("No results found for $start - $end")
            knownNodes.forEach{
                it.reset()
            }
        }
    }


    /**
     * Get a distance only from [start] to [end]
     * if [nodesAreGenerated] is false this wil need to be fed existing nodes that stay the same object every time they are found.
     * in that case it will also reset the nodes after pathfinding.
     *
     * Grid is an algorithm that will only work in a grid where start and end position are known.
     * It will always work from the node that has the lowest no-obstacle distance to the target,
     * so probably will be more efficient than a complete Dijkstra search
     */
    fun gridDistance(start: GridNode, end: GridNode, nodesAreGenerated: Boolean): Int?{
        var visitingList = setOf(start)
        val knownNodes = hashSetOf(start)
        var counter = 0
        while (end !in visitingList && visitingList.isNotEmpty()){
            knownNodes.addAll(visitingList)
            val newNodes = visitingList.map{
                it.visited()
                it.getNeighbours().filter{n -> n.isUnvisited() } }.flatten().toSet()
            visitingList = newNodes
            counter++
        }
        if (!nodesAreGenerated) knownNodes.forEach { it.reset() }
        return if (visitingList.isNotEmpty()) counter else null
    }


    fun simplifyMaze(maze: List<MazeNode>) {
        maze.filter { !it.deadEnd }.forEach {
            if (it.canBeDead && it.getNeighbours().size == 1) {
                var currentNode = it
                do {
                    currentNode.markDeadEnd()
                    // println("neighbours: ${currentNode.getNeighbours()}")
                    currentNode = currentNode.getNeighbours().first() as MazeNode
                } while (currentNode.canBeDead && currentNode.getNeighbours().size == 1)
            }
        }
    }
}
