// Data class Figure з полем width та height
data class Figure(var width: Int, var height: Int) {
    // Приватний сеттер для поля area
    var area: Int
        private set(value) {}
        get() = width * height
}

// Enum class для типів фігур
enum class ShapeType {
    RECTANGLE, OVAL, LINE
}

// Sealed class Shape та його наслідники
sealed class Shape {
    class Rectangle(var width: Int, var height: Int) : Shape()
    class Oval(var radius: Int) : Shape()
    class Line(var length: Int) : Shape()
}

fun main() {
    // Створення списку із дата класів Figure
    val figures = listOf(
        Figure(5, 10),
        Figure(8, 6),
        Figure(3, 3)
    )

    // Порахувати загальну суму полів area
    val totalArea = figures.sumBy { it.area }
    println("Загальна площа всіх фігур: $totalArea")

    // Створення списку із Shape об'єктів
    val shapes = listOf(
        Shape.Rectangle(4, 7),
        Shape.Oval(5),
        Shape.Line(10),
        Shape.Rectangle(2, 5)
    )

    // Підрахувати кількість Rectangle, Oval та Line за допомогою фільтрації списку
    val rectangleCount = shapes.count { it is Shape.Rectangle }
    val ovalCount = shapes.count { it is Shape.Oval }
    val lineCount = shapes.count { it is Shape.Line }

    println("Кількість Rectangle: $rectangleCount")
    println("Кількість Oval: $ovalCount")
    println("Кількість Line: $lineCount")

    // Підрахувати кількість Rectangle, Oval та Line за допомогою when в циклі списку
    var rectangleCount2 = 0
    var ovalCount2 = 0
    var lineCount2 = 0

    for (shape in shapes) {
        when (shape) {
            is Shape.Rectangle -> rectangleCount2++
            is Shape.Oval -> ovalCount2++
            is Shape.Line -> lineCount2++
        }
    }

    println("Кількість Rectangle (за допомогою when): $rectangleCount2")
    println("Кількість Oval (за допомогою when): $ovalCount2")
    println("Кількість Line (за допомогою when): $lineCount2")
}
