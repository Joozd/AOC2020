package utils.extensions

import shared.AccessibleHashSet

fun <E> AccessibleHashSet<E>.getOrAdd(element: E): E = if (add(element)) element else getElement(element)!!

fun <E> AccessibleHashSet<E>.getOrAdd(elements: Collection<E>): List<E> {
    addAll(elements)
    return getElements(elements)
}
