import java.util.Scanner

interface Selectable<T> {
    fun select(scanner: Scanner): T?
}
interface Creatable<T> {
    fun create(scanner: Scanner): T?
}