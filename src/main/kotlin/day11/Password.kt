package day11

/**
 * Password internally is an Array of Ints which will be converted back to a string when needed
 */
class Password(initial: String) {
    init{
        require(initial.length ==8 ) { "Password must be 8 characters!"}
    }
    private var characters = initial.toList().map{it.toInt() - A_OFFSET}.toIntArray()

    private fun carry(direction: Int){
        (7 downTo 1).forEach{
            if (characters[it]/ CARRY_VALUE == 1){
                characters[it-1]++
                characters[it] -= CARRY_VALUE
            }
            if(characters[it] < 0){
                characters[it-1]--
                characters[it] += CARRY_VALUE
            }
            if (characters[it] in listOf(I,O,L)) characters[it] += direction
        }
        if (characters[0] < 0) characters [0] += CARRY_VALUE
        if (characters[0] == CARRY_VALUE) characters[0] == 0
        if (characters[0] in listOf(I,O,L)) characters[0] += direction
    }

    private fun checkIncreasing() =
        (0..5).any{
            characters[it] == characters[it+1] -1
                    && characters[it+1] == characters[it+2] -1
        }


    private fun checkTwoPairs(): Boolean{
        (0..6).firstOrNull{characters[it] == characters[it+1]}?.let{firstPair ->
            return (0..6).any{characters[it] == characters[it+1] && characters[it] != characters[firstPair]}
        } ?: return false
    }

    private val validPassword: Boolean
        get() = checkIncreasing() && checkTwoPairs()


    override fun toString() = characters.joinToString("") { (it + A_OFFSET).toChar().toString() }



    fun next(): Password {
        do{
            characters[7]++
            carry(UP)
        } while (!validPassword)
        return this
    }

    fun prev(): Password{
        do {
            characters[7]--
            carry(DOWN)
        } while (!validPassword)
        return this
    }



    companion object{
        private const val A_OFFSET = 'a'.toInt()
        private const val CARRY_VALUE = 'z'.toInt() - A_OFFSET+1
        private const val I = 'i'.toInt() - A_OFFSET
        private const val O = 'o'.toInt() - A_OFFSET
        private const val L = 'l'.toInt() - A_OFFSET

        private const val UP = 1
        private const val DOWN = -1

    }

}
