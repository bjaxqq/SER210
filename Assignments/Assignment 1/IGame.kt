/**
 * IGame interface.
 * @author relkharboutly
 * @date 1/12/2022
 */
interface IGame {
    /**
     * clear the board of all discs by setting all spots to EMPTY
     */
    fun clearBoard()

    /** Sets the given player at the given location on the game board.
     * The location must be available, or the board will not be changed
     * @param player - HUMAN_PLAYER or COMPUTER_PLAYER
     * @param location - The location (0-35) to place the move
     */
    fun setMove(player: Int, location: Int)

    /** Returns the best move for the computer to make. You must call setMove()
     * to actually make the computer move to the location
     * start with a dumb implementation that returns a the first non-occupied cell
     * @return the best move for the computer to make (0-35)
     */
    val computerMove: Int

    /**
     * Check for a winner and return a status value indicating who has won.
     * @return PLAYING if still playing, TIE if its a tie, BLUE_WON if X won, or RED_WON if 0 won
     */
    fun checkForWinner(): Int
}

object GameConstants{
    // Name-constants to represent the seeds and cell contents
    const val EMPTY = 0
    const val BLUE = 1
    const val RED = 2

    // Name-constants to represent the various states of the game
    const val PLAYING = 0
    const val TIE = 1
    const val RED_WON = 2
    const val BLUE_WON = 3

    const val ROWS = 6
    const val COLS = 6 // number of rows and columns
}