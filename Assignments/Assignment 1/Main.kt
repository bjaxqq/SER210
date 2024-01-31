


val FIR_board = FourInARow()

/** The entry main method (the program starts here)  */
fun main() {
  val currentState: Int = GameConstants.PLAYING
  val userInput = ""
  //game loop
  do {
    FIR_board.printBoard()

    print("Enter your desired location (0-35): ")
    val userLocation = readLine()?.toIntOrNull()

    if (userLocation != null) {
      FIR_board.setMove(GameConstants.BLUE, userLocation)
    }

    FIR_board.printBoard()

    val computerLocation = FIR_board.computerMove
    FIR_board.setMove(GameConstants.RED, computerLocation)

    val gameState = FIR_board.checkForWinner()

    when (gameState) {
      GameConstants.TIE -> println("The game results in a tie.")
      GameConstants.BLUE_WON -> println("The game results in a win for the user.")
      GameConstants.RED_WON -> println("The game results in a win for the computer.")
    }
  
  } while (currentState == GameConstants.PLAYING && userInput != "q")
// repeat if not game-over
}
 