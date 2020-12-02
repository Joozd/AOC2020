package day2

import utils.extensions.splitToWords

class Password(val first: Int, val second: Int, val policyChar: Char, val password: String) {
    val isValidOne:Boolean
        get() = password.filter{it == policyChar}.length in (first..second)

    val isValidTwo:Boolean
        get() = listOf (password.getOrNull(first - 1), password.getOrNull(second - 1)).filter{it == policyChar}.size == 1

    companion object{
        fun of (s: String): Password{
            val w = s.splitToWords()
            w[0].split('-').let { n ->
                return Password(n[0].toInt(), n[1].toInt(), w[1].first(), w[2])
            }
        }
    }
}