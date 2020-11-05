package day10

import Solution
import java.io.File

class Day10(private val input: String): Solution() {
    private val elementLines = File("inputs\\day10a.txt").readLines().drop(1) // from http://www.se16.info/js/lands2.htm
    private val elements = elementLines.map{Element.of(it)}
    private val elementsMap = elements.map{
        it.name to it}.toMap()

    fun getElementsInString(s: String): List<Element>{
        //check if input is in fact one element:
        elements.firstOrNull { it.string == s }?.let{
            return listOf(it)
        }
        TODO("Check which elements make up the string if not one")
    }

    private fun buildList(currentElements: List<Element>, iterationsToGo: Int): List<Element> = when(iterationsToGo){
        0-> currentElements
        else -> buildList(currentElements.map{it.evolution.map{n -> elementsMap[n] ?: error("ELEMENT NOT IN MAP - ERROR 1") }}.flatten(), iterationsToGo - 1)
    }

    override fun first() {
        val length = buildList(getElementsInString(input), 40).sumBy { it.size }
        println("With elements: $length")
    }

    override fun second() {
        val length = buildList(getElementsInString(input), 50).sumBy { it.size }
        println("With elements: $length")
    }
}
