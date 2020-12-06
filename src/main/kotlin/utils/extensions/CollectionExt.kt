package utils.extensions

fun <T> Collection<Collection<T>>.multiIntersect(): Collection<T> =
    this.reduce { acc, collection -> acc.intersect(collection) }
