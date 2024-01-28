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

    override fun clearBoard() {
        for (i in 0 until GameConstants.ROWS) {
            for (j in 0 until GameConstants.COLS) {
                board[i][j] = GameConstants.EMPTY
            }
        }
    }

    override fun setMove(player: Int, location: Int) {
        val row = location / GameConstants.COLS
        val col = location % GameConstants.COLS

        if (board[row][col] == GameConstants.EMPTY) {
            board[row][col] = player
        }
    }

    override val computerMove: Int
        get() {
            for (i in 0 until GameConstants.ROWS) {
                for (j in 0 until GameConstants.COLS) {
                    if (board[i][j] == GameConstants.EMPTY) {
                        return i * GameConstants.COLS + j
                    }
                }
            }
            return 0
        }

    override fun checkForWinner(): Int {
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

        for (col in 0 until GameConstants.COLS) {
            for (row in 0 until GameConstants.ROWS - 3) {
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

        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                if (board[row][col] == GameConstants.EMPTY) {
                    return GameConstants.PLAYING
                }
            }
        }
    
        return GameConstants.PLAYING
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

