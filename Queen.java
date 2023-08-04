public class Queen extends Piece {
  private int pieceType;
  
  // ================ CONSTRUCTOR ================ //
  public Queen(int pieceType) {
    this.pieceType = pieceType;
  }

	// ================ PRIVATE METHODS ================ //

  /* Method to check conditions to move a piece.
   * @Param   x1. , initial column
   *          y1. , initial row
   *          x2. , final column (move to)
   *          y2  , final row (move to) 
   *        board , Chess board 2D array      
   * @return  true, if the move is valid for the selected piece 
   *         false, if the move is invalid for the selected piece
  */
	private boolean moveUpRightDiagonal(int x1, int y1, int x2, int y2, Piece[][] board) {
		int j = x1 + 1;
    //check if the diagonal line is empty or not
		for (int i = y1 - 1; i > y2; i--) { 
			if (board[i][j] != null) { //if not empty, return false
				return false;
			}
			j++;
		}
    //if empty, move the piece to the selected block
		board[y2][x2] = board[y1][x1];
		board[y1][x1] = null;
		return true;
	}

  /* Method to check conditions to move a piece.
   * @Param   x1. , initial column
   *          y1. , initial row
   *          x2. , final column (move to)
   *          y2  , final row (move to) 
   *        board , Chess board 2D array      
   * @return  true, if the move is valid for the selected piece 
   *         false, if the move is invalid for the selected piece
  */
	private boolean moveUpLeftDiagonal(int x1, int y1, int x2, int y2, Piece[][] board) {
		int j = x1 - 1;
		for (int i = y1 - 1; i > y2; i--) { //check if the diagonal line is empty or not
			if (board[i][j] != null) { //if not empty, return false
				return false;
			}
			j--;
		}
    //if empty, move the piece to the selected block
		board[y2][x2] = board[y1][x1];
		board[y1][x1] = null;
		return true;
	}

  /* Method to check conditions to move a piece.
   * @Param   x1. , initial column
   *          y1. , initial row
   *          x2. , final column (move to)
   *          y2  , final row (move to) 
   *        board , Chess board 2D array      
   * @return  true, if the move is valid for the selected piece 
   *         false, if the move is invalid for the selected piece
  */
	private boolean moveDownRightDiagonal(int x1, int y1, int x2, int y2, Piece[][] board) {
		int j = x2 - 1;
		for (int i = y2 - 1; i > y1; i--) { //check if the diagonal line is empty or not
			if (board[i][j] != null) { //if not empty, return false
				return false;
			}
			j--;
		}
    //if empty, move the piece to the selected block
		board[y2][x2] = board[y1][x1];
		board[y1][x1] = null;
		return true;
	}

  /* Method to check conditions to move a piece.
   * @Param   x1. , initial column
   *          y1. , initial row
   *          x2. , final column (move to)
   *          y2  , final row (move to) 
   *        board , Chess board 2D array      
   * @return  true, if the move is valid for the selected piece 
   *         false, if the move is invalid for the selected piece
  */
	private boolean moveDownLeftDiagonal(int x1, int y1, int x2, int y2, Piece[][] board) {
		int j = x1 - 1;
		for (int i = y1 + 1; i < y2; i++) { //check if the diagonal line is empty or not
			if (board[i][j] != null) { //if not empty, return false
				return false;
			}
			j--;
		}
    //if empty, move the piece to the selected block
		board[y2][x2] = board[y1][x1];
		board[y1][x1] = null;
		return true;
	}

  /* Method to check conditions to move a piece.
   * @Param   x1. , initial column
   *          y1. , initial row
   *          x2. , final column (move to)
   *          y2  , final row (move to) 
   *        board , Chess board 2D array      
   * @return  true, if the move is valid for the selected piece 
   *         false, if the move is invalid for the selected piece
  */
  private boolean moveVertically (int x1, int y1, int x2, int y2, Piece[][] board) {
		// if it's moving downward
		if (y2 > y1) {
			for (int i = y1 + 1; i < y2; i++) { //check if the vertical line (downward) is empty or not
				if (board[i][x1] != null) { //if not empty, return false;
					return false;
				}
			}
		}
		// if it's moving upward
		else {
			for (int i = y1 - 1; i > y2; i--) { //check if the vertical line (upward) is empty or not
				if (board[i][x1] != null) { //if not empty, return false
					return false;
				}
			}	
		}
    //if empty, move the piece to the selected block
		board[y2][x2] = board[y1][x1];
		board[y1][x1] = null;
		return true;
  }

  /* Method to check conditions to move a piece.
   * @Param   x1. , initial column
   *          y1. , initial row
   *          x2. , final column (move to)
   *          y2  , final row (move to) 
   *        board , Chess board 2D array      
   * @return  true, if the move is valid for the selected piece 
   *         false, if the move is invalid for the selected piece
  */
  private boolean moveHorizontally (int x1, int y1, int x2, int y2, Piece[][] board) {
    //if it's moving to the right
    if (x2 > x1) {
      for (int i = x1 + 1; i < x2; i++) { //check if the horizontal line (right direction) is empty or not
        if (board[y1][i] != null) { //if not empty, return false
          return false;
        }
      }
   	}
    //if it's moving to the left
    else {
      for (int i = x1 - 1; i > x2; i--) { //check if the horizontal line (left direction) is empty or not
        if (board[y1][i] != null) { //if not empty, return false
          return false;
        }
      }
    } 
    //if empty, move the piece to the selected block
    board[y2][x2] = board[y1][x1];
    board[y1][x1] = null;
    return true;
  }

  /* Method to check conditions to move a piece.
   * @Param   x1. , initial column
   *          y1. , initial row
   *          x2. , final column (move to)
   *          y2  , final row (move to) 
   *        board , Chess board 2D array      
   * @return  true, if the move is valid for the selected piece 
   *         false, if the move is invalid for the selected piece
  */
  private boolean takeWhitePiece (int x1, int y1, int x2, int y2, Piece[][] board) {
    // condition if the black piece takes white piece vertically
    if (x2 == x1) {
			return moveVertically(x1, y1, x2, y2, board);
		}
    // condition if the black piece takes white piece horizontally
		else if (y2 == y1) {
			return moveHorizontally(x1, y1, x2, y2, board);
		}
    // to prevent negative values for the diagonal (down right or down left)
		else if (Math.abs(x2 - x1) == Math.abs(y2 - y1)) {
      // condition if the black piece takes white piece diagonally (up right)
      if (x2 > x1 && y2 < y1) {
        return moveUpRightDiagonal(x1, y1, x2, y2, board);
      }
      // condition if the black piece takes white piece diagonally (up left)
      else if (x2 < x1 && y2 < y1) { //up left diagonal
        return moveUpLeftDiagonal(x1, y1, x2, y2, board);
      }
      // condition if the black piece takes white piece diagonally (down right)
      else if (x2 > x1 && y2 > y1) {
        return moveDownRightDiagonal(x1, y1, x2, y2, board);
      }
      // condition if the black piece takes white piece diagonally (down left)
      else if (x2 < x1 && y2 > y1) { 
        return moveDownLeftDiagonal(x1, y1, x2, y2, board);
      }
    }  
		return false;
  }

  /* Method to check conditions to move a piece.
   * @Param   x1. , initial column
   *          y1. , initial row
   *          x2. , final column (move to)
   *          y2  , final row (move to) 
   *        board , Chess board 2D array      
   * @return  true, if the move is valid for the selected piece 
   *         false, if the move is invalid for the selected piece
  */
  private boolean takeBlackPiece (int x1, int y1, int x2, int y2, Piece[][] board) {
    // condition if the white piece takes black piece vertically
    if (x2 == x1) {
			return moveVertically(x1, y1, x2, y2, board);
		}
    // condition if the white piece takes black piece horizontally
    else if (y2 == y1) {
			return moveHorizontally(x1, y1, x2, y2, board);
		}
    // to prevent negative values for the diagonal (down right or down left)
		else if (Math.abs(x2 - x1) == Math.abs(y2 - y1)) {
			// condition if the white piece takes black piece diagonally (up right)
      if (x2 > x1 && y2 < y1) {
				return moveUpRightDiagonal(x1, y1, x2, y2, board);
			}
			// condition if the white piece takes black piece diagonally (up left)
			else if (x2 < x1 && y2 < y1) { //up left diagonal
				return moveUpLeftDiagonal(x1, y1, x2, y2, board);
			}
      // condition if the white piece takes black piece diagonally (down right)
			else if (x2 > x1 && y2 > y1) {
				return moveDownRightDiagonal(x1, y1, x2, y2, board);
			}
      // condition if the white piece takes black piece diagonally (down left)
			else if (x2 < x1 && y2 > y1) { 
				return moveDownLeftDiagonal(x1, y1, x2, y2, board);
			}
		}
		return false;
  }

  // ================ PUBLIC METHODS ================ //

  /* Method to get the piece type.       
   * @return pieceType, a value that represents certain piece 
   */
  public int GetPieceType () {
    return this.pieceType;
  }
  
  /* Method to get the string of the piece type.       
   * @return bQ, indicates black queen.
   *         wQ, indicates white queen.
  */
  public String GetStr() {
    if(pieceType < 0) { //if the piece type is < 0, return black queen string 
      return "^bQ^";
    }
    else { //if the piece type is > 0, return white queen string
      return "_wQ_";
    }
  }

  /* Method to check conditions to move a piece.
   * @Param   x1. , initial column
   *          y1. , initial row
   *          x2. , final column (move to)
   *          y2  , final row (move to) 
   *        board , Chess board 2D array      
   * @return  true, if the move is valid for the selected piece 
   *         false, if the move is invalid for the selected piece
  */
  public boolean Move(int x1, int y1, int x2, int y2, Piece[][] board) {
    //move
    if (board[y2][x2] == null) { //check if the selected block is empty or not
      if (x2 == x1) { //condition if the piece move vertically  
				return moveVertically (x1, y1, x2, y2, board);
      }
      else if (y2 == y1) { //condition if the piece move horizontally
        return moveHorizontally (x1, y1, x2, y2, board);
      }
      //to prevent negative values for the diagonal (down right or down left)
			else if (Math.abs(x2 - x1) == Math.abs(y2 - y1)) {
				if (x2 > x1 && y2 < y1) { //condition if the piece move up right diagonal
					return moveUpRightDiagonal(x1, y1, x2, y2, board);
				}
				else if (x2 < x1 && y2 < y1) {  //condition if the piece move up left diagonal			
          return moveUpLeftDiagonal(x1, y1, x2, y2, board);
				}
				else if (x2 > x1 && y2 > y1) {  //condition if the piece move down right diagonal				
				  return moveDownRightDiagonal(x1, y1, x2, y2, board);
				}
				else if (x2 < x1 && y2 > y1) {  //condition if the piece move down left diagonal						
          return moveDownLeftDiagonal(x1, y1, x2, y2, board);
				}
			}
    }
    //take piece
    else {
      //condition for black piece to take white piece
      if (pieceType < 0 && board[y2][x2].GetPieceType() > 0) {		    
				return takeWhitePiece(x1, y1, x2, y2, board);
		  } 
      //condition for white piece to take black piece
		  else if (pieceType > 0 && board[y2][x2].GetPieceType() < 0) {				
				return takeBlackPiece(x1, y1, x2, y2, board);
		  }
    }
    return false;
  }
}