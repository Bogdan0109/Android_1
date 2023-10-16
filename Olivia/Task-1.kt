// Базовий клас Тварина
open class Animal(val name: String) {
    // Віртуальна функція, яку можна перевизначити в нащадках
    open fun makeSound() {
        println("$name видає звук.")
    }
}

// Клас Собака, який розширює клас Тварина
class Dog(name: String) : Animal(name), Voice {
    // Додаткова змінна
    private var breed: String = "Лахматий"

    // Перевизначення функції з базового класу
    override fun makeSound() {
        println("$name лаїть.")
    }

    // Реалізація функцій інтерфейсу ПодатиГолос
    override fun loudVoice() {
        println("$name голосно лаїть.")
    }

    override fun quietVoice() {
        println("$name тихо лаїть.")
    }
}

// Клас Птиця, який розширює клас Тварина
class Cat(name: String) : Animal(name), Voice {
    // Додаткова змінна
    private var color: String = "Синій"

    // Перевизначення функції з базового класу
    override fun makeSound() {
        println("$name мурчить.")
    }

    // Реалізація функцій інтерфейсу ПодатиГолос
    override fun loudVoice() {
        println("$name мурчить голосно.")
    }

    override fun quietVoice() {
        println("$name мурчить тихо.")
    }
}

// Інтерфейс ПодатиГолос з функціями
interface Voice {
    fun loudVoice()
    fun quietVoice()
}

fun main() {
    // Створення екземплярів класів та виклик їх функцій та змінних
    val dog = Dog("Річ")
    val cat = Cat("Мурчік")

    dog.makeSound()
    cat.makeSound()

    dog.loudVoice()
    cat.quietVoice()
}
