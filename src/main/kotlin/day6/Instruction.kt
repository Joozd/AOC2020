package day6

import shared.Coordinate

class Instruction(val operation: Int, val bottomLeft: Coordinate, val topRight: Coordinate) {
    companion object{
        const val TURN_ON = 1
        const val TURN_OFF = 2
        const val TOGGLE = 3

        fun parse(s: String): Instruction{
            /**
             * parse an Instruction from a string:
             * toggle 478,7 through 573,148
             * turn on 917,936 through 928,959
             * turn off 580,170 through 963,206
             */
            val coordinates: List<Coordinate> = s.split(' ')
                .filter {',' in it}
                .map{ Coordinate.parse(it) }

            return when{
                s.startsWith("toggle") -> Instruction(TOGGLE, coordinates[0], coordinates[1])
                s.startsWith("turn on") -> Instruction(TURN_ON, coordinates[0], coordinates[1])
                s.startsWith("turn off") -> Instruction(TURN_OFF, coordinates[0], coordinates[1])
                else -> error("Instruction.parse(): WRONG INPUT")
            }
        }
    }
}