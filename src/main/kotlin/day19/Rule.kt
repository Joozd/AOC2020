package day19

import utils.extensions.splitToWords

/**
 * @param allRules: Map of all rules.
 * @param subRules: Rules referenced by this Rule, as a List of Lists of Ints. They reference to [allRules].
 * @param hardValue: 'a' or 'b' (or another letter bot they are not in input) if this rule is just a letter.
 */
class Rule(val allRules: Map<Int, Rule>, val subRules: List<List<Int>>? = null, val hardValue: Char? = null) {
    fun allPossibilitiesForThisRule(): List<String> =
        subRules?.map{ rules ->
            allRules[rules.first()]!!.allPossibilitiesForThisRule().map{ one ->
                rules.getOrNull(1)?.let {
                    allRules[it]!!.allPossibilitiesForThisRule().map { two -> one + two }
                }?: listOf(one)
            }.flatten()
        }?.flatten() ?: listOf(hardValue.toString())

    fun isValidFor(s: String, q2: Boolean): Boolean = subRules?.any { rules ->
        if (q2 && this in specialRules){
            return isValidSpecial(s)
        }
        if (rules.size == 1) allRules[rules.first()]!!.isValidFor(s, q2)
        else {
            if (rules.last() in finalRules!!){
                (1 until s.length).any { splitPoint ->
                    allRules[rules.last()]!!.isValidFor(s.drop(splitPoint), q2) && allRules[rules.first()]!!.isValidFor(s.take(splitPoint), q2)
                }
            }
            else {
                (1 until s.length).any { splitPoint ->
                    allRules[rules.first()]!!.isValidFor(s.take(splitPoint), q2) && allRules[rules.last()]!!.isValidFor(s.drop(splitPoint), q2)
                }
            }
        }

    } ?: (s.length == 1 && s.first() == hardValue)


    private fun isValidSpecial(s: String): Boolean = when {
        this === allRules[8] -> s in FORTY_TWO || eight(s)
        this === allRules[11] -> eleven(s)
        else -> error("TRIED TO RUN SPECIAL ON A NON_SPECIAL RULE")
    }

    private fun eight(s: String): Boolean =
        if (FORTY_TWO.any {it == s}) true
        else FORTY_TWO.any { start ->  if (s.startsWith(start)) eight(s.drop(start.length)) else false }


    private fun eleven(s: String): Boolean{
        if (FORTY_TWO.none { s.startsWith(it)} || THIRTY_ONE.none { s.endsWith(it)}) return false
        if (FORTY_TWO.any{ st ->
                s.startsWith(st) &&
                THIRTY_ONE.any { s.drop(st.length) == it }
            }
        ) return true
        //if we get here, this starts with a 42 and ends with a 31 but there is more in between
        return FORTY_TWO.mapNotNull { start ->
            if (s.startsWith(start)) s.drop(start.length) else null
        }.any{ shortenedS ->
            THIRTY_ONE.any { end ->  if (shortenedS.endsWith(end)) eleven(shortenedS.dropLast(end.length)) else false }
        }
    }
    companion object{
        fun parseToMap (allRules: MutableMap<Int, Rule>, s: String){
            val number =s.take(s.indexOf(':')).toInt()
            if ('\"' in s) allRules[number] = Rule(allRules, hardValue = s.dropLast(1).last())
            else {
                s.drop(s.indexOf(':') + 2).split(" | ").let{
                    val subRules = it.map{it.splitToWords().map{it.toInt()}}
                    allRules[number] = Rule(allRules, subRules = subRules)
                }
            }
        }
        var finalRules: Set<Int>? = null
        var specialRules: List<Rule> = emptyList()

        var FORTY_TWO: List<String> = emptyList()
        var THIRTY_ONE: List<String> = emptyList()
    }
}