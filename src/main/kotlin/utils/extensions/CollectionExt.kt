package utils.extensions

/**
 * Returns a Collection of the items present in all collections
 */
fun <T> Collection<Collection<T>>.multiIntersect(): Collection<T> =
    this.reduce { acc, collection -> acc.intersect(collection) }
