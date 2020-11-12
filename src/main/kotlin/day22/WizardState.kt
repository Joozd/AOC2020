package day22

data class WizardState(val hp: Int = 50, val mana: Int = 500, private val shielded: Boolean = false,
                       val shieldRemaining: Int = 0, val poisonRemaining: Int = 0, val rechargeRemaining: Int = 0,
                       val spellsCast: List<Int> = emptyList()) {

    override fun toString() = "WizardState: hp = $hp, mana = $mana spellsCast = $spellsCast"


    val totalManaSpent = spellsCast.sum()

    val effectiveHp: Int // amount of damage can take without dying in 1 hit
        get() = hp + if(shieldActive) SHIELD_STRENGTH else 0

    val dead: Boolean
        get() = hp <= 0 || totalManaSpent > 2389
    val alive: Boolean
        get() = hp > 0 && !noMoreSpells

    private val noMoreSpells: Boolean
        get() = availableSpells.isEmpty()


    fun startTurn(): WizardState {
        val shieldedNow = shieldRemaining > 0
        val newMana = if (recharging) mana + RECHARGE_AMOUNT else mana
        return WizardState(hp, newMana, shieldedNow, shieldRemaining - 1, poisonRemaining - 1, rechargeRemaining - 1, spellsCast)
    }

    val availableSpells = listOfNotNull(
            MAGIC_MISSILE,
            DRAIN.takeIf{hp <= 50 }, // only drain if low health. Probably not even then.
            SHIELD.takeIf { shieldRemaining < 1 },
            POISON.takeIf { poisonRemaining < 1 },
            RECHARGE.takeIf { rechargeRemaining < 1 }
    ).filter { it <= mana }

    /**
     * spells
     */

    fun damage(amount: Int): WizardState {
        val newHp = hp - (amount - if (shieldActive) SHIELD_STRENGTH else 0).also{
            if (it < 0) println("wizard hit for $it - $this")
        }
        return this.copy(hp = newHp)
    }

    fun loseOne() = this.copy(hp = hp-1)

    private val magicMissile: (BossState) -> Pair<WizardState, BossState> = { t ->
        tick(MAGIC_MISSILE) to t.damage(MISSILE_DAMAGE)
    }

    private val drain: (BossState) -> Pair<WizardState, BossState> = { t ->
        tick(DRAIN, draining = true) to t.damage(DRAIN_AMOUNT)
    }

    private val shield: (BossState) -> Pair<WizardState, BossState> = { t -> // target unused
        if (shieldRemaining > 0) error ("shield already active this should not happen")
        tick(SHIELD, shieldCast = true) to t
    }

    private val poison: (BossState) -> Pair<WizardState, BossState> = { t ->
        if (poisonRemaining > 0) error ("poison already active this should not happen")
        tick(POISON, poisonCast = true) to t.poison()
    }

    private val recharge: (BossState) -> Pair<WizardState, BossState> = { t ->
        if (rechargeRemaining > 0) error ("recharge already active this should not happen")
        tick(RECHARGE, rechargeCast = true) to t
    }

    val cast: Map<Int, (BossState) -> Pair<WizardState, BossState>> = listOf(
            MAGIC_MISSILE to magicMissile,
            DRAIN to drain,
            SHIELD to shield,
            POISON to poison,
            RECHARGE to recharge
    ).toMap()



    private fun tick(manaSpent: Int, draining: Boolean = false, shieldCast: Boolean = false, poisonCast: Boolean = false, rechargeCast: Boolean = false): WizardState{
        val newMana = mana - manaSpent
        val newHp = hp + if(draining) DRAIN_AMOUNT else 0
        val shield = if (shieldCast) DURATION_SHIELD else shieldRemaining
        val shieldedNow = shielded || shieldCast
        val poison = if (poisonCast) DURATION_POISON else poisonRemaining
        val recharge = if (rechargeCast) DURATION_RECHARGE else rechargeRemaining
        return WizardState(newHp, newMana, shieldedNow, shield, poison, recharge, spellsCast + manaSpent)
    }


    val recharging: Boolean
        get() = rechargeRemaining > 0

    val shieldActive: Boolean
        get() = shieldRemaining > 0


    companion object{
        const val MAGIC_MISSILE = 53
        const val DRAIN = 73
        const val SHIELD = 113
        const val POISON = 173
        const val RECHARGE = 229

        val alwaysAvailableSpells = listOf(MAGIC_MISSILE, DRAIN)

        const val DURATION_SHIELD = 6
        const val DURATION_POISON = 6
        const val DURATION_RECHARGE = 5

        const val MISSILE_DAMAGE = 4
        const val DRAIN_AMOUNT = 2
        const val SHIELD_STRENGTH = 7
        const val RECHARGE_AMOUNT = 101
    }
}