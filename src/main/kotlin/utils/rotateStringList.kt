package utils

fun rotateStringList(ls: List<String>, rotations: Int): List<String>{
    if (rotations %4 == 0) return ls
    if (rotations % 4 == 2) return ls.reversed().map{it.reversed()}
    val lineLength = ls.first().length
    val result = Array(lineLength){ Array(ls.size){ ' '} }
    (0 until lineLength).forEach { column ->
        ls.indices.forEach { line ->
            result[line][column] = ls[column][line]
        }
    }
    result.map{it.reversed().joinToString("")}.let {
        return if (rotations == 1) it else rotateStringList(it, rotations-1)
    }
}