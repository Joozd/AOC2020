package day21

class Character(val name: String, initialHp: Int = 100, private val baseDamage: Int = 0, private val baseArmor: Int = 0, val items: List<Item> = emptyList()) {
    private var hp = initialHp

    val hitpoints: Int
        get() = hp

    val damage: Int
        get() = baseDamage + items.sumBy{ it.damage}

    val armor: Int
        get() = baseArmor + items.sumBy{ it.armor}

    fun hit(dmg: Int, verbose: Boolean = false){
        val hit = dmg.minusFloored(armor)
        if (verbose){
            println("$name gets hit for $hit dmg! hp left: $hp")
        }
        hp -= hit
    }

    val dead: Boolean
        get() = hp <= 0

    val alive: Boolean
        get() = hp > 0

    val itemsPrice = items.sumBy { it.cost }



    private fun Int.minusFloored(other: Int, floor: Int = 1) = (this - other).let{ if (it < floor) floor else it }

    companion object{
        fun parse(s: List<String>, name: String = "Unnamed"): Character{
            s.map{it.split(' ').last().toInt()}.let{
                return Character(name, it[0], it[1], it[2])
            }
        }
    }
}