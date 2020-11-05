package day11

import Solution

class Day11(private val input: String): Solution() {
    private val password = Password(input)

    override fun first() {
        password.next()
        println("Next password is $password")
    }

    override fun second() {
        password.next()
        println("Next password is $password")
    }
}