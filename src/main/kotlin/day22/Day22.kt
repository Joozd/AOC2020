package day22

import Solution // Contains functions for reading input file and timing how long it takes to get the answers
import java.util.*

class Day22(day: Int): Solution(day) {

    override val first: String
        get() = one().toString()
    override val second: String
        get() = two().toString()

    private fun one(): Long {
        val decks = inputGroups.map{g -> LinkedList(g.drop(1).map{it.toInt()})}
        while(decks.none{it.isEmpty()}) {
            val player = decks[PLAYER].removeFirst()
            val crab = decks[CRAB].removeFirst()
            if (player > crab) decks[PLAYER].addAll(listOf(player, crab))
            else decks[CRAB].addAll(listOf(crab, player))
        }
        return getScore(decks.flatten())
    }

    private fun two(): Long {
        val decks = inputGroups.map{g -> g.drop(1).map{it.toInt()}}
        val result = recursiveCombat(decks)
        println(result)
        return getScore(result.flatten())
    }


    private fun getScore(cards: List<Int>): Long = cards.reversed().foldIndexed(0L) { i, acc, v ->
        acc + v*(i+1)
    }

    /**
     * Will play a game of recursive combat and return the result.
     * If won due to an infinite state, will return [[1],[]]
     */
    private fun recursiveCombat(decks: List<List<Int>>): List<List<Int>> {
        val dd = decks.map{ArrayList(it)}
        val knownStates = ArrayList(listOf(decks))
        while(dd.none { it.isEmpty()}){
            val player = dd[PLAYER].removeFirst()
            val crab = dd[CRAB].removeFirst()
            val winner = if (player > dd[PLAYER].size || crab > dd[CRAB].size){
                if (player > crab) PLAYER else CRAB
            }
            else{ // enough cards te recurse!
                if (recursiveCombat(listOf(dd[PLAYER].take(player), dd[CRAB].take(crab))).first().isNotEmpty()) PLAYER else CRAB
            }
            if (winner == PLAYER) dd[PLAYER].addAll(listOf(player, crab))
            else dd[CRAB].addAll(listOf(crab, player))

            dd.map{it.toList()}.let{
                if (it in knownStates) return listOf(listOf(1), emptyList())
                knownStates.add(it)
            }
        }
        return dd
    }

    companion object {
        const val PLAYER = 0
        const val CRAB = 1
    }
}