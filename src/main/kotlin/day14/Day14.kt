package day14

import Solution

class Day14(input: List<String>): Solution() {
    private val rrr = input.map{Reindeer.parse(it)}
    override fun first() {
        repeat(REPEATS){
            rrr.forEach{it.run()}
            val d = rrr.maxByOrNull { it.distance }!!.distance
            rrr.forEach{
                if (it.distance == d) it.point()
            }
        }
        val winner = rrr.maxByOrNull{it.distance}
        println("winner = $winner with ${winner?.distance} km!")
    }

    override fun second() {
        val most = rrr.maxByOrNull{it.score}
        println("Most points: ${most?.name} with ${most?.score} points.")
    }

    companion object{
        const val REPEATS = 2503
    }
}