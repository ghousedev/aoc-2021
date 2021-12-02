fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day01")

    // Part 1
    var increases = 0
    var i = 0

    while (i < part1(input) - 1) {
        if (input[i].toInt() < input[i + 1].toInt()) {
            increases++
        }
        i++
    }
    println("Part One: $increases")

    // Part 2
    increases = 0
    i = 0

    while (i < part2(input) - 3 ) {
        val sum1 = input[i].toInt() + input[i + 1].toInt() + input[i + 2].toInt()
        val sum2 = input[i + 1].toInt() + input[i + 2].toInt() + input [i + 3].toInt()
            if (sum2 > sum1) {
                increases++
            }
        i++
        }
    println("Part Two: $increases")
}
