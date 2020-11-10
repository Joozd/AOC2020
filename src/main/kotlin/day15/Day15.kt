package day15

import Solution
import utils.SplitResource

class Day15(val input: List<String>): Solution() {
    private val ingredients: Set<Ingredient> by lazy { input.map{ Ingredient.parse(it) }.toSet() }

    private fun calculateScore(ingredients: Collection<Ingredient>, m: Map<Ingredient, Int>, dontNeedCalories: Boolean = true): Int {
        val capacity: Int = ingredients.sumBy { it.capacity *  (m[it] ?: error("WRONG INGREDIENT ${it.name}")) }.zeroIfNegative()
        val durability: Int = ingredients.sumBy { it.durability *  (m[it] ?: error("WRONG INGREDIENT ${it.name}")) }.zeroIfNegative()
        val flavor: Int = ingredients.sumBy { it.flavor *  (m[it] ?: error("WRONG INGREDIENT ${it.name}")) }.zeroIfNegative()
        val texture: Int = ingredients.sumBy { it.texture *  (m[it] ?: error("WRONG INGREDIENT ${it.name}")) }.zeroIfNegative()
        return if (dontNeedCalories)
            capacity * durability * flavor * texture
        else {
            val calories: Int = ingredients.sumBy { it.calories *  (m[it] ?: error("WRONG INGREDIENT ${it.name}")) }
            if (calories == 500) capacity * durability * flavor * texture
            else 0
        }
    }
    private fun Int.zeroIfNegative() = if (this < 0) 0  else this

    override fun first() {
        val result = SplitResource(ingredients, 100, 1).maxByOrNull {m ->
            calculateScore(ingredients, m)
        }!!
        println("score: ${calculateScore(ingredients,result)}")
    }

    override fun second() {
        val ingredients = input.map{ Ingredient.parse(it) }.toSet()
        val result = SplitResource(ingredients, 100, 1).maxByOrNull {m ->
            calculateScore(ingredients, m, dontNeedCalories = false)
        }!!
        println("score: ${calculateScore(ingredients,result)}")
    }
}