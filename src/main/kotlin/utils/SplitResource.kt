package utils

/**
 * Divide [resourcesToDivide] resources over [receivers]
 * [minimum] and [maximum] apply to each receiver (not required)
 * eg divide 10 over listOf(a,b,c) with minimum 1 will iterate over:
 * 1,1,8
 * 1,2,7
 * 1,3,6
 * (...)
 * 2,1,7
 * 2,2,6
 * (...)
 * 8,1,1
 * TODO: [maximum] is not implemented
 */
class SplitResource<T>(private val receivers: Set<T>, private val resourcesToDivide: Int, private val minimum: Int = 0, private val maximum: Int = Int.MAX_VALUE): Iterable<Map<T, Int>> {
    private val fixedAssets = receivers.size * minimum
    private val max = resourcesToDivide - fixedAssets + minimum
    var done: Boolean = receivers.isEmpty() || fixedAssets > resourcesToDivide || maximum.toLong() * receivers.size < resourcesToDivide // if it has not enough [resourcesToDivide] to fill all minimums, or too many to fit withing maximums, it will be empty
    private var array = IntArray(receivers.size){minimum}.apply{
        this[lastIndex] += resourcesToDivide - sum()
    }

    override fun iterator(): Iterator<Map<T, Int>> = object: Iterator<Map<T,Int>>{
        /**
         * iterates over possible distributions.
         * @return true if no more iterations possible
         */
        private fun iterate(): Boolean{
            if (array[array.lastIndex] > minimum){
                array[array.lastIndex-1]++
                array[array.lastIndex]--
                return false
            }
            else{
                array[array.lastIndex-2]++
                array[array.lastIndex-1] = minimum
                for (i in (array.size-3) downTo 0){
                    val m = resourcesToDivide - array.take(i).sum() - (array.size - i -1) * minimum
                    if (array[i] > m){
                        if (i == 0) return true
                        else {
                            array[i - 1]++
                            (i until array.size).forEach{
                                array[it] = minimum
                            }
                        }
                    }

                }
                array[array.lastIndex] += resourcesToDivide - array.sum()
                return false
            }
        }

        override fun hasNext(): Boolean = !done

        override fun next(): Map<T, Int> {
            if (receivers.size == 1) return listOf(receivers.first() to resourcesToDivide).toMap() .also{
                done = true
            }
            if (receivers.size == 2){
                return receivers.mapIndexed{i, r -> r to array[i]}.toMap().also{
                    array[0]++
                    array[1]--
                    if (array[0] > max) done = true
                }
            }
            return receivers.mapIndexed { i, t -> t to array[i]}.toMap().also{
                done = iterate()
            }
        }

    }
}