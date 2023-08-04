public class Displayer {
  // ================ CONSTRUCTOR ================ //

  // ================ PRIVATE METHODS ================ //

  // ================ PUBLIC METHODS ================ //

  /*Method to display the game header*/
  public void GameHeader() {
    System.out.println("                =================================");
	 System.out.println("                |     WELCOME TO CHESS.JAVA     |");
	 System.out.println("                =================================");  
  }

  /*Method to display the instructions regarding the piece*/
  public void Instructions() {
    System.out.println("        ================================================");
    System.out.println("        |                 INSTRUCTIONS                 |");
    System.out.println("        ================================================");
	 System.out.println("        | W             = White  | B = Black           |");
	 System.out.println("        | K             = King   | Q = Queen           |");
	 System.out.println("        | k (lowercase) = Knight | B = Bishop          |");
	 System.out.println("        | R             = Rook   | P = Pawn            |");
	 System.out.println("        ================================================");
  }

  /*Method to display the chess board*/
  public void Board (Piece[][] board) {
    System.out.println("row  =========================================================");
    for(int row = 0; row < Chess.SIZE; row++) { //generate the row
        System.out.print(" " + (Chess.SIZE - row) + "   ");
      for (int col = 0; col < Chess.SIZE; col++) { //generate the column
        if(board[row][col] == null) { //if the array is empty, display nothing
          System.out.print("|      ");
        }    
        else { //if the array is filled, display the string of the piece
          System.out.print("| " + board[row][col].GetStr() + " ");    
        } 
      }
      System.out.println("|");
      System.out.println("     =========================================================");
    }
    System.out.println ("col      A      B      C      D      E      F      G      H \n");
  }
}