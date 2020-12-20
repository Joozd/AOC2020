package day20

import utils.extensions.getDigits
import utils.rotateStringList

/**
 * [edgeIDsUnsorted] are all possible edge IDs, unsorted for now
 * @param edgeStrings = list of edges (top, right, bottom, left)
 */
class Tile(val number: Int, edgeStrings: List<String>, private var content: List<String>) {
    val edgeIDsUnsorted = edgeStrings.map{it.toInt(2)} + edgeStrings.map{it.reversed().toInt(2)}
    private var flipped = false // flipped vertical is the same as flipped horizontal and rotated twice
    private var rotation = 0 // can be 0,1,2, or 3
    private var _edgeIds = edgeStrings.toTypedArray()
    val edgeIDs: List<Int>
        get() = _edgeIds.map{it.toInt(2)}

    val top: Int
        get() = _edgeIds[0].toInt(2)
    val right: Int
        get() = _edgeIds[1].toInt(2)
    val bottom: Int
        get() = _edgeIds[2].toInt(2)
    val left: Int
        get() = _edgeIds[3].toInt(2)

    /**
     * Best to flip when "rotate" is at 0
     * This flips HORIZONTAL
     */
    fun flip(){
        flipped = !flipped
        val newEdgeIds = Array<String>(4){ "0000000000"}
        newEdgeIds[0] = _edgeIds[0].reversed()
        newEdgeIds[2] = _edgeIds[2].reversed()
        newEdgeIds[1] = _edgeIds[3]
        newEdgeIds[3] = _edgeIds[1]
        _edgeIds = newEdgeIds
    }
    fun rotate(){
        rotation = (rotation+1) % 4
        val newEdgeIds = Array<String>(4){ "0000000000"}
        newEdgeIds[1] = _edgeIds[0]
        newEdgeIds[2] = _edgeIds[1].reversed()
        newEdgeIds[3] = _edgeIds[2]
        newEdgeIds[0] = _edgeIds[3].reversed()
        _edgeIds = newEdgeIds
    }

    /**
     * Sets the next orientation for this tile.
     */
    fun nextOrientation(){
        rotate()
        if (rotation == 0)
            flip()
    }

    fun showContent(): List<String>{
        var result = content
        if (flipped) result = result.map{it.reversed()}
        return rotateStringList(result, rotation)
    }

    /**
     * Edge IDs are always left to right and top to bottom
     */





    companion object{

        fun of(s: List<String>): Tile{
            val number = s.first().getDigits().toInt()
            val tile = s.drop(1).map{it.replace('.', '0').replace('#', '1')}
            val edges = listOf(tile.first(), tile.map{it.last()}.joinToString(""), tile.last(), tile.map{it.first()}.joinToString(""))
            val content = s.drop(2).dropLast(1).map{it.drop(1).dropLast(1)}
            return Tile(number, edges, content)
        }
    }
}
