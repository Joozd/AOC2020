package day23

import Solution

class Day23(private val input: List<String>): Solution() {

    /**
     * `memory` is the programs memory.
     * adress:
     * 0: position of current instruction POSITION
     * 1: a
     * 2: b
     */

    override fun first() {
        val memory = IntArray(3){0}
        while (memory[POSITION] in program.indices){
            program[memory[POSITION]](memory)
        }
        println("reg b: ${memory[REG_B]}")
    }

    override fun second() {
        val memory  = listOf(0,1,0).toIntArray()
        while (memory[POSITION] in program.indices){
            program[memory[POSITION]](memory)
        }
        println("reg b: ${memory[REG_B]}")
    }



    /**
     * The program is a list of instructions.
     * An instruction is a function that takes an optional parameter(can be any value if not used) and the memory to use.
     * Instructions have to take care of setting the POSITION to next instruction.
     */
    val program: List<(IntArray) -> Unit> by lazy{
        input.map{buildInstruction(it)}
    }

    /**
     * instcurtions:
     */


    fun buildInstruction(s: String): (IntArray) -> Unit =
        when {
            s == HLF_A -> {memory -> memory[REG_A] /= 2
                memory[POSITION]++}
            s == HLF_B -> {memory -> memory[REG_B] /= 2
                memory[POSITION]++}

            s == TPL_A -> {memory -> memory[REG_A] *= 3
                memory[POSITION]++}
            s == TPL_B -> {memory -> memory[REG_B] *= 3
                memory[POSITION]++}

            s == INC_A -> {memory -> memory[REG_A]++
                memory[POSITION]++}
            s == INC_B -> {memory -> memory[REG_B]++
                memory[POSITION]++}

            s.startsWith(JMP) ->
                {memory -> memory[POSITION] += getParameter(s)}

            s.startsWith(JIO_A) ->
                {memory -> if (memory[REG_A] == 1) memory[POSITION] += getParameter(s) else memory[POSITION]++}
            s.startsWith(JIO_B) ->
                {memory -> if (memory[REG_B] == 1) memory[POSITION] += getParameter(s) else memory[POSITION]++}

            s.startsWith(JIE_A) ->
                {memory -> if (memory[REG_A]%2 == 0) memory[POSITION] += getParameter(s) else memory[POSITION]++}
            s.startsWith(JIE_B) ->
                {memory -> if (memory[REG_B]%2 == 0) memory[POSITION] += getParameter(s) else memory[POSITION]++}
            else -> error ("CANNOT PARSE INSTRUCTION $s")
        }

    private fun getParameter(s: String): Int = s.split(' ').last().toInt()


    companion object{
        //memory adresses:
        const val POSITION = 0
        const val REG_A = 1
        const val REG_B = 2

        //opcodes:
        const val HLF_A = "hlf a"
        const val HLF_B = "hlf b"

        const val TPL_A = "tpl a"
        const val TPL_B = "tpl b"

        const val INC_A = "inc a"
        const val INC_B = "inc b"

        const val JMP = "jmp" // plus parameter

        const val JIO_A = "jio a" // jum if one
        const val JIO_B = "jio b"

        const val JIE_A = "jie a"
        const val JIE_B = "jie b"
    }

}