package utils.dijkstra

operator fun Number.compareTo(other: Comparable<Number>): Int = when (this) {

    is Int -> this.compareTo(other)
    is Double -> this.compareTo(other)
    is Float -> this.compareTo(other)
    is Long -> this.compareTo(other)
    is Byte -> this.compareTo(other)
    is Short -> this.compareTo(other)
    else -> error("Cannot compare these types: $this, $other")
}



@Suppress("UNCHECKED_CAST")
operator fun <T: Number> Number.plus(other: Number): T = when (this) {
    is Int -> (this + other.toInt()) as T
    is Double -> this + other.toDouble() as T
    is Float -> this + other.toFloat() as T
    is Long -> this + other.toLong() as T
    is Byte -> this + other.toByte() as T
    is Short -> this + other.toShort() as T
    else -> error("Cannot add these types: $this, $other")
}

@Suppress("UNCHECKED_CAST")
operator fun <T: Number> Number.minus(other: Number): T = when (this) {
    is Int -> (this - other.toInt()) as T
    is Double -> this - other.toDouble() as T
    is Float -> this - other.toFloat() as T
    is Long -> this - other.toLong() as T
    is Byte -> this - other.toByte() as T
    is Short -> this - other.toShort() as T
    else -> error("Cannot subtract these types: $this, $other")
}

fun Int.abs() = if (this < 0 ) this * -1 else this