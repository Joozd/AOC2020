package utils

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Wrapper for CPU-heavy iterables that will spread the work across [threads] threads that should go to different cores.
 * Not worth it for tasks that are not heavy per iteration as the overhead is not zero.
 */

class MultiThreader<T>(private val iterable: Iterable<T>, val threads: Int = 4): Iterable<T>, CoroutineScope {
    init{
        require(threads > 0) { "Cannot work with 0 threads."}
    }

    override fun iterator() = object: Iterator<T>{

        private var index: Int = 0
        private val parentIterator = iterable.iterator()
        private val asyncThread: MutableList<Deferred<T>?> = (0 until threads).map{
            if (parentIterator.hasNext()) async{ parentIterator.next()} else null
        }.toMutableList()

        override fun hasNext(): Boolean =
            asyncThread[index % threads] != null

        override fun next(): T =
            runBlocking { asyncThread[index % threads]!!.await() }.also{
            asyncThread[index % threads] = if (parentIterator.hasNext()) async { parentIterator.next() } else null
        }
    }

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job
}