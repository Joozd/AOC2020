package shared

class AccessibleHashSet<T>(): MutableSet<T> {
    constructor(initialElements: Collection<T>): this(){
        addAll(initialElements)
    }

    private val _contents = HashMap<T, T>()

    override val size
        get() = _contents.keys.size
    override fun add(element: T): Boolean =
        if (element in _contents.keys) false else true.also{
            _contents[element] = element
        }


    override fun addAll(elements: Collection<T>): Boolean =
        elements.filter {it !in _contents.keys}.takeIf{ it.isNotEmpty()}?.let{
            it.forEach {  e ->
                _contents[e] = e
            }
            true
        } ?: false


    override fun clear() {
        _contents.clear()
    }

    override fun iterator(): MutableIterator<T> = object: MutableIterator<T>{
        val i = _contents.keys.iterator()
        var mostRecent: T? = null
        override fun hasNext(): Boolean = i.hasNext()

        override fun next(): T = i.next().also{
            mostRecent = it
        }

        override fun remove() {
            mostRecent?.let {
                _contents.remove(it)
            }
        }

    }

    override fun remove(element: T): Boolean =
        if (element !in _contents.keys) false else true.also{
            _contents.remove(element)
        }

    override fun removeAll(elements: Collection<T>): Boolean =
        elements.map{ remove(it) }.any()

    override fun retainAll(elements: Collection<T>): Boolean = if (_contents.keys.all { it in elements}) false
    else{
        val presentInBoth = elements.intersect(_contents.keys).map{it to it}
        _contents.clear()
        _contents.putAll(presentInBoth.toMap())
        true
    }

    override fun contains(element: T): Boolean = _contents.keys.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean = _contents.keys.containsAll(elements)

    override fun isEmpty(): Boolean = _contents.isEmpty()

    fun getElement(element: T): T? = _contents[element]

    /**
     * Will return a list of all found elements in this AccessibleHashSet
     */
    fun getElements(elements: Collection<T>): List<T> = elements.mapNotNull{_contents[it]}

    override fun toString() = _contents.keys.toString()
}