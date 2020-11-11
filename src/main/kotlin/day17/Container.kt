package day17

class Container(val size: Int): Comparable<Container>{
    override fun compareTo(other: Container): Int = size - other.size
    override fun toString() = "$size"
}
