package day16

data class Aunt(val identifier: Int, val traits: Map<String, Int>) {
    companion object{
        fun parse(s: String): Aunt {
            val identifier = s.filter{ it != ':'}.split(' ')[1].toInt()
            val traits = s.drop(s.indexOf(':')+1).split(',').map{it.trim()}.map{trait ->
                trait.split(": ").let{it[0] to it[1].toInt()}
            }.toMap()
            return Aunt(identifier, traits )
        }
    }
}

// Sue 454: goldfish: 8, pomeranians: 6, trees: 10