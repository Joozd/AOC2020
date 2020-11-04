package day5

import Solution


/**
 * Santa needs help figuring out which strings in his text file are naughty or nice.

A nice string is one with all of the following properties:

It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
For example:

ugknbfddgicrmopn is nice because it has at least three vowels (u...i...o...), a double letter (...dd...), and none of the disallowed substrings.
aaa is nice because it has at least three vowels and a double letter, even though the letters used by different rules overlap.
jchzalrnumimnmhp is naughty because it has no double letter.
haegwjzuvuyypxyu is naughty because it contains the string xy.
dvszwmarrgswjxmb is naughty because it contains only one vowel.
How many strings are nice?
 */
class Day5(private val input: List<String>): Solution() {
    // true is string is naughty
    private fun checkNaughty(s: String): Boolean = naughtyStrings.any{ it in s}

    private fun hasEnoughVowels(s: String) = s.filter{ it in "aeiou"}.length >= 3

    private fun hasRepeating(s: String): Boolean {
        s.forEach {
            if ("$it$it" in s) return true
        }
        return false
    }

    private fun checkTwoPair(s: String): Boolean{
        s.forEachIndexed { i, c ->
            if (i > s.length -2) return false
            val hits = "$c${s[i+1]}".toRegex().findAll(s).toList().size
            if (hits >=2) return true
        }
        return false
    }

    private fun checkSkipOne(s: String): Boolean {
        val longerstring = "$s!#%" // for index errors, '!#%'not used in strings
        s.forEachIndexed {i, c ->
            if (longerstring[i+2] == c) return true
        }
        return false
    }


    private fun String.checkFirst(): Boolean = !checkNaughty(this) && hasEnoughVowels(this) && hasRepeating(this)

    private fun String.checkSecond(): Boolean = checkTwoPair(this) && checkSkipOne(this)

    override fun first() {
        println("1: There are ${input.count { it.checkFirst() }} nice strings")
    }

    override fun second() {
        println("2: There are ${input.count { it.checkSecond() }} nice strings")
    }

    companion object{
        val naughtyStrings = listOf("ab", "cd", "pq", "xy")
    }

}