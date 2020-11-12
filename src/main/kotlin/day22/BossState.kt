package day22

data class BossState(val hp: Int, val dmg: Int, val poisonTime: Int = 0) {

    override fun toString() = "BossState: hp = $hp, dmg = $dmg, poisonTime = $poisonTime"

    fun startTurn(): BossState{
        val newHp = if (poisonTime > 0) hp - POISON_DAMAGE else hp
        return BossState(newHp, dmg, poisonTime.minusOne())
    }

    val dead: Boolean
        get() = hp <= 0
    val alive: Boolean
        get() = hp > 0

    val poisoned: Boolean
        get() = poisonTime > 0

    fun damage(amount: Int): BossState{
        if (amount > hp) println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXded")
        return tick(damage = amount)
    }

    fun poison(): BossState{
        if (poisonTime > 0) error ("already poisoned, this should not happen")
        return tick(poisoned = true)
    }

    fun attack(target: WizardState): Pair<WizardState, BossState> = (target.damage(dmg) to this.copy())


    private fun tick(damage: Int = 0, poisoned: Boolean = false): BossState{
        return BossState(hp = hp - damage, dmg = this.dmg, poisonTime = if(poisoned) DURATION_POISON else poisonTime)
    }

    companion object{
        fun parse(s: List<String>): BossState{
            s.map{it.split(' ').last().toInt()}.let{ vals ->
                return BossState(vals[0], vals[1])
            }
        }
        const val DURATION_POISON = 6

        const val POISON_DAMAGE = 3

        private fun Int.minusOne() = if (this>=1) this-1 else 0
    }
}