package utils.gameOfLife

class MultiThreadableUniverse<T>(universe: Collection<GameOfLifeNode<T>>, threads: Int = 4):  Iterable<Collection<GameOfLifeNode<T>>> {
    val chunks = universe.chunked(universe.size / threads + 1)
    override fun iterator() = object: Iterator<Collection<GameOfLifeNode<T>>>{
        var counter = 0
        override fun hasNext() = counter < chunks.size

        override fun next(): Collection<GameOfLifeNode<T>> =
            chunks[counter++]
    }
}