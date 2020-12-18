package day17

import Solution // Contains functions for reading input file and timing how long it takes to get the answers
import utils.MultiThreader

class Day17(day: Int): Solution(day) {
    //override val inputLines = test1

    override val first: String
        get() = one().toString()
    override val second: String
        get() = two().toString()

    private fun one(): Int {
        val universe = generateUniverse(inputLines.first().length + 2 * OFFSET, inputLines.size + 2 * OFFSET, 2 * OFFSET)
        inputLines.forEachIndexed { y, s ->
            s.forEachIndexed { x, c ->
                if (c == '#')
                universe.first { it.x == x && it.y == y && it.z == 0 }.state = true
            }
        }
        universe.forEach {
            it.assignNeighbours(universe)
        }
        repeat(6){
            universe.forEach {
                it.tick()
            }
            universe.forEach {
                it.execute()
            }
        }
        return universe.count { it.state }
    }

    private fun two(): Int {
        val universe = generateUniverse(inputLines.first().length + 2 * OFFSET, inputLines.size + 2 * OFFSET, 2 * OFFSET, 2* OFFSET)
        println("Universe generated!")
        inputLines.forEachIndexed { y, s ->
            s.forEachIndexed { x, c ->
                if (c == '#')
                    universe.first { it.x == x && it.y == y && it.z == 0 && it.w == 0}.state = true
            }
        }
        println("Universe populated!")
        MultiThreader(assignMultiThread(universe)).forEach { _ ->

        }
        println("Universe populated!")
        repeat(6){ repeat ->
            universe.forEach {
                it.tick()
            }
            universe.forEach {
                it.execute()
            }
            println("repeat #$repeat!")
        }
        return universe.count { it.state }
    }

    private fun generateUniverse(xx: Int, yy: Int, zz: Int): Collection<CubeNode> =
        (0..xx).map{ x ->
            (0..yy).map{ y ->
                (0..zz).map { z ->
                    CubeNode(x-OFFSET,y-OFFSET,z-OFFSET)
                }
            }.flatten()
        }.flatten()

    private fun generateUniverse(xx: Int, yy: Int, zz: Int, ww: Int): List<HyperNode> =
        (0..xx).map{ x ->
            (0..yy).map{ y ->
                (0..zz).map { z ->
                    (0..ww).map { w ->
                        HyperNode(x-OFFSET,y-OFFSET,z-OFFSET, w-OFFSET)
                    }
                }.flatten()
            }.flatten()
        }.flatten()


    fun assignMultiThread(universe: List<HyperNode>) = object: Iterable<Unit>{
        private val universe = universe
        override fun iterator() = object: Iterator<Unit>{
            private val u = universe
            private var counter = 0
            override fun hasNext() = counter < u.size

            override fun next() {
                u[counter++].assignNeighbours(universe)
            }
        }
    }

    companion object{
        const val OFFSET = 6

        val test1 = (".#.\n" +
                "..#\n" +
                "###").lines()
    }
}