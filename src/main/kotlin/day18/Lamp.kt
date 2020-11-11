package day18

open class Lamp(val x: Int, val y: Int, var on: Boolean, private val alwaysOn: Boolean = false) {
    private var nextValue = on
    private val neighbours = mutableListOf<Lamp>()
    private val litNeighbours: Int
        get() = neighbours.filter{it.on}.size

    fun addNeighbour(n: Lamp?){
        n?.let{
            neighbours.add(n)
        }
    }

    fun cycle(){
        nextValue = alwaysOn || if (on) litNeighbours in (2..3) else litNeighbours == 3
    }

    fun execute(){
        on = nextValue
    }
}