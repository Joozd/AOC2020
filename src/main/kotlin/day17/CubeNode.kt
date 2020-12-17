package day17

import utils.gameOfLife.GameOfLifeNode

class CubeNode(x: Int, y: Int, val z: Int): GameOfLifeNode<Boolean>(x,y) {
    override var state = false
    val active: Boolean
        get() = state

    fun assignNeighbours(universe: Collection<CubeNode>){
        neighbours = universe.filter {
            it.x in (x-1..x+1) && it.y in (y-1..y+1) && it.z in (z-1..z+1) && (it.x != x || it.y != y || it.z != z)
        }
    }

    override fun assignNextState(): Boolean =
        neighbours.count { it.state }.let{
            if (state) it in (2..3) else it == 3
        }
}