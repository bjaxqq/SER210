


val FIR_board = FourInARow()

/** The entry main method (the program starts here)  */
fun main() {
 val currentState: Int = GameConstants.PLAYING
 val userInput = ""
 //game loop
 do {
  FIR_board.printBoard()
  /** TODO implement the game loop
   * 1- accept user move
   * 2- call getComputerMove
   * 3- Check for winner
   * 4- Print game end messages in case of Win , Lose or Tie !
   */
 } while (currentState == GameConstants.PLAYING && userInput != "q")
// repeat if not game-over
}
 