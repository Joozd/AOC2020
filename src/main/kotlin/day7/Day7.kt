package day7

import Solution

class Day7(private val input: List<String>): Solution() {
    private var result1: Int = 0

    override fun first() {
        val wires = HashMap<String, Int>()

        val instructions = input.toMutableList()

        while (instructions.isNotEmpty()){
            instructions.first().let{
                if (checkWiresAlive(wires, it))
                    one(wires, it)
                else
                    instructions.add(it)

                instructions.removeAt(0)
            }
        }
        println("Value of a: ${wires["a"]}")
        result1 = wires["a"]!!
    }

    override fun second() {
        val wires = HashMap<String, Int>().apply {
            set("b", result1)
        }

        val instructions = input.toMutableList()

        while (instructions.isNotEmpty()){
            instructions.first().let{
                if (checkWiresAlive(wires, it))
                    two(wires, it)
                else
                    instructions.add(it)

                instructions.removeAt(0)
            }
        }
        println("New value of a: ${wires["a"]}")
    }

    fun one(wires: HashMap<String, Int>, instruction: String){
        val w = instruction.split(" ")
        wires [w.last()] = when (w.firstOrNull{ it.all{ c -> c.isUpperCase()}}){
            AND ->  ((wires[w[0]] ?: w[0].toInt()) and (wires[w[2]] ?: w[2].toInt()))//.to16Bit()
            NOT -> (BITMASK xor (wires[w[1]] ?: w[0].toInt()))//.to16Bit()
            OR  -> ((wires[w[0]] ?: w[0].toInt()) or (wires[w[2]] ?: w[2].toInt()))//.to16Bit()
            LSHIFT -> ((wires[w[0]] ?: w[0].toInt()).shl(w[2].toInt()))//.to16Bit()
            RSHIFT -> ((wires[w[0]] ?: w[0].toInt()).shr(w[2].toInt()))//.to16Bit()
            else -> wires[w[0]] ?: w[0].toInt()
        }
    }

    fun two(wires: HashMap<String, Int>, instruction: String){
        val w = instruction.split(" ")
        if ( w.last() == "b") return
        wires [w.last()] = when (w.firstOrNull{ it.all{ c -> c.isUpperCase()}}){
            AND ->  ((wires[w[0]] ?: w[0].toInt()) and (wires[w[2]] ?: w[2].toInt()))//.to16Bit()
            NOT -> (BITMASK xor (wires[w[1]] ?: w[0].toInt()))//.to16Bit()
            OR  -> ((wires[w[0]] ?: w[0].toInt()) or (wires[w[2]] ?: w[2].toInt()))//.to16Bit()
            LSHIFT -> ((wires[w[0]] ?: w[0].toInt()).shl(w[2].toInt()))//.to16Bit()
            RSHIFT -> ((wires[w[0]] ?: w[0].toInt()).shr(w[2].toInt()))//.to16Bit()
            else -> wires[w[0]] ?: w[0].toInt()
        }
    }

    /**
     * Check if all but the last wire are already alive
     */
    private fun checkWiresAlive(wires: HashMap<String, Int>, instruction: String): Boolean =
        instruction.split(" ")
            .filter{ w -> w.all{ c-> c.isLowerCase() } }
            .dropLast(1)
            .all{ it in wires.keys }




    companion object{
        private const val AND = "AND"
        private const val NOT = "NOT"
        private const val OR = "OR"
        private const val LSHIFT = "LSHIFT"
        private const val RSHIFT = "RSHIFT"


        private const val BITMASK = 65535
    }
}