import java.lang.Integer.parseInt

fun main() {
    data class Bingo(val numbers: List<Int>, val players: MutableList<MutableList<MutableList<Int>>>)

    val input = readInput("Day04")
    //val input = readInput("Day04Test")
    val numbers: List<Int> = input[0].split(",").map {
        it.toInt()
    }
    val boards = input.toMutableList()
    // Take out the numbers list and the first empty string
    boards.removeFirst()
    boards.removeFirst()

    // Replace the double spaces before single digits for parsing
    val iterator = boards.listIterator()
    while (iterator.hasNext()) {
        var string = iterator.next()
        if (string.contains("  ")) {
            string = string.replace("\\s+".toRegex(), " ")
            iterator.set(string)
        }
    }

    // Separate each board into a separate list
    val board: MutableList<MutableList<String>> = mutableListOf()
    var i = 0
    var j = 5
    while (j < 598) {
        board.add(boards.subList(i, j))
        i += 6
        j += 6
    }

    // Map the string list to a 2d array of ints
    val grid = board.map { list ->
        list.map { line ->
            line.trim().split(" ").map { int ->
                try {
                    parseInt(int)
                } catch (e: Exception) {
                    println(e)
                }

            }
        }
    }

    val currentGame = Bingo(numbers, grid as MutableList<MutableList<MutableList<Int>>>)

    fun calculateScore(player: List<List<Int>>, lastCalled: Int): Int {
        var total = 0
        // Sum of unchanged numbers
        player.forEach { list ->
            list.forEach { number ->
                if (number != 100) {
                    total += number
                }
            }
        }
        total = total.times(lastCalled)
        return total
    }
    val winnerList = listOf<Int>().toMutableList()

    // For the following loops
    var numberIndex = 0
    var playerIndex = 0
    var columnIndex = 0
    var rowIndex = 0

    while (numberIndex < currentGame.numbers.size) {
        while (playerIndex < currentGame.players.size) {
            while (rowIndex < 5) {
                while (columnIndex < 5) {
                    val numberDrawn = currentGame.numbers[numberIndex]
                    val numberToCheck = currentGame.players[playerIndex][rowIndex][columnIndex]
                    if (numberToCheck == numberDrawn) {
                        // Mark number
                        currentGame.players[playerIndex][rowIndex][columnIndex] = 100
                        // Check for row
                        val firstColumn = currentGame.players[playerIndex][rowIndex][0]
                        val secondColumn = currentGame.players[playerIndex][rowIndex][1]
                        val thirdColumn = currentGame.players[playerIndex][rowIndex][2]
                        val fourthColumn = currentGame.players[playerIndex][rowIndex][3]
                        val fifthColumn = currentGame.players[playerIndex][rowIndex][4]
                        val currentRowTotal = firstColumn + secondColumn + thirdColumn + fourthColumn + fifthColumn
                        // Check for column
                        val firstRow = currentGame.players[playerIndex][0][columnIndex]
                        val secondRow = currentGame.players[playerIndex][1][columnIndex]
                        val thirdRow = currentGame.players[playerIndex][2][columnIndex]
                        val fourthRow = currentGame.players[playerIndex][3][columnIndex]
                        val fifthRow = currentGame.players[playerIndex][4][columnIndex]
                        val currentColumnTotal = firstRow + secondRow + thirdRow + fourthRow + fifthRow
                        // Record first and last winners index and print answers
                        if (currentRowTotal == 500 || currentColumnTotal == 500) { // Board is a winner
                            if (!winnerList.contains(playerIndex)) {
                                winnerList.add(playerIndex)
                                // First winner for part 1
                                if (winnerList.size == 1) {
                                    println(currentGame.players[winnerList.first()]) // Winning board
                                    println("First winner score: " +                 // Winning score
                                        calculateScore(
                                            currentGame.players[winnerList.first()],
                                            currentGame.numbers[numberIndex]
                                        )
                                    )
                                }
                                // Last winner for part 2
                                if (winnerList.size == 99) {
                                    println(currentGame.players[winnerList.last()]) // Last board to win
                                    println("Last winner score: " +                 // Last winners score
                                        calculateScore(
                                            currentGame.players[winnerList.last()],
                                            currentGame.numbers[numberIndex]
                                        )
                                    )
                                    return
                                }
                            }
                        }
                    }
                    columnIndex++
                }
                columnIndex = 0
                rowIndex++
            }
            rowIndex = 0
            playerIndex++
        }
        playerIndex = 0
        numberIndex++
    }
}
