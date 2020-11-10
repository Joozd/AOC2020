package day15

data class Ingredient(val name: String, val capacity: Int, val durability: Int, val flavor: Int, val texture: Int, val calories: Int) {
    override fun toString(): String = "$name: $capacity, $durability, $flavor, $texture, $calories"


    companion object{
        fun parse(s: String): Ingredient{
            s.filter{it !in ",:"}.split(' ').let{ words ->
                val name = words[0]
                val capacity = words[2].toInt()
                val durability = words[4].toInt()
                val flavor = words[6].toInt()
                val texture = words[8].toInt()
                val calories = words[10].toInt()
                return Ingredient(name, capacity, durability, flavor, texture, calories)
            }
        }
    }
}
//  0           1  2    3        4    5     6   7      8   9       10
// Sugar: capacity 0, durability 0, flavor -2, texture 2, calories 1