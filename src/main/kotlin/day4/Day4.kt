package day4

import Solution
import utils.extensions.splitToWords

class Day4(day: Int): Solution(day) {
    private val passports by lazy { inputLines.joinToString("~").split("~~").map{Passport.of(it)} } // lazy for timing purposes

    override val first: String
        get() = passports.filter {it.valid1}.size.toString()

    //109 too low
    override val second: String
        get() = passports.filter {it.valid2}.size.toString()
}