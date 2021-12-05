fun main() {
    data class Power(var gammaRate: String, var epsilonRate: String)
    val input = readInput("Day03")
    val inputSize = input.size
    val consumption = Power("", "")
    val column = CharArray(inputSize)
    var i = 0
    var j = 0

    fun countBits(list: MutableList<Char>, input: MutableList<String>): List<Int> {
        var zeroes = 0
        var ones = 0
        while (i < input.size) {
            val aChar = input[i][j]
            list.add(aChar)
            i++
        }
        list.forEach { char ->
            if (char == Char(48)) {
                zeroes++
            } else {
                ones++
            }
        }
        return listOf(zeroes, ones)
    }

    while (j < input[i].length) {
        while (i < inputSize) {
            val aChar = input[i][j]
            column[i] = aChar
            i++
        }
        var zeroes = 0
        var ones = 0
        column.forEach { char ->
            if (char == Char(48)) {
                zeroes++
            } else {
                ones++
            }
        }
        if (zeroes < ones) {
            consumption.gammaRate = consumption.gammaRate + "0"
            consumption.epsilonRate = consumption.epsilonRate + "1"
        } else {
            consumption.gammaRate = consumption.gammaRate + "1"
            consumption.epsilonRate = consumption.epsilonRate + "0"
        }
        j++
        i = 0
    }
    val gammaInt = Integer.parseInt(consumption.gammaRate, 2)
    val epsilonInt = Integer.parseInt(consumption.epsilonRate, 2)
    val consumptionRate = gammaInt.times(epsilonInt)
    println(consumptionRate)

    // Part 2
    fun reduceOxyList(input: MutableList<String>, zeroes: Int, ones: Int, j: Int) {
        if (zeroes > ones) {
            val iterator = input.listIterator()
            while (iterator.hasNext()) {
                if (iterator.next()[j] == Char(49)) {
                    iterator.remove()
                }
            }
        }
        if (zeroes == ones || zeroes < ones) {
            val iterator = input.listIterator()
            while (iterator.hasNext()) {
                if (iterator.next()[j] == Char(48)) {
                    iterator.remove()
                }
            }
        }
    }

    fun reduceCoList(input: MutableList<String>, zeroes: Int, ones: Int, j: Int) {
        if (zeroes > ones) {
            val iterator = input.listIterator()
            while (iterator.hasNext()) {
                if (iterator.next()[j] == Char(48)) {
                    iterator.remove()
                }
            }
        }
        if (zeroes == ones || zeroes < ones) {
            val iterator = input.listIterator()
            while (iterator.hasNext()) {
                if (iterator.next()[j] == Char(49)) {
                    iterator.remove()
                }
            }
        }
    }

    val oxyInput = input.toMutableList()
    j = 0
    while (oxyInput.size > 1) {
        i = 0
        val bitList = emptyList<Char>().toMutableList()
        val count = countBits(bitList, oxyInput)
        reduceOxyList(oxyInput, count[0], count[1], j)
        j++
    }

    val coInput = input.toMutableList()
    j = 0
    while (coInput.size > 1) {
        i = 0
        val bitList = emptyList<Char>().toMutableList()
        val count = countBits(bitList, coInput)
        reduceCoList(coInput, count[0], count[1], j)
        j++
    }

    val oxyInt = Integer.parseInt(oxyInput[0], 2)
    val coInt = Integer.parseInt(coInput[0], 2)

    println(oxyInt.times(coInt))
}