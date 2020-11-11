package day21

class Item(val type: String, val name: String, val cost: Int, val damage: Int, val armor: Int) {
    override fun toString() = "Item: $name"
    companion object{
        fun parse(s: String, type: String, isRing: Boolean = false): Item{
            if (!isRing) {
                s.split(' ').filterNot { it.isEmpty() }.let {
                    val name = it[0]
                    val cost = it[1].toInt()
                    val damage = it[2].toInt()
                    val armor = it[3].toInt()
                    return Item(type, name, cost, damage, armor)
                }
            } else {
                s.split(' ').filterNot { it.isEmpty() }.let {
                    val name = "${it[0]} ${it[1]}"
                    val cost = it[2].toInt()
                    val damage = it[3].toInt()
                    val armor = it[4].toInt()
                    return Item(type, name, cost, damage, armor)
                }
            }
        }
        const val RING = "RING"
        const val WEAPON = "WEAPON"
        const val ARMOR = "ARMOR"
        val NONE = Item("NONE", "Empty slot", 0, 0, 0)
    }
}