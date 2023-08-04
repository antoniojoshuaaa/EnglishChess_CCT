public abstract class Piece {
  private int pieceType;
  private boolean hasMoved;
  
  final static int W_PAWN = 1;
  final static int W_KNIGHT = 2;
  final static int W_BISHOP = 3;
  final static int W_ROOK = 4;
  final static int W_QUEEN = 5;
  final static int W_KING = 6;

  final static int B_PAWN = -1;
  final static int B_KNIGHT = -2;
  final static int B_BISHOP = -3;
  final static int B_ROOK = -4;
  final static int B_QUEEN = -5;
  final static int B_KING = -6;

  // ================ CONSTRUCTOR ================ //
  public Piece() {
  }

  // ================ PRIVATE METHODS ================ //

  // ================ PUBLIC METHODS ================ //
  
  /* Method to get the piece type.       
   * @return pieceType, a value that represents certain piece 
   */
  public int GetPieceType () {
    return this.pieceType;
  }

  /* Method to set the piece to has moved.       
   * @param hasMoved, set the hasMoved into yes 
   */
  public void SetHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

  /* Method to get the has moved.       
   * @return hasMoved, status that the piece has moved or not 
   */
	public boolean GetHasMoved() {
		return this.hasMoved;
	}

  /* Abstract method to get the string of the piece type to be overriden */
  public abstract String GetStr();

  /* Abstract method to move a pice to be overriden.
   * @Param   x1. , initial column
   *          y1. , initial row
   *          x2. , final column (move to)
   *          y2  , final row (move to) 
   *        board , Chess board 2D array      
   * @return  true, if the move is valid for the selected piece 
   *         false, if the move is invalid for the selected piece
  */
  public abstract boolean Move(int x1, int y1, int x2, int y2, Piece[][] board);
}