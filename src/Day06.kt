fun main() {
    data class Fish(val days: MutableList<List<Int>>)

    val input = readInput("Day06")

    //val input = readInput("Day06Test")

    // Make input into a list of ints held in a list
    val split = input.first().split(",")

    val inputInt = split.toIntList().toMutableList()

    val count = Fish(mutableListOf(inputInt))

    val numberOfDays = 80

    var day = 0

    // Brute force the answer, this does not work for part 2.
    while (day < numberOfDays) {
        val nextDay = mutableListOf<Int>()
        var newFishCount = 0
        count.days.first().forEach {
            if (it == 0) {
                val newFish = 6
                nextDay.add(newFish)
                newFishCount++
            } else {
                nextDay.add(it - 1)
            }
            while (newFishCount > 0) {
                nextDay.add(8)
                newFishCount--
            }
        }

        count.days.add(nextDay)
        count.days.removeAt(0)
        day++
    }
    println("After 80 days there will be " + count.days.last().size + " lanternfish.")

    // Part 2
    fun fishGrowth(input: List<Int>, daysOfGrowth: Int): Long {
        val aList = listOf<Long>(0, 0, 0, 0, 0, 0, 0, 0, 0).toMutableList()
        input.forEach {
            aList[it]++
        }
        var days = 0
        var i = 0
        while (days < daysOfGrowth) {
            val readyToReplicate = aList[0]
            while (i < 8) {
                aList[i] = aList[i + 1]
                i++
            }
            aList[6] += readyToReplicate
            aList[8] = readyToReplicate
            i = 0
            days++
        }
        return aList.sum()
    }
    println("After 256 days there will be " + fishGrowth(inputInt, 256) + " lanternfish.")
}