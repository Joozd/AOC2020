package day13

import Solution // Contains functions for reading input file and timing how long it takes to get the answers
import utils.extensions.isDigits

class Day13(day: Int): Solution(day) {
    //override val inputLines = test3

    val arrivalTime by lazy { inputLines.first().toInt()}
    val busses by lazy { inputLines.last().split (",")}

    override val first: String
        get() = one().toString()
    override val second: String
        get() = two().toString()

    private fun one() = busses.filter{it.isDigits()}
        .map{it.toInt()}
        .minByOrNull { it - arrivalTime%it}
        ?.let { it * (it-arrivalTime%it)}

    private fun two(): Long {
        //map of bus number to offset
        val bussesWithOffsets = busses.mapIndexed { index, s -> s to index }.filter { it.first.isDigits() }.map { it.first.toInt() to it.second }.toMap()
        val busses = bussesWithOffsets.keys
        var period = 1L //how often this repeats
        var offset = 0L // first occurrence until now where all current buses comply
        busses.sorted().forEach { bus ->
            println("bus: $bus, index: ${bussesWithOffsets[bus]}")
            while (!offsetIsMatchForThisBus(offset, bus, bussesWithOffsets[bus]!!)){
                offset += period
            }
            period *= bus
            println("period: $period, offset: $offset")
        }

        return offset

        //return bussesWithOffsets.toString()
    }

    private fun offsetIsMatchForThisBus(offset: Long, bus: Int, index: Int): Boolean{
        return (offset + index) % bus == 0L
    }

    companion object{
        val test2 = "1000\n17,x,13,19".lines()
        val test3 = "10000\n1789,37,47,1889".lines()
        val test = ("939\n" +
                "7,13,x,x,59,x,31,19").lines()
    }
}