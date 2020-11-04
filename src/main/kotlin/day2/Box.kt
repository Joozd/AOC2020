package day2

import java.lang.Exception

class Box(private val l: Int, private val w: Int, private val h: Int) {
    private val front = w*h
    private val side = l*h
    private val bottom = l*w

    val paper: Int
        get() = 2*front + 2*side + 2*bottom + minOf(front, side, bottom)

    val ribbon: Int
        get() = listOf(l,w,h).sorted().dropLast(1).sumBy { 2*it } + l*w*h

    companion object{
        fun of(s: String): Box?{
            val lwh = try{
                s.split('x').map{it.toInt()}
            } catch (e: Exception) { return null }
            if (lwh.size != 3) return null
            return Box(lwh[0], lwh[1], lwh[2])
        }
    }
}