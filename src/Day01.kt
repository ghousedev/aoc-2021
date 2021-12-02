fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day01")

    val intInput = input.toIntList() // Convert to ints for comparison

    // Part 1
    var increases = 0
    var i = 0

    while (i < part1(input) - 1) {
        if (intInput[i] < intInput[i + 1]) {
            increases++
        }
        i++
    }
    println("Part One: $increases")

    // Part 2
    increases = 0
    i = 0

    while (i < part2(input) - 3) {
        val sum1 = intInput[i] + intInput[i + 1] + intInput[i + 2]
        val sum2 = intInput[i + 1] + intInput[i + 2] + intInput [i + 3]
            if (sum2 > sum1) {
                increases++
            }
        i++
        }
    println("Part Two: $increases")
}
