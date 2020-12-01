package utils

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AsyncLookForwardCache<T>(override val size: Int, private val cores: Int = 4, private val function: (Int) -> T): Iterable<T>, Collection<T>, CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default

    private val buffer = ArrayList<T>(size)
    //fill first [size] fields
    init{
        buffer.addAll(runBlocking {
            (0 until size).map {
                calculateNextAsync(it)
            }.awaitAll()
        })
    }

    private var currentIndex = 0
    private val start
        get() = currentIndex%size

    private val displacedList
        get() = buffer.drop(start) + buffer.take(start)

    private fun calculateNextAsync(index: Int) = async{
        function(index)
    }

    //private var prepareNext =  calculateNextAsync(currentIndex+size+1)
    //private var nextNext =  calculateNextAsync(currentIndex+size+2)
    private val prepare = (1 .. cores).map{
        calculateNextAsync(currentIndex+size+it)
    }.toMutableList()



    fun next(): T{
        val first = buffer[start]
        // val nextNextNext = calculateNextAsync(currentIndex+size+3)

        buffer[start] = runBlocking { prepare[currentIndex%cores].await() }
        prepare[currentIndex%cores] = calculateNextAsync(currentIndex + size + cores)
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