package day17

import Solution

class Day17(private val input: List<String>): Solution() {
    private val containers = input.map{Container(it.toInt())}.sorted().toSet()

    private var resultOne: List<Set<Container>>? = null

    /**
     * Pretty sure this is far from optimal, I get like one million results which are mostly duplicates (answer is 654)
     * Works though.
     */
    private fun fillContainers(remainingContainers: Set<Container>, remainingLitres: Int, currentList: List<Container> = emptyList()): List<Set<Container>>{
        val candidates = remainingContainers.filter{it.size <= remainingLitres}
        if (candidates.isEmpty())
            return emptyList()
        val solutions = candidates.filter { it.size == remainingLitres }
        val result = solutions.map{(currentList + it).toSet()}
        val remaining = candidates.filter { it !in solutions }
        val next = remaining.map{c ->
            val rest = remaining.filter{ it != c }.toSet()
            fillContainers(rest, remainingLitres-c.size, currentList + c)}.flatten()
        return result + next
    }



    override fun first() {
        println("We have ${containers.distinct().size} containers: $containers")
        resultOne = fillContainers(containers, 150).distinct()
        println("Filled ${resultOne?.size} combinations!")
    }

    override fun second() {
        val least = resultOne!!.minByOrNull { it.size }!!.size
        println("Least amount is $least which occurs in ${resultOne!!.filter { it.size == least }.size} combinations")
    }
}