public class Knight extends Piece {
  private int pieceType;
  
  // ================ CONSTRUCTOR ================ //
  public Knight(int pieceType) {
    this.pieceType = pieceType;
  }

  // ================ PRIVATE METHODS ================ //

  // ================ PUBLIC METHODS ================ //

  /* Method to get the piece type.       
   * @return pieceType, a value that represents certain piece 
   */
  public int GetPieceType () {
    return this.pieceType;
  }
  
  /* Method to get the string of the piece type.       
   * @return bk, indicates black knight.
   *         wk, indicates white knight.
  */
  public String GetStr() {
    if(pieceType < 0) { //if the piece type is lower than 0, return black knight string
      return "^bk^";
    }
    else { //if the piece type is > 0, return white knight string
      return "_wk_";
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
		// check whether or not the piece is moving in an L shape direction (2 grids long 1 gird wide)
		if (((x2 == x1 + 2 && (y2 == y1 + 1 || y2 == y1 - 1)) || (y2 == y1 - 2 && (x2 == x1 + 1 || x2 == x1 - 1)) ||
				(x2 == x1 - 2 && (y2 == y1 + 1 || y2 == y1 - 1)) || (y2 == y1 + 2 && (x2 == x1 + 1 || x2 == x1 - 1)))) {
			if (board[y2][x2] == null) { //if empty (to move)
				board[y2][x2] = board[y1][x1];
				board[y1][x1] = null;
				return true;
			}
         //to take white piece (for black piece)
			if (board[y2][x2].GetPieceType() > 0  && this.pieceType < 0) { 
				board[y2][x2] = board[y1][x1];
				board[y1][x1] = null;
				return true;
			}
         //to take black piece (for white piece)
			if (board[y2][x2].GetPieceType() < 0  && this.pieceType > 0) {
				board[y2][x2] = board[y1][x1];
				board[y1][x1] = null;
				return true;
			}
		}
    return false;
  }
}