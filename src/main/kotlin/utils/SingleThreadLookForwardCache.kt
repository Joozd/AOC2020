package utils

class SingleThreadLookForwardCache<T>(override val size: Int, private val function: (Int) -> T): Iterable<T>, Collection<T> {
    private val buffer = ArrayList<T>(size)
    //fill first [size] fields
    init{
        repeat(size) {
            buffer.add(function(it))
        }
    }
    private var currentIndex = 0
    private val start
            get() = currentIndex%size

    private val displacedList
        get() = buffer.drop(start) + buffer.take(start)

    fun next(): T{
        val first = buffer[start]
        buffer[start] = function(currentIndex+size+1)
        currentIndex++
        return first
    }

    fun first() = buffer[start]

    fun last() = buffer[(start-1+size)%size]

    val index: Int
        get() = currentIndex

    /**
     * Get position relative to current result (steps into the future)
     */
    fun indexOf(item: T) = displacedList.indexOf(item)

    fun originalIndexOf(item: T) = indexOf(item) + currentIndex

    /**
     * get value at [index] steps into the future
     */
    operator fun get(index: Int)= displacedList[index]

    override fun iterator(): Iterator<T> = displacedList.listIterator()
    override fun contains(element: T): Boolean = buffer.contains(element)

    override fun containsAll(elements: Collection<T>) = buffer.containsAll(elements)

    override fun isEmpty(): Boolean = buffer.isEmpty()
}