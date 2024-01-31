


val FIR_board = FourInARow()

/** The entry main method (the program starts here)  */
fun main() {
  val currentState: Int = GameConstants.PLAYING
  val userInput = ""
  //game loop
  do {
    FIR_board.printBoard()

    print("Enter a location for your move (0-35): ")
    val userMove = readLine()?.toIntOrNull()

    if (userMove != null) {
      FIR_board.setMove(GameConstants.BLUE, userMove)
    }

    FIR_board.printBoard()

    val computerMove = FIR_board.computerMove
    FIR_board.setMove(GameConstants.RED, computerMove)

    val winState = FIR_board.checkForWinner()

    when (winState) {
      GameConstants.TIE -> println("The game has finished with a tie.")
      GameConstants.BLUE_WON -> println("The game has finished with a win for the users.")
      GameConstants.RED_WON -> println("The game has finished with a win for the computer.")
    }
  } while (currentState == GameConstants.PLAYING && userInput != "q")
// repeat if not game-over
}
 