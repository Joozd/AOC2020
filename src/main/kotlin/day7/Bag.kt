package day7

import utils.extensions.splitToWords

data class Bag(val name: String, private val contains: List<String>){
    private var containedBags: Map<Bag, Int> = emptyMap()

    fun addBags(allBags: List<Bag>){
        containedBags = contains.map{ containedBag ->
            allBags.first { it.name == containedBag.drop(2) } to                         // `to` makes a pair, in this case Pair(Bag, amount)
                    containedBag.first().toString().toInt()
        }.toMap()                                                                           // transforms a list of Pairs into a Map (what Python would call a dict)
    }

    val containsShinyGold: Boolean
        get() = "shiny gold" in containedBags.keys.map{ it.name } || containedBags.keys.any { it.containsShinyGold }

    val bagsInside: Int
        get() = containedBags.values.sum() + containedBags.keys.sumBy { it.bagsInside * containedBags[it]!! }

    companion object{
        fun of(s: String): Bag{
            val parts = s.dropLast(1).split(", ").map{ it.splitToWords() }     // get rid of the period at the end and split it in to pieces (a list of lists of words)
            val ownName = "${parts[0][0]} ${parts[0][1]}"                                   // eg. drab magenta
            val otherBags = if (parts[0].size == 7) emptyList()                             // if parts[0] has length 7 it contains no other bags
                else parts.map{it.dropLast(1).takeLast(3).joinToString(" ")}  // remove "bag" or "bags", take the three words in front of that (eg. "4 lovely pink")
            return Bag(ownName, otherBags)
        }
    }
}
