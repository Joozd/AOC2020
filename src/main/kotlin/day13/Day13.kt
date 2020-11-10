package day13

import Solution
import utils.Permutations

@Suppress("MapGetWithNotNullAssertionOperator")
class Day13(private val input: List<String>): Solution() {
    private val pairs: Map<String, Map<String, Int>> by lazy {
        val map = HashMap<String, Map<String, Int>>()
        input.map {l -> l.split(' ')}.let{lines ->
            val names = lines.map{it.first()}.distinct()
            names.forEach{name ->
                val lines1 = lines.filter{it.first() == name}
                val lines2 = lines.filter{it.last().dropLast(1) == name}
                val pairs1: List<Pair<String, Int>> = lines1.map{it.last().dropLast(1) to if (it[2] == "lose") it[3].toInt() * -1 else it[3].toInt()
                }
                val pairs2: List<Pair<String, Int>> = lines2.map{it.first() to if (it[2] == "lose") it[3].toInt() * -1 else it[3].toInt()
                }
                val innerMap: MutableMap<String, Int> = pairs1.toMap().toMutableMap()
                pairs2.forEach{
                    innerMap[it.first] = (innerMap[it.first] ?: error ("YOLO ERROR 1")) + it.second
                }
                map[name] = innerMap
            }
        }
        map
    }

    override fun first() {
        val names = input.map{it.split(' ').first()}.distinct()
        val list = Permutations(names.drop(1))
        val winner = names.take(1) + list.maxByOrNull{
            val ll = names.take(1) + it
            getValueNew(ll, pairs, true)}!!
        println("max happy is ${getValueNew(winner, pairs, true)}")
        println("seating: $winner")
    }


    override fun second() {
        val names = input.map{it.split(' ').first()}.distinct()
        val list = Permutations(names)
        val winner = list.maxByOrNull{getValueNew(it, pairs, false)}!!
        println("max happy is ${getValueNew(winner, pairs, false)}")
        println("seating: $winner")
    }

    private fun getValueNew(names: List<String>, pairs: Map<String, Map<String, Int>>, circle: Boolean = false): Int {
        val nn = if (circle) names + names.take(1) else names
        return (0 until nn.size-1).sumBy{i ->
            //println(nn)
            (pairs[nn[i]]?: error("YOLO 1"))[nn[i+1]]?: error("YOLO 2")
        }
    }
}