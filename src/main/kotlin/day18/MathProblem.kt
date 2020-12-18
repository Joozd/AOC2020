package day18

import utils.extensions.getDigits
import utils.extensions.splitToWords
import java.util.*
import kotlin.collections.ArrayList

class MathProblem(private val subProblems: List<MathProblem>? = null, private val operators: List<Char>? = null, val singleValue: Long? = null, val string: String? = null) {
    init{
        if (subProblems != null) require (subProblems.isNotEmpty() && operators?.size == subProblems.size -1)
    }
    private val calculatedValue: Long? = subProblems?.let { p ->
        p.drop(1).foldIndexed(p.first().value) { index, acc, mp ->
            when (operators!![index]) {
                MULTIPLY -> acc * mp.value
                ADD -> acc + mp.value
                else -> error ("ONLY ADD OR MULTiPLY (got ${operators[index]})")
            }
        }
    }

    private fun calculateValue2(): Long {
        val ops = LinkedList(operators!!)
        val values = LinkedList(subProblems!!)
        while (ADD in ops){
            val index = ops.indexOf(ADD)
            values[index] = MathProblem(singleValue = (values[index].value2 + values[index+1].value2))
            values.removeAt(index+1)
            ops.removeAt(index)
        }
        return values.fold(1L) { acc, mp -> acc * mp.value2}
    }

    val value: Long = singleValue ?: calculatedValue ?: error("NO SINGLE OR CALCULATED VALUE")

    val value2: Long = singleValue ?: calculateValue2()

    companion object{
        const val MULTIPLY = '*'
        const val ADD = '+'

        fun of(s: String): MathProblem = recursiveOf(s).first

        private fun recursiveOf(s: String): Pair<MathProblem, String> {
            val ww = LinkedList(s.splitToWords())
            if (ww.size == 1) return (MathProblem(singleValue = ww.first().getDigits().toLong()) to "")
            val subProblems = ArrayList<MathProblem>()
            val operators = ArrayList<Char>()
            while (ww.isNotEmpty()){
                if(ww.first().endsWith(')') || ww.size == 1) {
                    val extraClosingBrackets = ww.first().filter{it == ')'}.drop(1).takeIf { it.isNotEmpty() }?.let {"$it "}?: ""
                    subProblems += MathProblem(singleValue = ww.removeFirst().getDigits().toLong())
                    return MathProblem(subProblems, operators, string = s) to extraClosingBrackets + ww.joinToString(" ")
                }
                if (ww.first().startsWith('(')){
                    recursiveOf(ww.joinToString(" ").drop(1)).let{
                        subProblems.add(it.first)
                        ww.clear()
                        ww.addAll(it.second.splitToWords())
                        if(ww.first().firstOrNull() == ')'){
                            val extraClosingBrackets = ww.removeFirst().drop(1).takeIf { it.isNotEmpty() }?.let {"$it "}?: ""
                            return MathProblem(subProblems, operators, string = s) to extraClosingBrackets + ww.joinToString(" ")
                        }
                        ww.removeFirstOrNull()?.firstOrNull()?.let { op -> operators.add(op) }
                    }
                }
                else {
                    subProblems.add(MathProblem(singleValue = ww.removeFirst().toLong()))
                    operators.add(ww.removeFirst().first())
                }
            }
            return (MathProblem(subProblems, operators, string = s) to "")
        }
    }
}