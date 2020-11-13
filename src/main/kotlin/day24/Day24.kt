package day24

import Solution

class Day24(input: List<String>): Solution() {
    private val presents = input.map{it.toInt()}
    override fun first() {
        println("Total weight: ${presents.sum()}")
        println("weight per group: ${presents.sum()/3}")
    }
}