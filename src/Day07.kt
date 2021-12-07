import kotlin.math.abs
import kotlin.math.roundToInt

fun main() {
    //val input = readInput("Day07Test")
    val input = readInput("Day07")

    val crabPositions = input.first().split(",").toIntList()

    fun median(aList: List<Int>) = aList.sorted().let { (it[it.size / 2] + it[(it.size - 1) / 2]) / 2 }

    fun part1(aList: List<Int>): Int {
        val bestPosition = median(aList)
        var totalFuel = 0
        crabPositions.forEach {
            totalFuel += abs(it - bestPosition)
        }
        return totalFuel
    }

    fun part2(aList: List<Int>): Int{
        val bestPosition = aList.average().roundToInt() - 1
        var totalFuel = 0
        crabPositions.forEach {
            val difference = abs(it - bestPosition)
            totalFuel += difference
            var i = 1
            while (i < difference) {
                totalFuel += i
                i++
            }
        }
        return totalFuel
    }

    println("Part 1 fuel used: " + part1(crabPositions))
    println("Part 2 fuel used: " + part2(crabPositions))
}