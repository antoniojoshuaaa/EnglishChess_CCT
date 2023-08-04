public class King extends Piece {
   private int pieceType;
	private boolean hasMoved;
  
  // ================ CONSTRUCTOR ================ //
  public King(int pieceType) {
    this.pieceType = pieceType;
		this.hasMoved = false;
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
  private boolean moveHorizontally (int x1, int y1, int x2, int y2, Piece[][] board) {
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
  private boolean moveVertically (int x1, int y1, int x2, int y2, Piece[][] board) {
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
  private boolean moveDiagonally (int x1, int y1, int x2, int y2, Piece[][] board) {
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
		//to prevent negative values for any diagonal direction
    if (Math.abs(x2 - x1) == Math.abs(y2 - y1) && Math.abs(y2 - y1) == 1) { 
      return moveDiagonally (x1, y1, x2, y2, board);
    }
    //prevent negative values (left direction)
    if (Math.abs(x2 - x1) == 1 && y2 == y1) {
      return moveHorizontally (x1, y1, x2, y2, board);
    }
    //prevent negative values (downward direction)
    if (Math.abs(y2 - y1) == 1 && x2 == x1) {
      return moveVertically (x1, y1, x2, y2, board);
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
		//to prevent negative values for any diagonal direction
    if (Math.abs(x2 - x1) == Math.abs(y2 - y1) && Math.abs(y2 - y1) == 1) {
      return moveDiagonally (x1, y1, x2, y2, board);
    }
    //prevent negative values (left direction)
    if (Math.abs(x2 - x1) == 1 && y2 == y1) {
      return moveHorizontally (x1, y1, x2, y2, board);
    }
    //prevent negative values (downward direction)
    if (Math.abs(y2 - y1) == 1 && x2 == x1) {
      return moveVertically (x1, y1, x2, y2, board);
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
  private boolean whiteKingCastling (int x1, int y1, int x2, int y2, Piece[][] board) {
    if(x2 == 2) { //if the king is moving to the left
			if(board[7][0] instanceof Rook) { //check if left corner is rook or not
				if (board[7][0].GetHasMoved() == false) { //check if the left rook has moved from its original pos or not
					if (x1 == 4 && x2 == 2 && y1 == y2) { //check if the selected block to do castle is valid
						for (int i = x1-1; i > 0; i--) { //check the line (no bishop/knight/queen)
							if (board[y1][i] != null) { //if yes, return false
								return false;
							}
						}
                  //if empty, set the king and the rook status to has moved
						this.hasMoved = true;
						board[7][0].SetHasMoved(true);
                  //swap the position of the rook and the king
						board[y1][2] = board[y1][4];
						board[y1][3] = board[y1][0];
						board[y1][4] = null;
						board[y1][0] = null;
						return true;
					}
				}
			}
      }
    else if (x2 == 6) { //if the king is moving to the right
      if (board[7][7] instanceof Rook) { //check if left corner is rook or not
        if (board[7][7].GetHasMoved() == false) { //check if the right rook has moved from its original pos or not
          if (x1 == 4 && x2 == 6 && y1 == y2) { //check if the selected block to do castle is valid
            for (int i = x1+1; i < 7; i++) { //check the line (no bishop/knight/queen)
              if (board[y1][i] != null) { //if yes, return false
                return false;
              }
            }
            //if empty, set the king and the rook status to has moved
            this.hasMoved = true;
            board[7][7].SetHasMoved(true);
            //swap the position of the rook and the king
            board[y1][6] = board[y1][4];
            board[y1][5] = board[y1][7];
            board[y1][4] = null;
            board[y1][7] = null;
            return true;
          }
        }
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

  private boolean blackKingCastling (int x1, int y1, int x2, int y2, Piece[][] board) {
    if(x2 == 1) { //if the king is moving to the left
				if(board[7][0] instanceof Rook) { //check if left corner is rook or not
					if (board[7][0].GetHasMoved() == false) { //check if the left rook has moved from its original pos or not
						if (x1 == 3 && x2 == 1 && y1 == y2) { //check if the selected block to do castle is valid
							for (int i = x1-1; i > 0; i--) { //check the line (no bishop/knight/queen)
								if (board[y1][i] != null) { //if yes, return false
									return false;
								}
							}
              //if empty, set the king and the rook status to has moved
							this.hasMoved = true;
							board[7][0].SetHasMoved(true);
              //swap the position of the rook and the king
							board[y1][1] = board[y1][3];
							board[y1][2] = board[y1][0];
							board[y1][3] = null;
							board[y1][0] = null;
							return true;
						}
					}
				}
      }
    else if (x2 == 5) { //if the king is moving to the right
      if (board[7][7] instanceof Rook) { //check if left corner is rook or not
        if (board[7][7].GetHasMoved() == false) { //check if the right rook has moved from its original pos or not
          if (x1 == 3 && x2 == 5 && y1 == y2) { //check if the selected block to do castle is valid
            for (int i = x1+1; i < 7; i++) { //check the line (no bishop/king/)
              if (board[y1][i] != null) { //if yes, return false
                return false;
              }
            }
            //if empty, set the king and the rook status to has moved
            this.hasMoved = true;
            board[7][7].SetHasMoved(true);
            //swap the position of the rook and the king
            board[y1][5] = board[y1][3];
            board[y1][4] = board[y1][7];
            board[y1][3] = null;
            board[y1][7] = null;
            return true;
          }
        }
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
   * @return bK, indicates black king.
   *         wK, indicates white king.
  */
  public String GetStr() {
    if(pieceType < 0) { //if the piece type is negative, return black king string
      return "^bK^"; 
    } 
    else { // if the piece type is > 0, reeturn white king string
      return "_wK_";
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
    //if the king has not moved from its original position
    if (!hasMoved) { 
      if (pieceType > 0) { //for white king
        if (Math.abs(x2 - x1) == Math.abs(y2 - y1) && Math.abs(y2 - y1) == 1 && board[y2][x2] == null) { 
          return moveDiagonally (x1, y1, x2, y2, board);
        }
        //prevent negative values (for negative direction (left))
        if (Math.abs(x2 - x1) == 1 && y2 == y1 && board[y2][x2] == null) {
          return moveHorizontally (x1, y1, x2, y2, board);
        }
        //prevent negative values (for negative direction (down))
        if (Math.abs(y2 - y1) == 1 && x2 == x1 && board[y2][x2] == null) {
          return moveVertically (x1, y1, x2, y2, board);
        }
        //prevent negative values for casling (left or right)
        if (Math.abs(x2 - x1) == 2) {
          return whiteKingCastling (x1, y1, x2, y2, board);
        }
      }
      else if (pieceType < 0) { //for black king
        if (Math.abs(x2 - x1) == Math.abs(y2 - y1) && Math.abs(y2 - y1) == 1 && board[y2][x2] == null) { 
            return moveDiagonally (x1, y1, x2, y2, board);
        }
        //prevent negative values (for negative direction (left))
        if (Math.abs(x2 - x1) == 1 && y2 == y1 && board[y2][x2] == null) {
          return moveHorizontally (x1, y1, x2, y2, board);
        }
        //prevent negative values (for negative direction (down))
        if (Math.abs(y2 - y1) == 1 && x2 == x1 && board[y2][x2] == null) {
          return moveVertically (x1, y1, x2, y2, board);
        }
        //prevent negative values for casling (left or right)
        if (Math.abs(x2 - x1) == 2) {
          return blackKingCastling (x1, y1, x2, y2, board);
        }
      }
    }
    //if the king has moved from its original posiiton and unable to do castling
		else {
      if (board[y2][x2] == null) { //check if the desired block is empty or not (to move)
        //prevent negative values (for negative direction (down-left & down-right))
        if (Math.abs(x2 - x1) == Math.abs(y2 - y1) && Math.abs(y2 - y1) == 1 && board[y2][x2] == null) { 
          return moveDiagonally (x1, y1, x2, y2, board);
        }
        //prevent negative values (for negative direction (left))
        if (Math.abs(x2 - x1) == 1 && y2 == y1 && board[y2][x2] == null) {
          return moveHorizontally (x1, y1, x2, y2, board);
        }
        //prevent negative values (for negative direction (down))
        if (Math.abs(y2 - y1) == 1 && x2 == x1 && board[y2][x2] == null) {
          return moveVertically (x1, y1, x2, y2, board);
        }
      }
      //condition for black pieces to take white pieces
      else if (pieceType < 0 && board[y2][x2].GetPieceType() > 0) {
        return takeWhitePiece (x1, y1, x2, y2, board);
      }
      //condition for white pieces to take black pieces
      else if (pieceType > 0 && board[y2][x2].GetPieceType() < 0) {
        return takeBlackPiece (x1, y1, x2, y2, board);
      }
    }
    return false;
  }
}