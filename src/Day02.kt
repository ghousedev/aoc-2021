fun main() {
    val input = readInput("Day02")

    // Part 1
    var horizontal = 0
    var depth = 0

    val commands = listOf("forward", "up", "down")

    input.forEach {
        val split = it.split(" ")
        val command = split[0] // Gets the command from the string
        val value = split[1].toInt() // Gets the value from the string

        when (command) {
            commands[0] -> horizontal += value // Forward command
            commands[1] -> depth -= value // Up command
            commands[2] -> depth += value // Down command
        }
    }

    var sum = horizontal.times(depth)
    println(sum)

    // Part 2
    horizontal = 0
    depth = 0
    var aim = 0

    input.forEach {
        val split = it.split(" ")
        val command = split[0] // Gets the command from the string
        val value = split[1].toInt() // Gets the value from the string

        when (command) {
            commands[0] -> {
                horizontal += value // Forward command
                depth += aim.times(value)
            }
            commands[1] -> aim -= value // Up command
            commands[2] -> aim += value // Down command
        }
    }
    sum = horizontal.times(depth)
    println(sum)
}