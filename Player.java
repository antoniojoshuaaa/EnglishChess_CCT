public class Player {
  final static String W_PLAYER = "WHITE";
  final static String B_PLAYER = "BLACK";
  private int x;
  private int y;
  private String color;

  // ================ CONSTRUCTOR ================ //
  public Player (String color) {
 	 this.color = color;
  }
  // ================ PRIVATE METHODS ================ //

  // ================ PUBLIC METHODS ================ //
	

  /* Method to get the color of the player.       
   * @return the player colour (white/black)
   */
	public String GetColor() {
		return this.color;
	}

  /* Method to set/keep track of the king's position.       
   * @param x, the column of the king position (A-H)
            y, the row of the king position (1-8)
   */
	public void SetKingPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

  /* Method to set/keep track of the king's position.       
   * @return x, the column of the king position (A-H)
   *         y, the row of the king position (1-8)
   */
	public String GetKingPos() {
		return this.x + "" + this.y;
	}
}