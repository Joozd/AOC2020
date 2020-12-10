package day9

import Solution // Contains functions for reading input file and timing how long it takes to get the answers
import utils.PairMaker

/**
 * Got to reuse my PairMaker from day 1!
 * I had to look up "contiguous" in a dictionary.
 * Maybe should have done that before setting up a multithreading BFS and getting hundreds of results.
 */
class Day9(day: Int): Solution(day) {
    private val numbers = inputLines.map{it.toLong()}
    private var answer1: Long = 0

    override val first: String
        get() = one().toString()
    override val second: String
        get() = two().toString()

    private fun one(): Long {
        val list =ArrayDeque(numbers.take(25))
        var index = 25
        while (PairMaker(list.toSet()).any {it.first + it.second == numbers[index]}){
            list.removeFirst()
            list.add(numbers[index++])
        }
        return numbers[index].also{
            answer1 = it
        }
    }

    private fun two(): Long {
        val interestingList = numbers.take(numbers.indexOf(answer1)).filter {it < answer1}.reversed()
        var pointer = 0
        while (true){
            var length = 1
            while (interestingList.subList(pointer, pointer+length).sum() < answer1){ length++ }
            interestingList.subList(pointer, pointer+length).let{
                if (it.sum() == answer1) return it.minOrNull()!! + it.maxOrNull()!!
            }
            pointer++
        }
    }
}