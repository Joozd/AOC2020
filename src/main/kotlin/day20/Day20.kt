package day20

import Solution // Contains functions for reading input file and timing how long it takes to get the answers

class Day20(day: Int): Solution(day) {
    private val tiles by lazy { inputGroups.map{Tile.of(it)}}

    override val first: String
        get() = one().toString()
    override val second: String
        get() = two().toString()

    private fun one(): Long {
        tiles.filter {
            it.edgeIDsUnsorted.count { e -> tiles.filter { tt -> tt.number != it.number }.any { t -> e in t.edgeIDsUnsorted } } == 4 //4 because two sides match and match when both flipped
        }.let {
            return it.map { it.number }.fold(1L) { acc, n -> acc * n }
        }
    }

    private fun two(): Int {
        val result = Puzzle(tiles.toMutableList()).result()
        val monsterExtraSpace = result.first().length - MONSTER.first().length
        val monsterRegEx = MONSTER.joinToString(".{$monsterExtraSpace}").replace(' ', '.').toRegex()
        var mapAsLine = result.joinToString("")
        findAllMonsters(monsterRegEx, mapAsLine).forEach{
            mapAsLine = removeMonster(mapAsLine, it, monsterExtraSpace)
        }
        //I got lucky and didn't need to rotate my puzzle

        /*
        //Print a nice map
        mapAsLine.replace('.', ' ').replace('#', '~').chunked(96).forEach{
            println(it)
        }
        */
        return mapAsLine.count { it == '#'}
    }

    /**
     * Returns a list of all starting positions from which a monster has been found
     */
    private fun findAllMonsters(monster: Regex, map: String): List<Int>{
        var currentPos = 0
        val results = ArrayList<Int>()
        var result = monster.find(map)
        while (result != null){
            results.add(result.range.first + currentPos)
            currentPos += result.range.first + 1
            result = monster.find(map.drop(currentPos))
        }
        return results
    }

    private fun removeMonster(map:String, index: Int, gap: Int): String{
        val monster = MONSTER.joinToString(".".repeat(gap))
        val replacements = monster.mapIndexedNotNull { i, c -> if (c == '#') i else null }
        return map.toCharArray().apply{
            replacements.forEach {
                this[it + index] = 'O'
            }
        }.joinToString("")
    }

    companion object{
        val MONSTER = (
                "                  # \n" +
                "#    ##    ##    ###\n" +
                " #  #  #  #  #  #   ").lines()
    }
}