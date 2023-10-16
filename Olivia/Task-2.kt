// Data class Figure з полями width, height
data class Figure(val width: Int, val height: Int)

// Enum class для типів фігур
enum class ShapeType {
    SQUARE, CIRCLE, LINE_SEGMENT
}

// Sealed class Shape та його наслідники
sealed class Shape {
    data class Square(val sideLength: Int) : Shape()
    data class Circle(val radius: Int) : Shape()
    data class LineSegment(val length: Int) : Shape()
}

fun main() {
    // Створення списку із дата класів Figure з новими значеннями
    val figures = listOf(
        Figure(7, 15),
        Figure(10, 8),
        Figure(5, 5)
    )

    // Порахувати загальну суму площ фігур
    val totalArea = figures.sumBy { it.width * it.height }
    println("Загальна площа всіх фігур: $totalArea")

    // Створення списку із Shape об'єктів
    val shapes = listOf(
        Shape.Square(6),
        Shape.Circle(8),
        Shape.LineSegment(12),
        Shape.Square(3)
    )

    // Підрахувати кількість Square, Circle та LineSegment за допомогою фільтрації списку
    val squareCount = shapes.filterIsInstance<Shape.Square>().size
    val circleCount = shapes.filterIsInstance<Shape.Circle>().size
    val lineSegmentCount = shapes.filterIsInstance<Shape.LineSegment>().size

    println("Кількість Square: $squareCount")
    println("Кількість Circle: $circleCount")
    println("Кількість LineSegment: $lineSegmentCount")

    // Підрахувати кількість Square, Circle та LineSegment за допомогою when в циклі списку
    var squareCount2 = 0
    var circleCount2 = 0
    var lineSegmentCount2 = 0

    for (shape in shapes) {
        when (shape) {
            is Shape.Square -> squareCount2++
            is Shape.Circle -> circleCount2++
            is Shape.LineSegment -> lineSegmentCount2++
        }
    }

    println("Кількість Square (за допомогою when): $squareCount2")
    println("Кількість Circle (за допомогою when): $circleCount2")
    println("Кількість LineSegment (за допомогою when): $lineSegmentCount2")
}
