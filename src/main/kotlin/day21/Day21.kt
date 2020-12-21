package day21

import Solution // Contains functions for reading input file and timing how long it takes to get the answers
import utils.extensions.multiIntersect
import utils.extensions.splitToWords

class Day21(day: Int): Solution(day) {
    private val products by lazy{
        inputLines.map{ l ->
            Product(
                l.take(l.indexOf('(')-1).splitToWords(),
                l.drop(l.indexOf('(')).filter{it !in "(,)"}.splitToWords().drop(1)
            )
        }
    }
    private val parsedData by lazy{
        val data = HashMap<String, MutableList<List<String>>>()
        products.forEach{ p ->
            p.allergens.forEach{ a ->
                if (data[a] == null) data[a] = ArrayList()
                data[a]!!.add(p.ingredients)
            }
        }
        data.mapValues { it.value.multiIntersect().toMutableList() }
    }

    override val first: String
        get() = products.map { it.ingredients }.flatten().filter { it !in parsedData.values.flatten() }.size.toString()
    override val second: String
        get() = two()

    private fun two(): String {
        repeat(parsedData.keys.size) {
            parsedData.values.mapNotNull { if (it.size == 1) it.first() else null }.let { known ->
                parsedData.mapValues { entry ->
                    if (entry.value.size != 1) entry.value.removeAll(known)
                }
            }
        }
        return parsedData.toList().sortedBy { it.first }.joinToString(",") { it.second.first() }
    }

    // I have learned my lesson to never ever use a Pair again if not for construction a Map
    private class Product(val ingredients: List<String>, val allergens: List<String>)
}