package day4

import Solution
import java.security.MessageDigest

/**
 * 1:
 * Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts for all the economically forward-thinking little girls and boys.

To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five zeroes.
The input to the MD5 hash is some secret key (your puzzle input, given below) followed by a number in decimal.

To mine AdventCoins, you must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.

For example:

If your secret key is abcdef, the answer is 609043, because the MD5 hash of abcdef609043 starts with five zeroes (000001dbbfa...), and it is the lowest such number to do so.
If your secret key is pqrstuv, the lowest number it combines with to make an MD5 hash starting with five zeroes is 1048970; that is, the MD5 hash of pqrstuv1048970 looks like 000006136ef....
Your puzzle input is iwrupvqb.
 */

class Day4(private val input: String): Solution() {
    private tailrec fun recursiveSearch(s: String, counter: Int, amount: Int): Int =
        if (checkHashForLeadingZeroes("$s$counter", amount)) counter
        else recursiveSearch(s, counter + 1, amount)

    private fun checkHashForLeadingZeroes(s: String, amount: Int): Boolean {
        val hash = MessageDigest.getInstance("MD5").digest(s.toByteArray(Charsets.UTF_8))
        return if (amount == 5)
            hash[0] == hash[1] && hash[0] == 0.toByte() && hash[2].toInt().shr(4) == 0
        else hash[0] == hash[1] && hash[1] == hash[2] && hash[2] == 0.toByte()
    }

    private var five: Int? = null

    override fun first() =
        recursiveSearch(input, 0, 5).let {
            five = it
            println("5 digits: $it")
        }

    override fun second() =
        recursiveSearch(input, five ?: 0, 6).let {
            println("6 digits: $it")
        }
}


