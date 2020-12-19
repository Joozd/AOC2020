package utils.extensions

fun <T> List<T>.indicesOf(element: T): List<Int> = mapIndexedNotNull { index, t -> if (t == element) index else null }
