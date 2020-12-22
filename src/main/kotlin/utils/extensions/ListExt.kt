package utils.extensions

fun <T> List<T>.indicesOf(element: T): List<Int> = mapIndexedNotNull { index, t -> if (t == element) index else null }

/**
 * Slice a window out of a two-domensional list
 */
fun <T> List<List<T>>.window(left: Int, top: Int, right: Int, bottom: Int): List<List<T>> = slice(top..bottom).map{it.slice(left..right)}

fun <T> List<List<T>>.window(startXY: Pair<Int, Int>, endXY: Pair<Int, Int>) = window(startXY.first, startXY.second, endXY.first, endXY.second)



