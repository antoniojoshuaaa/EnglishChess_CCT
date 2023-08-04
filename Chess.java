public class Chess {
	public final static int SIZE = 8;
	private Piece[][] board;
	private Player[] allPlayers;
	private Player currPlayer;

	// ================ CONSTRUCTOR ================ //
	public Chess() {
		allPlayers = new Player[2];
		allPlayers[0] = new Player(Player.W_PLAYER);
		allPlayers[1] = new Player(Player.B_PLAYER);
		allPlayers[0].SetKingPos(4, 7);
		allPlayers[1].SetKingPos(3, 7);
		currPlayer = allPlayers[0];
		board = new Piece[SIZE][SIZE];

		// Generate black pieces (rook,knight,bishop,queen,king) (1st row of the board)
		board[0][0] = new Rook(Piece.B_ROOK);
		board[0][1] = new Knight(Piece.B_KNIGHT);
		board[0][2] = new Bishop(Piece.B_BISHOP);
		board[0][3] = new Queen(Piece.B_QUEEN);
		board[0][4] = new King(Piece.B_KING);
		board[0][5] = new Bishop(Piece.B_BISHOP);
		board[0][6] = new Knight(Piece.B_KNIGHT);
		board[0][7] = new Rook(Piece.B_ROOK);

		// Generate black pawns (2nd row of the board)
		for (int col = 0; col < SIZE; col++) {
			board[1][col] = new Pawn(Piece.B_PAWN);
		}

		// Generate white pieces (rook,knight,bishop,queen,king) (8th row of the board)
		board[7][0] = new Rook(Piece.W_ROOK);
		board[7][1] = new Knight(Piece.W_KNIGHT);
		board[7][2] = new Bishop(Piece.W_BISHOP);
		board[7][3] = new Queen(Piece.W_QUEEN);
		board[7][4] = new King(Piece.W_KING);
		board[7][5] = new Bishop(Piece.W_BISHOP);
		board[7][6] = new Knight(Piece.W_KNIGHT);
		board[7][7] = new Rook(Piece.W_ROOK);

		// Generate white pawns (7th row of the board)
		for (int col = 0; col < SIZE; col++) {
			board[SIZE - 2][col] = new Pawn(Piece.W_PAWN);
		}
	}

	// ================ PRIVATE METHODS ================ //

	// ================ PUBLIC METHODS ================ //

	/*
	 * Method to check get the board object.
	 * 
	 * @return the board object
	 */
	public Piece[][] GetBoard() {
		return this.board;
	}

	/*
	 * Method to check conditions to move a piece.
	 * 
	 * @Param x1, initial column y1, initial row x2, final column (move to) y2,
	 * final row (move to)
	 * 
	 * @return 0, if the grid selected is empty. -1, if move is invalid 1, if
	 * successfully moved the piece
	 */
	public int MovePiece(int x1, int y1, int x2, int y2) {
		boolean movePiece;
		if (board[y1][x1] == null) { // if there is no piece in the selected block, return 0;
			return 0;
		} else { // if not empty, continue
			// to prevent white player to move black pieces, and otherwise
			if ((currPlayer.GetColor().equalsIgnoreCase("WHITE") && board[y1][x1].GetPieceType() > 0)
					|| (currPlayer.GetColor().equalsIgnoreCase("BLACK") && board[y1][x1].GetPieceType() < 0)) {
				movePiece = board[y1][x1].Move(x1, y1, x2, y2, this.board);
				if (movePiece) { // if the piece move successfully, return 1
					// if the
					if (board[y2][x2] instanceof King) {
						currPlayer.SetKingPos(x2, y2);
					}
					return 1;
				}
			}
			return -1; // if the piece failed to move
		}
	}

	/*
	 * Method to check conditions to promote a pawn.
	 * 
	 * @Param x2, final column (move to) y2, final row (move to)
	 * 
	 * @return true, if the pawn successfully promoted false, if the new piece is
	 * invalid
	 */

	public boolean PawnPromotion(int x2, int y2, String piece, Piece[][] board) {
		int pawn = board[y2][x2].GetPieceType(); // to shorten the piece type
		if (y2 == 0 && pawn < 0) { // if the pawn goes to the oppsite board and it's black pawn
			if (piece.equalsIgnoreCase("bishop")) { // promote the black pawn into black bishop
				board[y2][x2] = new Bishop(Piece.B_BISHOP);
				return true;
			} else if (piece.equalsIgnoreCase("knight")) { // promote the black pawn into black knight
				board[y2][x2] = new Knight(Piece.B_KNIGHT);
				return true;
			} else if (piece.equalsIgnoreCase("rook")) { // promote the black pawn into black rook
				board[y2][x2] = new Rook(Piece.B_ROOK);
				return true;
			} else if (piece.equalsIgnoreCase("queen")) { // promote the black pawn into black queen
				board[y2][x2] = new Queen(Piece.B_QUEEN);
				return true;
			} else { // error and return false if the user wants the pawn to be promoted into
						// pawn/king
				return false;
			}
		} else if (y2 == 0 && pawn > 0) { // if the pawn goes to the oppsite board and it's white pawn
			if (piece.equalsIgnoreCase("bishop")) { // promote the white pawn into white bishop
				board[y2][x2] = new Bishop(Piece.W_BISHOP);
				return true;
			} else if (piece.equalsIgnoreCase("knight")) { // promote the white pawn into white knight
				board[y2][x2] = new Knight(Piece.W_KNIGHT);
				return true;
			} else if (piece.equalsIgnoreCase("rook")) { // promote the white pawn into white rook
				board[y2][x2] = new Rook(Piece.W_ROOK);
				return true;
			} else if (piece.equalsIgnoreCase("queen")) { // promote the white pawn into white queen
				board[y2][x2] = new Queen(Piece.W_QUEEN);
				return true;
			} else { // error and return false if the user wants the pawn to be promoted into
						// pawn/king
				return false;
			}
		}
		return false; // to satisfy the return statement requirement
	}

	/* Method to flip the board for black player */
	public void FlipBoard() {
		Piece[][] flippedBoard = new Piece[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) { // to create rows of the board
			for (int col = 0; col < SIZE; col++) { // to create columns of the board
				flippedBoard[row][col] = board[SIZE - row - 1][SIZE - col - 1]; // swap white pieces and black pieces
																				// location
			}
		}
		board = flippedBoard;
	}

	/* Method to reset the board */
	public void ResetBoard() {
		board = new Piece[SIZE][SIZE];

		// Generate black pieces (rook,knight,bishop,queen,king) (1st row of the board)

		board[0][0] = new Rook(Piece.B_ROOK);
		board[0][1] = new Knight(Piece.B_KNIGHT);
		board[0][2] = new Bishop(Piece.B_BISHOP);
		board[0][3] = new Queen(Piece.B_QUEEN);
		board[0][4] = new King(Piece.B_KING);
		board[0][5] = new Bishop(Piece.B_BISHOP);
		board[0][6] = new Knight(Piece.B_KNIGHT);
		board[0][7] = new Rook(Piece.B_ROOK);

		// Generate black pawns (2nd row of the board)
		for (int col = 0; col < SIZE; col++) {
			board[1][col] = new Pawn(Piece.B_PAWN);
		}

		// // Generate white pieces (rook,knight,bishop,queen,king) (8th row of the
		// board)
		board[7][0] = new Rook(Piece.W_ROOK);
		board[7][1] = new Knight(Piece.W_KNIGHT);
		board[7][2] = new Bishop(Piece.W_BISHOP);
		board[7][3] = new Queen(Piece.W_QUEEN);
		board[7][4] = new King(Piece.W_KING);
		board[7][5] = new Bishop(Piece.W_BISHOP);
		board[7][6] = new Knight(Piece.W_KNIGHT);
		board[7][7] = new Rook(Piece.W_ROOK);

		// Generate white pawns (7th row of the board)
		for (int col = 0; col < SIZE; col++) {
			board[SIZE - 2][col] = new Pawn(Piece.W_PAWN);
		}
	}

	/* Method to switch the player (black to white, white to black) */
	public void SwitchPlayer() {
		// if the current player is white , it switches to black player
		if (currPlayer.GetColor().equalsIgnoreCase("WHITE")) {
			currPlayer = allPlayers[1];
		} else { // if the curreent player is black, it switches to white player
			currPlayer = allPlayers[0];
		}
	}

	/*
	 * Method to get the colour of current player.
	 *
	 * @return the colour of the current player
	 */
	public String GetCurrPlayerColor() {
		return this.currPlayer.GetColor();
	}

	/*
	 * Method to check has winner.
	 * 
	 * @return 6, indicates the winner is white king (still on the board) -6,
	 * indicates the winenr is black king (still on the board) 0, if there are 2
	 * kings (white and black) on the board
	 */
	public int HasWinner() {
		int num = 0; // variable to track which player is the winner
		for (int row = 0; row < 8; row++) { // check every row
			for (int col = 0; col < 8; col++) { // check every column
				if (board[row][col] == null) {
				} else if (board[row][col].GetPieceType() == Piece.W_KING) { // if white king is still on the board, 1
																				// is added to num variable
					num += 6;
				} else if (board[row][col].GetPieceType() == Piece.B_KING) { // if black king is still on the board, -1
																				// is added to num variable
					num += -6;
				}
			}
		}
		if (num == Piece.W_KING) { // if only white king is still on the board, white player is the winner
			return Piece.W_KING;
		} else if (num == Piece.B_KING) { // if only black king is still on the board, black player is the winner
			return Piece.B_KING;
		} else { // if num variable contains 0, there is no winner, the game is keep going
			return num;
		}
	}

	/*
	 * Method to scan the board ('check' for the king)
	 * 
	 * @return true, if the king is currently in check/threat false, if the king is
	 * safe
	 */
	public boolean ScanCheck() {
		// scan upwards
		int x = (int) currPlayer.GetKingPos().charAt(0) - 48;
		int y = (int) currPlayer.GetKingPos().charAt(1) - 48;

		for (int i = y - 1; i >= 0; i--) {
			// to prevent null pointer exception
			if (board[i][x] != null) {
				// to check whether the current player is white of black
				if (currPlayer.GetColor().equalsIgnoreCase("Black")) {
					if (board[i][x].GetPieceType() == Piece.W_QUEEN || board[i][x].GetPieceType() == Piece.W_ROOK) {
						return true; // player black is in check.
					}
				} else if (currPlayer.GetColor().equalsIgnoreCase("White")) {
					if (board[i][x].GetPieceType() == Piece.B_QUEEN || board[i][x].GetPieceType() == Piece.B_ROOK) {
						return true; // player white is in check.
					}
				}
				break;
			}
		}
		// scan downwards
		for (int i = y + 1; i < SIZE; i++) {
			// to prevent null pointer exception
			if (board[i][x] != null) {
				// to check whether the current player is white of black
				if (currPlayer.GetColor().equalsIgnoreCase("Black")) {
					if (board[i][x].GetPieceType() == Piece.W_QUEEN || board[i][x].GetPieceType() == Piece.W_ROOK) {
						return true; // player black is in check.
					}
				} else if (currPlayer.GetColor().equalsIgnoreCase("White")) {
					if (board[i][x].GetPieceType() == Piece.B_QUEEN || board[i][x].GetPieceType() == Piece.B_ROOK) {
						return true; // player white is in check.
					}
				}
				break;
			}
		}
		// scan right-ward..?
		for (int i = x + 1; i < SIZE; i++) {
			// to prevent null pointer exception
			if (board[y][i] != null) {
				// to check whether the current player is white of black
				if (currPlayer.GetColor().equalsIgnoreCase("Black")) {
					if (board[y][i].GetPieceType() == Piece.W_QUEEN || board[y][i].GetPieceType() == Piece.W_ROOK) {
						return true; // player black is in check.
					}
				} else if (currPlayer.GetColor().equalsIgnoreCase("White")) {
					if (board[y][i].GetPieceType() == Piece.B_QUEEN || board[y][i].GetPieceType() == Piece.B_ROOK) {
						return true; // player white is in check.
					}
				}
				break;
			}
		}
		// scan left-wards...?
		for (int i = x - 1; i >= 0; i--) {
			// to prevent null pointer exception
			if (board[y][i] != null) {
				// to check whether the current player is white of black
				if (currPlayer.GetColor().equalsIgnoreCase("Black")) {
					if (board[y][i].GetPieceType() == Piece.W_QUEEN || board[y][i].GetPieceType() == Piece.W_ROOK) {
						return true; // player black is in check.
					}
				} else if (currPlayer.GetColor().equalsIgnoreCase("White")) {
					if (board[y][i].GetPieceType() == Piece.B_QUEEN || board[y][i].GetPieceType() == Piece.B_ROOK) {
						return true; // player white is in check.
					}
				}
				break;
			}
		}
		// scan up right diagonal
		int j = x + 1;
		for (int i = y - 1; i >= 0; i--) {
			// to prevent out of bounds exception
			if (j > 7) {
				break;
			}
			// to prevent null pointer exception
			if (board[y - 1][x + 1] != null) {
				// to check whether the current player is white of black
				// if it's black, check for white pawns
				if (board[y - 1][x + 1].GetPieceType() == Piece.W_PAWN
						&& currPlayer.GetColor().equalsIgnoreCase("Black")) {
					return true;
				}
				if (board[y - 1][x + 1].GetPieceType() == Piece.B_PAWN
						&& currPlayer.GetColor().equalsIgnoreCase("White")) {
					return true;
				}
			}
			// to prevent null pointer exception
			if (board[i][j] != null) {
				// to check whether the current player is white of black
				if (currPlayer.GetColor().equalsIgnoreCase("Black")) {
					// if it's black, check for white queens/bishops
					if (board[i][j].GetPieceType() == Piece.W_QUEEN || board[i][j].GetPieceType() == Piece.W_BISHOP) {
						return true; // player black is in check.
					}
				} else if (currPlayer.GetColor().equalsIgnoreCase("White")) {
					if (board[i][j].GetPieceType() == Piece.B_QUEEN || board[i][j].GetPieceType() == Piece.B_BISHOP) {
						return true; // player white is in check.
					}
				}
				break;
			}
			j++;
		}
		// scan up left
		j = x - 1;
		for (int i = y - 1; i >= 0; i--) {
			// to prevent out of bounds exception
			if (j < 0) {
				break;
			}
			// to prevent null pointer exception
			if (board[y - 1][x - 1] != null) {
				// to check whether the current player is white of black
				// if it's black, check for white pawns
				if (board[y - 1][x - 1].GetPieceType() == Piece.W_PAWN
						&& currPlayer.GetColor().equalsIgnoreCase("Black")) {
					return true;
				}
				if (board[y - 1][x - 1].GetPieceType() == Piece.B_PAWN
						&& currPlayer.GetColor().equalsIgnoreCase("White")) {
					return true;
				}
			}
			// to prevent null pointer exception
			if (board[i][j] != null) {
				// to check whether the current player is white of black
				if (currPlayer.GetColor().equalsIgnoreCase("Black")) {
					// if it's black, check for white queens/bishops
					if (board[i][j].GetPieceType() == Piece.W_QUEEN || board[i][j].GetPieceType() == Piece.W_BISHOP) {
						return true; // player black is in check.
					}
				} else if (currPlayer.GetColor().equalsIgnoreCase("White")) {
					if (board[i][j].GetPieceType() == Piece.B_QUEEN || board[i][j].GetPieceType() == Piece.B_BISHOP
					/* || board[y - 1][x - 1].GetPieceType() == Piece.B_PAWN */) {
						return true; // player white is in check.
					}
				}
				break;
			}
			j--;
		}
		// scan down right
		j = x + 1;
		for (int i = y + 1; i < SIZE; i++) {
			// to prevent out of bounds exception
			if (j > 7) {
				break;
			}
			// to prevent null pointer exception
			if (board[i][j] != null) {
				// to check whether the current player is white of black
				if (currPlayer.GetColor().equalsIgnoreCase("Black")) {
					// if it's black, check for white queens/bishops
					if (board[i][j].GetPieceType() == Piece.W_QUEEN || board[i][j].GetPieceType() == Piece.W_BISHOP) {
						return true; // player black is in check.
					}
				} else if (currPlayer.GetColor().equalsIgnoreCase("White")) {
					if (board[i][j].GetPieceType() == Piece.B_QUEEN || board[i][j].GetPieceType() == Piece.B_BISHOP) {
						return true; // player white is in check.
					}
				}
				break;
			}
			j++;
		}
		// scan down left
		j = x - 1;
		for (int i = y + 1; i < SIZE; i++) {
			// to prevent out of bounds exception
			if (j < 0) {
				break;
			}
			// to prevent null pointer exception
			if (board[i][j] != null) {
				// to check whether the current player is white of black
				if (currPlayer.GetColor().equalsIgnoreCase("Black")) {
					// if it's black, check for white queens/bishops
					if (board[i][j].GetPieceType() == Piece.W_QUEEN || board[i][j].GetPieceType() == Piece.W_BISHOP) {
						return true; // player black is in check.
					}
				} else if (currPlayer.GetColor().equalsIgnoreCase("White")) {
					// if it's black, check for white queens/bishops
					if (board[i][j].GetPieceType() == Piece.B_QUEEN || board[i][j].GetPieceType() == Piece.B_BISHOP) {
						return true; // player white is in check.
					}
				}
				break;
			}
			j--;
		}

		// scan for knights (including for loop)
		int[][] data = { { 1, -2 }, { 2, -1 }, { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }, { -2, -1 }, { -1, -2 } };
		for (int i = 0; i < data.length; i++) {
			int dx = x + data[i][0];
			int dy = y + data[i][1];

			if (dx >= 0 && dx <= 7 && dy >= 0 && dy <= 7) {
				if (board[dy][dx] == null)
					;
				else if ((currPlayer.GetColor().equals("BLACK") && board[dy][dx].GetPieceType() == Piece.W_KNIGHT)
						|| (currPlayer.GetColor().equals("WHITE") && board[dy][dx].GetPieceType() == Piece.B_KNIGHT)) {
					return true;
				}
			}
		}
		return false;
	}
}