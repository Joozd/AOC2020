package day22

import Solution
import utils.extensions.toIntCeil

class Day22(private val input: List<String>): Solution() {

    /**
     * Returns mana spent on wizard win, or Int.MAX_VALUE on boss win
     * Will need a bit of AI to not run out of memory.
     * - if can kill now, kill now
     * - Start with poison
     * - if not enough mana to kill boss, recharge should be active
     * - shield only if needed to not die before killing boss
     */
    fun fight(wizard: WizardState, boss: BossState, spell: Int): Int{
        var round = 0
        var lowestMana = Int.MAX_VALUE
        var possibilities = listOf(wizard.cast[spell]!!(boss)).map{ it.first.startTurn() to it.second.startTurn() }
        println(" CP1 - Possibilities: $possibilities")

        while (possibilities.isNotEmpty()){
            println("round ${++round}. ")
            println("${possibilities.size} possibilities.")
            //get lowest mana for complete fight
            possibilities.filter{it.second.dead}.minByOrNull { it.first.totalManaSpent }?.first?.let{
                lowestMana = it.totalManaSpent
            }
            //continue other fights that have spent less mana than that with boss'turn and remove dead wizards
            possibilities = possibilities.filter{it.first.totalManaSpent < lowestMana}.map{
                it.second.attack(it.first)
            }       .filter{it.first.alive}                               // remove dead wizards
                    .map{it.first.startTurn() to it.second.startTurn()}     // apply effects like poison
            //get lowest mana for complete fight
            possibilities.filter{it.second.dead}.minByOrNull { it.first.totalManaSpent }?.first?.let{
                lowestMana = it.totalManaSpent
            }


            // now, make a new batch of possibilities:
            possibilities = possibilities
                    .map { wb ->
                        val w = wb.first.loseOne()
                        val b = wb.second
                        when {

                            b.hp < 4 || (b.hp < 7 && b.poisoned && w.effectiveHp > b.dmg) -> listOf(w.cast[WizardState.MAGIC_MISSILE]!!(b)).map{ it.first.startTurn() to it.second.startTurn() }
                            !w.recharging && notEnoughManaToKill(w.mana, b) == true -> listOf(w.cast[WizardState.RECHARGE]!!(b))
                            else -> wb.first.availableSpells.map {
                                wb.first.cast[it]!!(wb.second)
                            }.map{ it.first.startTurn() to it.second.startTurn() }
                        }
                    }.flatten()
        }
        return lowestMana
    }



    fun fight2(wizard: WizardState, boss: BossState, spell: Int): Int{
        var lowestMana = Int.MAX_VALUE
        var possibilities = listOf(wizard.loseOne().cast[spell]!!(boss)).map{ it.first.startTurn() to it.second.startTurn() }

        while (possibilities.isNotEmpty()){
            //get lowest mana for complete fight
            possibilities.filter{it.second.dead}.minByOrNull { it.first.totalManaSpent }?.first?.let{
                lowestMana = it.totalManaSpent
            }
            //continue other fights that have spent less mana than that with boss'turn and remove dead wizards
            possibilities = possibilities.filter{it.first.totalManaSpent < lowestMana}.map{
                it.second.attack(it.first)
            }       .filter{it.first.alive}                               // remove dead wizards
                    .map{it.first.startTurn() to it.second.startTurn()}     // apply effects like poison
            //get lowest mana for complete fight
            possibilities.filter{it.second.dead}.minByOrNull { it.first.totalManaSpent }?.first?.let{
                lowestMana = it.totalManaSpent
            }


            // now, make a new batch of possibilities:
            possibilities = possibilities
                    .map{wb -> wb.first.loseOne() to wb.second}
                    .filter { wb -> wb.first.alive }
                    .map { wb ->
                val w = wb.first.loseOne()
                val b = wb.second
                when {

                    b.hp < 4 || (b.hp < 7 && b.poisoned && w.effectiveHp > b.dmg) -> listOf(w.cast[WizardState.MAGIC_MISSILE]!!(b)).map{ it.first.startTurn() to it.second.startTurn() }
                    !w.recharging && notEnoughManaToKill(w.mana, b) == true -> listOf(w.cast[WizardState.RECHARGE]!!(b))
                    else -> wb.first.availableSpells.map {
                        wb.first.cast[it]!!(wb.second)
                    }.map{ it.first.startTurn() to it.second.startTurn() }
                }
            }.flatten()
        }
        return lowestMana
    }

    /**
     * Returns true if more mana is needed to kil [b] than [mana]
     */
    private fun notEnoughManaToKill(mana: Int, b: BossState): Boolean? = when{
            b.hp in (-1000..4) -> WizardState.MAGIC_MISSILE
            b.hp in (-1000..7) && b.poisoned -> WizardState.MAGIC_MISSILE
            b.hp in (5..8) -> WizardState.MAGIC_MISSILE*2
            b.hp in (5..11) && b.poisoned -> WizardState.MAGIC_MISSILE*2
            b.hp in (5..14) && b.poisonTime >= 2 -> WizardState.MAGIC_MISSILE*2
            b.hp in (9..12 + maxOf(3*b.poisonTime, 9)) -> WizardState.MAGIC_MISSILE * 3
            b.hp in (13..16 + maxOf(3*b.poisonTime, 12)) -> WizardState.MAGIC_MISSILE * 4
            b.hp in (17..20 + maxOf(3*b.poisonTime, 15)) -> WizardState.MAGIC_MISSILE * 5
            b.hp in (21..24 + maxOf(3*b.poisonTime, 18)) -> WizardState.MAGIC_MISSILE * 6
            else  -> {
                val damageUntilEndOfPoison = 7*b.poisonTime
                if (damageUntilEndOfPoison > b.hp) (b.hp/7.0).toIntCeil()*WizardState.MAGIC_MISSILE else null
            }
        }?.let{
        it > mana
    }


override fun first() {
    val wizard = WizardState()
    val boss = BossState.parse(input).also{
        println("created boss: $it")
    }
    //Will need mana anyhow. If we die, it will start with shield.
    val result = fight(wizard, boss, WizardState.RECHARGE)

    println("minimum mana: $result")
}


    override fun second() {
        val wizard = WizardState()
        val boss = BossState.parse(input).also{
            println("created boss: $it")
        }
        //Will Will die on any other start than SHIELD
        val result = fight2(wizard, boss, WizardState.SHIELD)

            println("minimum mana: $result")
    }

}