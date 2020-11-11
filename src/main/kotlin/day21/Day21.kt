package day21

import Solution

class Day21(private val input: List<String>, itemsInput: List<String>): Solution() {
    private fun generateBoss() = Character.parse(input, "Boss")

    private val weapons = itemsInput.slice(1..5).map{ Item.parse(it, Item.WEAPON) }
    private val armor = itemsInput.slice(8..12).map{ Item.parse(it, Item.WEAPON) } + Item.NONE
    private val rings = itemsInput.slice(15..20).map{ Item.parse(it, Item.WEAPON, isRing = true) } + Item.NONE + Item.NONE

    fun getAllPossibleCharacters(): List<Character> =
        weapons.map { w ->
            armor.map { a ->
                rings.map { r1 ->
                    rings.filter{it != r1}.map { r2 ->
                        Character(name = "Player", items = listOf(w,a,r1,r2))
                    }
            }.flatten()
        }.flatten()
    }.flatten()

    //true if player wins
    fun battle(player: Character, boss: Character, verbose: Boolean = false): Boolean{
        if(verbose){
            println("player:\ndmg:\t${player.damage}\narmor:\t${player.armor}\n")
            println("boss:\ndmg:\t${boss.damage}\narmor:\t${boss.armor}\n")
        }
        while (player.alive && boss.alive){
            boss.hit(player.damage, verbose)
            if (boss.dead) return true.also{
                if(verbose) println("Fight is over! Player won!\n\n")
            }
            player.hit(boss.damage, verbose)
            if(player.dead) return false.also{
                if(verbose) println("Fight is over! Boss won!\n\n")
            }
        }
        error("The while loop should not have quit without a return")
    }

    override fun first() {
        val allChars = getAllPossibleCharacters().sortedBy{ it.itemsPrice }
        val cheapest = allChars.first { battle(it, generateBoss())}
        println("cheapest outfit is:")
        cheapest.items.forEach { println(it) }
        println("for a total cost of ${cheapest.itemsPrice}")
        println("After this heroic fight, our hero has ${cheapest.hitpoints} hp left!")
    }

    override fun second() {
        val allChars = getAllPossibleCharacters().sortedBy{ it.itemsPrice }.reversed()
        val expensivest = allChars.first { !battle(it, generateBoss())}
        println("Most expensive losing outfit is:")
        expensivest.items.forEach { println(it) }
        println("for a total cost of ${expensivest.itemsPrice}")
        println("After this heroic fight, our hero has ${expensivest.hitpoints} hp left :(")
    }
}