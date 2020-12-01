package utils.extensions


/**
 * If values in original map are not unique, some will be discarded!
 */
fun <T, R> Map<T,R>.reversed(): Map<R,T> = this.toList().map{it.second to it.first}.toMap()