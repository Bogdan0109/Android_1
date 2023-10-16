// Абстрактний клас Тварина
abstract class Animal(private val name: String) {
    // Публічна змінна
    var age: Int = 0
    
    // Абстрактна функція
    abstract fun makeSound()

    // Публічна функція
    fun introduce() {
        println("Мене звуть $name і мені $age років.")
    }
}

// Клас Собака, який розширює клас Тварина
class Dog(name: String) : Animal(name), Voice {
    override fun makeSound() {
        println("Гав-гав!")
    }

    // Реалізація функцій інтерфейсу ПодатиГолос
    override fun loudVoice() {
        println("Собака гучно гавкає!")
    }

    override fun quietVoice() {
        println("Собака тихо гавкає.")
    }
}

// Клас Птиця, який розширює клас Тварина
class Bird(name: String) : Animal(name), Voice {
    override fun makeSound() {
        println("Цвірінь-цвірінь!")
    }

    // Реалізація функцій інтерфейсу ПодатиГолос
    override fun loudVoice() {
        println("Птиця співає голосно!")
    }

    override fun quietVoice() {
        println("Птиця співає тихо.")
    }
}

// Інтерфейс ПодатиГолос з двома функціями
interface Voice {
    fun loudVoice()
    fun quietVoice()
}

fun main() {
    // Створення екземплярів класів та виклик їх функцій
    val dog = Dog("Рекс")
    dog.age = 5
    dog.introduce()
    dog.makeSound()
    dog.loudVoice()
    dog.quietVoice()

    val bird = Bird("Чічі")
    bird.age = 2
    bird.introduce()
    bird.makeSound()
    bird.loudVoice()
    bird.quietVoice()
}
