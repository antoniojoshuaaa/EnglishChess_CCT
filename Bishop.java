public class Bishop extends Piece {
  private int pieceType;

  // ================ CONSTRUCTOR ================ //
  public Bishop(int pieceType) {
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
		for (int i = y1 - 1; i > y2; i--) { //to check if the diagonal is empty or not
			if (board[i][j] != null) { //if the diagonal path is not empty, return false
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
		for (int i = y1 - 1; i > y2; i--) { //to check if the diagonal is empty or not
			if (board[i][j] != null) { //if the diagonal path is not empty, return false
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
		for (int i = y2 - 1; i > y1; i--) {//to check if the diagonal is empty or not
			if (board[i][j] != null) { //if the diagonal path is not empty, return false
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
		for (int i = y1 + 1; i < y2; i++) {//to check if the diagonal is empty or not
			if (board[i][j] != null) { //if the diagonal path is not empty, return false
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
	private boolean takeWhitePiece(int x1, int y1, int x2, int y2, Piece[][] board) {
		// up right diagonal
		if (x2 > x1 && y2 < y1) {
			return moveUpRightDiagonal(x1, y1, x2, y2, board);
		}
		// up left diagonal
		else if (x2 < x1 && y2 < y1) {
			return moveUpLeftDiagonal(x1, y1, x2, y2, board);
		}
		// down right diagonal
		else if (x2 > x1 && y2 > y1) { 
			return moveDownRightDiagonal(x1, y1, x2, y2, board);
		}
		// down left diagonal
		else if (x2 < x1 && y2 > y1) {
			return moveDownLeftDiagonal(x1, y1, x2, y2, board);
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
	private boolean takeBlackPiece(int x1, int y1, int x2, int y2, Piece[][] board) {
		// up right diagonal
		if (x2 > x1 && y2 < y1) {
			return moveUpRightDiagonal(x1, y1, x2, y2, board);
		}
		// up left diagonal
		else if (x2 < x1 && y2 < y1) {
			return moveUpLeftDiagonal(x1, y1, x2, y2, board);
		}
		// down right diagonal
		else if (x2 > x1 && y2 > y1) {
			return moveDownRightDiagonal(x1, y1, x2, y2, board);
		}
		// down left diagonal
		else if (x2 < x1 && y2 > y1) {
			return moveDownLeftDiagonal(x1, y1, x2, y2, board);
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

	/*
	 * Method to get the string of the piece type.
	 * 
	 * @return bB, indicates black bishop. wB, indicates white bishop.
	 */
	public String GetStr() {
		if (pieceType < 0) { //if the piece type is negatve, return black bishop
			return "^bB^";
		} else { //if the piece type is positive, return white bishop
			return "_wB_";
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
		if (Math.abs(x2 - x1) == Math.abs(y2 - y1)) { //avoid negative values
			if (board[y2][x2] == null) { // if the selected block is empty
				// up right diagonal
				if (x2 > x1 && y2 < y1) {
					return moveUpRightDiagonal(x1, y1, x2, y2, board);
				}
				// up left diagonal
				else if (x2 < x1 && y2 < y1) { 
					return moveUpLeftDiagonal(x1, y1, x2, y2, board);
				}
				// down right diagonal
				else if (x2 > x1 && y2 > y1) { 
					return moveDownRightDiagonal(x1, y1, x2, y2, board);
				}
				// down left diagonal
				else if (x2 < x1 && y2 > y1) {
					return moveDownLeftDiagonal(x1, y1, x2, y2, board);
				}
			} 
      //take white piece (for black piece)
      else if (this.pieceType < 0 && board[y2][x2].GetPieceType() > 0) {
				return takeWhitePiece(x1, y1, x2, y2, board);
			} 
      //take black piece (for white peice)
      else if (this.pieceType > 0 && board[y2][x2].GetPieceType() < 0) {
				return takeBlackPiece(x1, y1, x2, y2, board);
			}
		}
		return false;
	}
}