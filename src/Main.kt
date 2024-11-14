import java.util.*
import kotlin.concurrent.thread

fun runDiningPhilosophers(philosopherCount: Int) {
    val chopsticks = Array(philosopherCount) { Chopstick() }
    val philosophers = Array(philosopherCount) { i ->
        Philosopher(i, chopsticks[i], chopsticks[(i + 1) % philosopherCount])
    }

    philosophers.shuffle()

    val threads = philosophers.map { philosopher ->
        thread { philosopher.tryToEat() }
    }
    threads.forEach { it.join() }

    println("Результаты:")
    philosophers.forEach { philosopher ->
        if (philosopher.isEating) {
            println("Философ ${philosopher.id} обедает.")
        } else {
            println("Философ ${philosopher.id} размышляет.")
        }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    println("Введите количество философов:")
    val philosopherCount = scanner.nextInt()
    runDiningPhilosophers(philosopherCount)
}
