package utils.extensions

fun <T> List<T>.getOrNull(index: Int): T? = if (index in indices) get(index) else null