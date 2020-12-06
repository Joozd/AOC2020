package day6

import Solution

class Day6(day: Int): Solution(day) {

    // inputGroups from [Solution]:
    // inputLines.joinToString("~").split("~~").map{it.split("~")}

    override val first: String
        get() = inputGroups.map{it.joinToString("")
            .toSet()
            .size
        }.sum().toString()

    override val second: String
        get() = inputGroups.map{ group ->
            group.joinToString()
                .toList()
                .groupingBy { it }
                .eachCount()
                .filter{it.value == group.size}
                .keys
                .size
        }.sum().toString()
}