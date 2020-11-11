package utils.extensions

fun Double.toIntCeil(): Int =
    this.toInt().let{
        if (it < this) it+1 else it
}