package day24

import Solution

class Day24(input: List<String>): Solution() {
    private val presents = input.map{it.toInt()}.sortedDescending()

    /**
     * Find all shortest lists from [list] that add up to [target]
     * BFS ran out of memory, lets go DFS with some rules:
     * DFS will return 1 hit.
     * trying a greedy algorithm:
     * Greedy should work because (max * min) < (middle * middle)
     */
    private fun findShortest(target: Int, list: List<Int>, currentSet: Set<Int> = emptySet(), maxLength: Int): Set<Int>? {
        val sorted = list.sortedDescending()
        val currentSum = currentSet.sum()
        if(currentSum == target) return currentSet
        if (currentSet.size >= maxLength || sorted.isEmpty() || currentSum > target || sorted.take(maxLength - currentSet.size).sum() < target - currentSum) return null
        sorted.forEach { selected ->
            findShortest(target, sorted.filter{i -> i != selected}, currentSet + selected, maxLength)?.let{
                return it
            }
        }
        return null
    }


    private fun findAllShortest(target: Int, list: List<Int>, currentSet: Set<Int> = emptySet(), maxLength: Int): List<Set<Int>> {
        val sorted = list.sortedDescending()
        val currentSum = currentSet.sum()
        if(currentSum == target) return listOf(currentSet)
        if (currentSet.size >= maxLength || sorted.isEmpty() || currentSum > target || sorted.take(maxLength - currentSet.size).sum() < target - currentSum) return emptyList()
        return sorted.map { selected ->
            findAllShortest(target, sorted.filter{i -> i != selected}, currentSet + selected, maxLength)
        }. flatten().distinct()
    }

    // 438478398078 is too high
    // 67601337186 is also too high
    // 405925792351 is wrong.
    // 163845007999 also wong.
    // 11846773891
    override fun first() {
        val weightPerCompartment = presents.sum()/3
        println("Total presents: ${presents.size}")
        println("weight per group: $weightPerCompartment")
        var i = 1
        var result: Set<Int>? = null
        while (result == null){
            result = findShortest(weightPerCompartment, presents, maxLength = i)
            i++
        }
        println(result)

        val results = findAllShortest(weightPerCompartment, presents, maxLength = result!!.size)
        println(results)

        val entanglement: Long? = result?.fold(1L) { acc, i -> acc*i }
        val entanglements: List<Long> = results.map{ it.fold(1L) { acc, i -> acc*i }}
        println( "found ${results.size}  results")
        println("Examples:")
        results.forEach { println(it) }
        println( "Entanglement = $entanglement")
        println( "Entanglements = $entanglements")
        println( "minimum entanglement:\n*************************\n${entanglements.minOrNull()}\n" +
                "*************************\n")
        println("goes for ${results.filter {it.fold(1L) { acc, i -> acc*i } == entanglements.minOrNull()}}")
    }

    override fun second() {
        val weightPerCompartment = presents.sum()/4
        println("Total presents: ${presents.size}")
        println("weight per group: $weightPerCompartment")
        var i = 1
        var result: Set<Int>? = null
        while (result == null){
            result = findShortest(weightPerCompartment, presents, maxLength = i)
            i++
        }
        println(result)

        val results = findAllShortest(weightPerCompartment, presents, maxLength = result!!.size)
        println(results)

        val entanglement: Long? = result?.fold(1L) { acc, i -> acc*i }
        val entanglements: List<Long> = results.map{ it.fold(1L) { acc, i -> acc*i }}
        println( "found ${results.size}  results")
        println("Examples:")
        results.forEach { println(it) }
        println( "Entanglement = $entanglement")
        println( "Entanglements = $entanglements")
        println( "minimum entanglement:\n*************************\n${entanglements.minOrNull()}\n" +
                "*************************\n")
        println("goes for ${results.filter {it.fold(1L) { acc, i -> acc*i } == entanglements.minOrNull()}}")
    }
}
