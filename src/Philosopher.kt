data class Philosopher(val id: Int, val leftChopstick: Chopstick, val rightChopstick: Chopstick) {
    var isEating = false

    fun tryToEat() {
        synchronized(leftChopstick) {
            if (leftChopstick.isTaken) return
            synchronized(rightChopstick) {
                if (rightChopstick.isTaken) return

                // Забираем обе палочки
                leftChopstick.isTaken = true
                rightChopstick.isTaken = true
                isEating = true
            }
        }
    }
}