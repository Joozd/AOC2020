package day13

import Solution // Contains functions for reading input file and timing how long it takes to get the answers
import utils.extensions.isDigits

class Day13(day: Int): Solution(day) {
    private val arrivalTime by lazy { inputLines.first().toInt()}
    private val busses by lazy { inputLines.last().split (",")}

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
            while (!offsetIsMatchForThisBus(offset, bus, bussesWithOffsets[bus]!!)){
                offset += period
            }
            period *= bus
        }
        return offset
    }

    private fun offsetIsMatchForThisBus(offset: Long, bus: Int, index: Int): Boolean{
        return (offset + index) % bus == 0L
    }
}