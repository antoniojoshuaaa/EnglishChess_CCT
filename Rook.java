public class Rook extends Piece {
  private int pieceType;
	private boolean hasMoved;
  
  // ================ CONSTRUCTOR ================ //
  public Rook(int pieceType) {
    this.pieceType = pieceType;
	 hasMoved = false;
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
  private boolean moveVertically (int x1, int y1, int x2, int y2, Piece[][] board) {
		// if it's moving downward
		if (y2 > y1) {
			for (int i = y1 + 1; i < y2; i++) { //check if all the blocks in the line (dowanward) is empty
				if (board[i][x1] != null) { //if it's not empty, return false
					return false;
				}
			}
		}
		// if it's moving upward
		else {
			for (int i = y1 - 1; i > y2; i--) { //check if all the blocks in the line (upward) is empty 
				if (board[i][x1] != null) { //if it's not empty, return false
					return false;
				}
			}	
		}
    //if empty, move the piece to the selected block
		board[y2][x2] = board[y1][x1];
		board[y1][x1] = null;
      this.hasMoved = true;
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
      for (int i = x1 + 1; i < x2; i++) { //check if all the blocks in the line (right direction) is empty
        if (board[y1][i] != null) { //if it's not empty, return false
          return false;
        }
      }
   	}
    //if it's moving to the left 
    else { 
      for (int i = x1 - 1; i > x2; i--) { //check if all the blocks in the line (left direction) is empty
        if (board[y1][i] != null) { //if it's not empty, return false
          return false;
        }
      }
    }
    //if empty, move the piece to the selected block
    board[y2][x2] = board[y1][x1];
    board[y1][x1] = null;
    this.hasMoved = true;
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
    //for black piece, take white piece vertically (upward or downward)
    if (x2 == x1) {
   		return moveVertically(x1, y1, x2, y2, board);
   	}
    //for white piece, take black piece vertically (left or right)
   	else if (y2 == y1) {
   		return moveHorizontally(x1, y1, x2, y2, board);
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
    //for black piece, take white piece vertically (upward or downward)
    if (x2 == x1) {
			return moveVertically(x1, y1, x2, y2, board);
		}
    //for white piece, take black piece vertically (left or right)
    else if (y2 == y1) {
			return moveHorizontally(x1, y1, x2, y2, board);
		}
		return false;
  }

  // ================ PUBLIC METHODS ================ //

  /* Method to get the piece type.       
   * @return pieceType, a value that represents certain piece 
   */ 
  public int GetPieceType() {
    return this.pieceType;
  }

  /* Method to get the string of the piece type.       
   * @return bR, indicates black rook.
   *         wR, indicates white rook.
  */
  public String GetStr() {
    if(pieceType < 0) { //if the piece type is < 0, return black rook string
      return "^bR^";
    }
    else { //if the piece type is > 0, return white rook string
      return "_wR_";
    }
  }

  /* Method to get the rook has moved or not.       
   * @return hasMoved, true if the rook has moved from its original posiiton
   *                   false if the rook has not moved
   */ 
	public boolean GetHasMoved() {
		return this.hasMoved;
	}

  /* Method to set the rook into has moved or has not moved.       
   * @param hasMoved, setting the status of the rook to has moved
   */ 
	public void SetHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
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
    //if the selected block to be moved is empty
    if (board[y2][x2] == null) {
		 	if (x2 == x1) { //condition to check if the rook is moving vertically or not
	       return moveVertically (x1, y1, x2, y2, board);
		 	}
		 	else if (y2 == y1) { //condition to check if the rook is moving horizontally
         return moveHorizontally (x1, y1, x2, y2, board);
		 	}
    }
		else { //if the selected block to be moved is not empty
      //condition for the black piece to take the white piece
			if (pieceType < 0 && board[y2][x2].GetPieceType() > 0) { 
				return takeWhitePiece(x1, y1, x2, y2, board);
			}
      //condition for the white piece to take the black piece
			else if (pieceType > 0 && board[y2][x2].GetPieceType() < 0) {
				return takeBlackPiece(x1, y1, x2, y2, board);
			}
		}
		return false;
  }
}