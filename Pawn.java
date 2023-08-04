public class Pawn extends Piece {
  private int pieceType;
  private boolean hasMoved;
  
  // ================ CONSTRUCTOR ================ //
  public Pawn(int pieceType) {
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
	private boolean firstMove (int x1, int y1, int x2, int y2, Piece[][] board) {
		//if the pawn has not moved from its original position, it may move up to 2 blocks only
    if (x2 == x1 && (y2 == y1 - 1 || y2 == y1 - 2)) {
        board[y2][x2] = board[y1][x1];
        board[y1][x1] = null;
        this.hasMoved = true;
        return true;
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
  private boolean movePawn (int x1, int y1, int x2, int y2, Piece[][] board) {
		//if the pawn has moved for the second time, it only may move 1 block forward
    if (x2 == x1 && y2 == y1 - 1) { 
      board[y2][x2] = board[y1][x1];
      board[y1][x1] = null;
      return true;
    }
    //return false if the user move wrong direction (invalid move)
    return false;
  }

  /* Method to check conditions to move a piece.
   * @Param     x1, initial column
   *            y1, initial row
   *            x2, final column (move to)
   *            y2, final row (move to) 
   *        board, Chess board 2D array      
   * @return  true, if the move is valid for the selected piece 
   *         false, if the move is invalid for the selected piece
  */
  private boolean takeWhitePiece (int x1, int y1, int x2, int y2, Piece[][] board) {
		//condition for the black piece, to take the white piece
    if (board[y2][x2].GetPieceType() > 0) {
			board[y2][x2] = board[y1][x1];
			board[y1][x1] = null;
			return true;  
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
		//condition for the white piece to take black piece
    if (board[y2][x2].GetPieceType() < 0) {
			board[y2][x2] = board[y1][x1];
			board[y1][x1] = null;
			return true;
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
   * @return bP, indicates black pawn.
   *         wP, indicates white pawn.
  */
  public String GetStr() {
    if(pieceType < 0) { //if the piece type is < 0, return black pawn string
      return "^bP^"; 
    }
    else { //if the piece type is > 0, return white pawn string
      return "_wP_";
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
    //special case (if the pawn has not moved from its original position, it is allowed to move 2 blocks forward )
    if (!hasMoved) {
      //if the desire block is not empty, and the movement is valid, return true (for white piece)
			if (board[y2][x2] != null && pieceType > 0 && y2 == y1 - 1 && (x2 == x1 + 1 || x2 == x1 - 1)) {
				this.hasMoved = true;
				return takeWhitePiece(x1, y1, x2, y2, board);
			}
      //if the desire block is not empty, and the movement is valid, return true (for black piece)
			if (board[y2][x2] != null && pieceType < 0 && y2 == y1 - 1 && (x2 == x1 + 1 || x2 == x1 - 1)) {
				this.hasMoved = true;
				return takeBlackPiece(x1, y1, x2, y2, board);
			}
      return firstMove(x1, y1, x2, y2, board);
    }
    //move the pawn 1 block forward
    if (board[y2][x2] == null && x2 == x1 && y2 == y1 - 1) {
      return movePawn(x1, y1, x2, y2, board);
    }
    //condition for black piece to take white piece diagonally
    if (board[y2][x2] != null && pieceType < 0 && y2 == y1 - 1 && (x2 == x1 + 1 || x2 == x1 - 1)) {
      return takeWhitePiece(x1, y1, x2, y2, board);
		}
    //condition for white piece to take black piece diagonally
    if (board[y2][x2] != null && pieceType > 0 && y2 == y1 - 1 && (x2 == x1 + 1 || x2 == x1 - 1)) {
      return takeBlackPiece(x1, y1, x2, y2, board);
		}
    return false;
  }
}