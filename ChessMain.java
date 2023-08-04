import java.util.Scanner;

public class ChessMain {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Displayer display = new Displayer();
    Chess c = new Chess();

    int x1, y1, x2, y2, hasWinner;
    String promotePiece, playAgain, initBlock = "none", finalBlock = "none";
    boolean anotherGame = true;

    display.GameHeader();
    display.Instructions();

    while (anotherGame == true) { // keep looping, until there is a winner
      display.Board(c.GetBoard());

      if (c.ScanCheck()) { // showing the king is in danger!
        System.out.println("CHECK! Move or protect your king");
      }

      System.out.println("Player " + c.GetCurrPlayerColor() + ", enter which piece to move (i.e A2, E4, etc): ");
      // do-while loop to prevent the user to input invalid initial column
      do {
        // while loop to avoid index out of bounds
        while (initBlock.length() != 2) {
          System.out.print("From: ");
          initBlock = input.nextLine();
          initBlock = initBlock.toUpperCase();
          // error message if block selected is incorrect
          if (initBlock.length() != 2) {
            System.out.println("Error: Invalid block. Please follow the instructions written.\n");
          }
        }
        // error message if the block selected is incorrect
        if ((int) initBlock.charAt(0) - 65 < 0 || (int) initBlock.charAt(0) - 65 > 8
            || (int) initBlock.charAt(1) - 48 < 0 || (int) initBlock.charAt(1) - 48 > 8) {
          System.out.println("Error: Invalid block. Please follow the instructions written.\n");
          initBlock = "none";
        }
      } while ((int) initBlock.charAt(0) - 65 < 0 || (int) initBlock.charAt(0) - 65 > 8
          || (int) initBlock.charAt(1) - 48 < 0 || (int) initBlock.charAt(1) - 48 > 8);
      // do-while loop to prevent the user to input invalid final block
      do {
        // while loop to avoid index out of bounds errors
        while (finalBlock.length() != 2) {
          System.out.print("To: ");
          finalBlock = input.nextLine();
          finalBlock = finalBlock.toUpperCase();
          // error message if block selected is incorrect
          if (finalBlock.length() != 2) {
            System.out.println("Error: Invalid block. Please follow the instructions written.\n");
          }
        }
        // error message if the block selected is incorrect
        if ((int) finalBlock.charAt(0) - 65 < 0 || (int) finalBlock.charAt(0) - 65 > 8
            || (int) finalBlock.charAt(1) - 48 < 0 || (int) finalBlock.charAt(1) - 48 > 8) {
          System.out.println("Error: Invalid block. Please follow the instructions written.\n");
          finalBlock = "none";
        }
      } while (finalBlock.charAt(0) - 65 < 0 || (int) finalBlock.charAt(0) - 65 > 8
          || (int) finalBlock.charAt(1) - 48 < 0 || (int) initBlock.charAt(1) - 48 > 8);

      // convert the information into integer to simplify the code
      x1 = (int) initBlock.charAt(0) - 65;
      y1 = (int) initBlock.charAt(1) - 48;
      x2 = (int) finalBlock.charAt(0) - 65;
      y2 = (int) finalBlock.charAt(1) - 48;

      // re-initialize the inputs
      finalBlock = "none";
      initBlock = "none";

      // call MovePiece() method in class Chess
      int movePiece = c.MovePiece(x1, Chess.SIZE - y1, x2, Chess.SIZE - y2);

      // more checking
      if (movePiece == 1) { // if the piece moved successfully
        if (c.GetBoard()[Chess.SIZE - y2][x2].GetPieceType() == Piece.W_PAWN
            || c.GetBoard()[Chess.SIZE - y2][x2].GetPieceType() == Piece.B_PAWN) { // check if it's a pawn or not (black
                                                                                   // or white)
          // check if the pawn is reaching to the opposite board and able to be promoted
          if (y2 == 8 && y1 == 7) {
            // do-while loop if the user doesn't input correct keyword/promotion piece
            do {
              System.out.println("Your pawn at row 8, column " + ((char) (x2 + 65))
                  + " just got promoted, what piece you want the pawn to be?");
              promotePiece = input.nextLine();
              // error message if the user input incorrect keyword
              if (!promotePiece.equalsIgnoreCase("bishop") && !promotePiece.equalsIgnoreCase("knight")
                  && !promotePiece.equalsIgnoreCase("queen") && !promotePiece.equalsIgnoreCase("rook")) {
                System.out.println("Please choose Rook/Knight/Bishop/Queen ONLY\n");
              }
            } while (!promotePiece.equalsIgnoreCase("bishop") && !promotePiece.equalsIgnoreCase("knight")
                && !promotePiece.equalsIgnoreCase("queen") && !promotePiece.equalsIgnoreCase("rook"));

            // promote the pawn to promotePiece.
            c.PawnPromotion(x2, Chess.SIZE - y2, promotePiece, c.GetBoard());
          }
        }
        c.FlipBoard();
        c.SwitchPlayer();
        System.out.println("Successfully moved piece\n");
      } else if (movePiece == 0) { // error message if there is no piece in the selected block
        System.out.println("Error: There is no piece in the block\n");
      } else if (movePiece == -1) { // error message if the piece failed to move
        System.out.println("Error: Invalid move\n");
      }

      hasWinner = c.HasWinner();

      if (hasWinner == 6 || hasWinner == -6) { // if there is a winner
        display.Board(c.GetBoard());
        if (hasWinner == 6) { // showing the message that white player has won the game
          System.out.println("Congratulation! Player WHITE has won the game!");
        } else if (hasWinner == -6) { // showing the message that black player has won the game
          System.out.println("Congratulation! Player BLACK has won the game!");
        }
        do { // loop if the user input invalid input
          System.out.print("Would you like to play one more time (yes/no)? ");
          playAgain = input.nextLine();
          // error messageif the user doesn't input yes or no
          if (!playAgain.equalsIgnoreCase("yes") && !playAgain.equalsIgnoreCase("no")) {
            System.out.println("Error: Please enter YES/NO only\n");
          } else if (playAgain.equalsIgnoreCase("yes")) { // if yes, the board will be reset, and black starts first
            System.out.println("\n");
            c.ResetBoard();
            c.SwitchPlayer();
            break;
          } else if (playAgain.equalsIgnoreCase("no")) { // if no, the directly exit the programme (game)
            System.out.println("Thank You For Playing! Good Game!\n");
            anotherGame = false;
            break;
          }
        } while (!playAgain.equalsIgnoreCase("yes") || !playAgain.equalsIgnoreCase("no"));
      }
    }
    input.close();
  }
}