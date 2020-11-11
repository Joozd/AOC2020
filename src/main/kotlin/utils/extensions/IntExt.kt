package utils.extensions

/**
 * get square root of an integer (floored if not exact, just like fractions)
 */
fun Int.sqrt(): Int{
    require(this > 0){ "Cannot get the square root of a negative number" }
    /**
     * Call this from the first power of two that is higher than [target]
     * @param target: The value to be squared
     * @param guess: This guess. First time should be [previous] - [previous shr 2] (ie halfway between previous and the one before that)
     * @param previous: Previous guess. Initially the first power of two higher than [target]
     * @param tooHigh: [previous]^2 > target
     */
    fun zoomIn(target: Int, guess: Int, previous: Int, tooHigh: Boolean = true): Int{
        val sqrd = guess*guess
        if (sqrd == target) return guess
        if ((guess - previous).abs() == 1) { // last iteration
            return when {
                sqrd > target -> guess - 1
                tooHigh -> guess
                else -> guess+1
            }
        }
        val half = (((guess+previous)/2.0).toIntCeil() - guess).abs()
        if (sqrd > target) return zoomIn(target, guess-half, guess, true)
        return zoomIn(target, guess+half, guess, false)
    }

    /**
     * Use this to find the first value to enter into `zoomIn`
     * If target is a power of two squared, this will return exact answer
     */
    fun getFirstHigher(target: Int): Int{
        var n = 1
        while (n*n < target) n = n.shl(1)
        return n
    }

    when(this){
        0 -> return 0
        in(1..3) -> return 1
        in(4..8) -> return 2
        in(9..15) -> return 3
        else -> {
            val fh = getFirstHigher(this)
            if (fh*fh == this) return fh
            return zoomIn(this, fh-fh.shr(2), fh)
        }
    }
}

fun Int.abs() = if (this < 0) this*-1 else this

