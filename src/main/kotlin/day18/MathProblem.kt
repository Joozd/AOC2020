package day18

import utils.extensions.getDigits
import utils.extensions.splitToWords
import java.util.*
import kotlin.collections.ArrayList

class MathProblem(val subProblems: List<MathProblem>? = null, val operators: List<Char>? = null, val singleValue: Long? = null, val string: String? = null) {
    init{
        if (subProblems != null) require (subProblems.isNotEmpty() && operators?.size == subProblems.size -1)
    }
    private val calculatedValue: Long? = subProblems?.let { p ->
        p.drop(1).foldIndexed(p.first().value) { index, acc, mp ->
            when (operators!![index]) {
                MULTIPLY -> acc * mp.value
                ADD -> acc + mp.value
                else -> error ("ONLY ADD OR MULTiPLY")
            }
        }
    }

    val value: Long = singleValue ?: calculatedValue ?: error("NO SINGLE OR CALCULATED VALUE")


    companion object{
        const val MULTIPLY = '*'
        const val ADD = '+'

        fun of(s: String): MathProblem = recursiveOf(s).first

        private fun recursiveOf(s: String): Pair<MathProblem, String> {
            val ww = LinkedList(s.splitToWords())
            if (ww.size == 1) return MathProblem(singleValue = ww.first().getDigits().toLong()) to ""
            val subProblems = ArrayList<MathProblem>()
            val operators = ArrayList<Char>()
            while (ww.isNotEmpty()){
                if(ww.first().endsWith(')') || ww.size == 1) {
                    subProblems += MathProblem(singleValue = ww.removeFirst().getDigits().toLong())
                    return (MathProblem(subProblems, operators, string = s) to ww.joinToString(" "))
                }
                if (ww.first().startsWith('(')){
                    recursiveOf(ww.joinToString(" ").drop(1)).let{
                        subProblems.add(it.first)
                        ww.clear()
                        ww.addAll(it.second.splitToWords())
                        ww.removeFirstOrNull()?.firstOrNull()?.let { op -> operators.add(op) }
                    }
                }
                else {
                    subProblems.add(MathProblem(singleValue = ww.removeFirst().toLong()))
                    operators.add(ww.removeFirst().first())
                }
            }
            return MathProblem(subProblems, operators, string = s) to ""
        }
    }
}