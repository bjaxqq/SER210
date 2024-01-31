/**
 * TicTacToe class implements the interface
 * @author relkharboutly
 * @date 2/12/2022
 */
class FourInARow
/**
 * clear board and set current player
 */
    : IGame {
    // game board in 2D array initialized to zeros
    private val board = Array(GameConstants.ROWS) { IntArray(GameConstants.COLS){0} }

    // Function for clearing the entire board
    override fun clearBoard() {
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                // Setting each cell to an empty cell
                board[row][col] = GameConstants.EMPTY
            }
        }
    }

    // Function for setting the player's move
    override fun setMove(player: Int, location: Int) {
        // Converting the location to coordinates
        val row = location / GameConstants.COLS
        val col = location % GameConstants.COLS

        // Checking if the cell is empty or not before moving
        if (board[row][col] == GameConstants.EMPTY) {
            board[row][col] = player
        }
    }

    // Function for the computer's AI
    override val computerMove: Int
        get() {
            for (row in 0 until GameConstants.ROWS) {
                for (col in 0 until GameConstants.COLS) {
                    if (board[row][col] == GameConstants.EMPTY) {
                        return row * GameConstants.COLS + col
                    }
                }
            }
            return 0
        }

    override fun checkForWinner(): Int {
        // Horizontal win check
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS - 3) {
                if (board[row][col] != GameConstants.EMPTY &&
                    board[row][col] == board[row][col + 1] &&
                    board[row][col] == board[row][col + 2] &&
                    board[row][col] == board[row][col + 3]
                ) {
                    return if (board[row][col] == GameConstants.BLUE) {
                        GameConstants.BLUE_WON
                    } else {
                        GameConstants.RED_WON
                    }
                }
            }
        }

        // Vertical win check
        for (row in 0 until GameConstants.ROWS - 3) {
            for (col in 0 until GameConstants.COLS) {
                if (board[row][col] != GameConstants.EMPTY &&
                    board[row][col] == board[row + 1][col] &&
                    board[row][col] == board[row + 2][col] &&
                    board[row][col] == board[row + 3][col]
                ) {
                    return if (board[row][col] == GameConstants.BLUE) {
                        GameConstants.BLUE_WON
                    } else {
                        GameConstants.RED_WON
                    }
                }
            }
        }

        // Diagonal (left to right) win check
        for (row in 0 until GameConstants.ROWS - 3) {
            for (col in 0 until GameConstants.COLS - 3) {
                if (board[row][col] != GameConstants.EMPTY &&
                    board[row][col] == board[row + 1][col + 1] &&
                    board[row][col] == board[row + 2][col + 2] &&
                    board[row][col] == board[row + 3][col + 3]
                ) {
                    return if (board[row][col] == GameConstants.BLUE) {
                        GameConstants.BLUE_WON
                    } else {
                        GameConstants.RED_WON
                    }
                }
            }
        }

        // Diagonal (right to left) win check
        for (row in 0 until GameConstants.ROWS - 3) {
            for (col in 3 until GameConstants.COLS) {
                if (board[row][col] != GameConstants.EMPTY &&
                    board[row][col] == board[row + 1][col - 1] &&
                    board[row][col] == board[row + 2][col - 2] &&
                    board[row][col] == board[row + 3][col - 3]
                ) {
                    return if (board[row][col] == GameConstants.BLUE) {
                        GameConstants.BLUE_WON
                    } else {
                        GameConstants.RED_WON
                    }
                }
            }
        }

        // Tie check
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                if (board[row][col] == GameConstants.EMPTY) {
                    // If there is an empty cell, the game is still in progress
                    return GameConstants.PLAYING
                }
            }
        }

        return GameConstants.TIE
    }

    /**
     * Print the game board
     */
    fun printBoard() {
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                printCell(board[row][col]) // print each of the cells
                if (col != GameConstants.COLS - 1) {
                    print("|") // print vertical partition
                }
            }
            println()
            if (row != GameConstants.ROWS - 1) {
                println("-----------") // print horizontal partition
            }
        }
        println()
    }

    /**
     * Print a cell with the specified "content"
     * @param content either BLUE, RED or EMPTY
     */
    fun printCell(content: Int) {
        when (content) {
            GameConstants.EMPTY -> print("   ")
            GameConstants.BLUE -> print(" B ")
            GameConstants.RED -> print(" R ")
        }
    }
}

