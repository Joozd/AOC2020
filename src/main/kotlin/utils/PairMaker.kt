package utils

/**
 * Iterates over al pairs in [list]
 * eg. listOf(1,2,3) will become [(1, 2), (1, 3), (2, 3)]
 * If order matters, just use it.second to it.first for every output.
 */
class PairMaker<T>(val list: List<T>): Iterable<Pair<T,T>> {
    constructor(set: Set<T>) : this(set.toList())
    override fun iterator(): Iterator<Pair<T,T>> = object: Iterator<Pair<T,T>>{
        private var firstIndex: Int = 0
        private var secondIndex: Int = 1
        override fun hasNext(): Boolean =
            firstIndex != list.indices.last

        override fun next(): Pair<T,T> = (list[firstIndex] to list[secondIndex]).also{
            setNextIndex()
        }

        private fun setNextIndex(){
            if (secondIndex == list.indices.last) {
                firstIndex++
                secondIndex = firstIndex + 1
            }
            else secondIndex++
        }
    }
}