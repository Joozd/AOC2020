package utils

//maybe i'll make one with a cutoff later
/**
 * This will return the first index that gets a repeat
 */
fun <T> findRepeat(iterable: Iterable<T>): Int{
    val lines = ArrayList<T>()
    return lines.indexOf(
        iterable.first{ t ->
            (t in lines).also{
                lines.add(t)
            }
        }
    )
}