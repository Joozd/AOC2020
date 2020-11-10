package day16

import Solution

class Day16(val input: List<String>): Solution() {
    private val ticker = "children: 3\n" +
            "cats: 7\n" +
            "samoyeds: 2\n" +
            "pomeranians: 3\n" +
            "akitas: 0\n" +
            "vizslas: 0\n" +
            "goldfish: 5\n" +
            "trees: 3\n" +
            "cars: 2\n" +
            "perfumes: 1"

    private val tickerMap: Map<String, Int> by lazy{
        ticker.split("\n").map{trait ->
            trait.split(": ").let{it[0] to it[1].toInt()}
        }.toMap()
    }

    private val aunts: List<Aunt> by lazy { input.map { Aunt.parse(it)}}

    override fun first() {
        println("It is aunt ${aunts.first{it.traits.all{"${it.key}: ${it.value}" in ticker}}}")
    }

    override fun second() {
        val foundAunt = aunts.first{
            it.traits.all{t ->
                when(t.key){
                    "cats", "trees" -> tickerMap[t.key]!! < t.value
                    "pomeranians", "goldfish" -> tickerMap[t.key]!! > t.value
                    else -> tickerMap[t.key]!! == t.value
                }
            }
        }
        println("No, it was aunt $foundAunt!")
    }
}

/*
In particular, the cats and trees readings indicates that there are greater than that many
(due to the unpredictable nuclear decay of cat dander and tree pollen),
while the pomeranians and goldfish readings indicate that there are fewer than that many
(due to the modial interaction of magnetoreluctance).
 */