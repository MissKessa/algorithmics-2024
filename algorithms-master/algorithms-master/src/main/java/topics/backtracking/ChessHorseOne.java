package topics.backtracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BACKTRACKING PROBLEM: THE HORSE JUMPING PROBLEM This program calculates all
 * the ways of moving a horse through an entire chessboard of side n
 * 
 * @author viceg
 */
public class ChessHorseOne {
	private static Logger log = LoggerFactory.getLogger(ChessHorseOne.class);
	private int n; // Size of the side of the board
	private int[][] board; // Chessboard
	private int[] a; // Displacements of horse (axis x)
	private int[] b; // Displacements of horse (axis y)
	private boolean found; // Solutions found

	/**
	 * Constructor for ChessHorseOne objects
	 * 
	 * @param n         Size of the board
	 * @param startingX starting position in the board (horizontally)
	 * @param startingY starting position in the board (vertically)
	 */
	public ChessHorseOne(int n, int startingX, int startingY) {
		this.n = n;

		board = new int[n][n]; // The cell (i,j) has not been visited
		board[startingX][startingY] = 1; // Initial position of the horse

		// VALID MOVES:
		a = new int[8]; // Valid movements in x
		b = new int[8]; // Valid movements in y
		a[0] = 1;
		b[0] = 2;
		a[1] = 2;
		b[1] = 1;
		a[2] = 2;
		b[2] = -1;
		a[3] = 1;
		b[3] = -2;
		a[4] = -1;
		b[4] = -2;
		a[5] = -2;
		b[5] = -1;
		a[6] = -2;
		b[6] = 1;
		a[7] = -1;
		b[7] = 2;

		found = false;
	}

	/**
	 * Performs the backtracking process
	 * 
	 * @param jumpNumber Number of jumps performed so far starting at 1
	 * @param x          Current x position
	 * @param y          Current y position
	 */
	public void backtracking(int jumpNumber, int x, int y) {
		if (jumpNumber == n * n + 1) { // At this moment the horse has finished (it has been all over the board)
			found = true;
			log.debug("SOLUTION FOUND:");
			// PRINT SOLUTION:
			StringBuilder sb = new StringBuilder();
			for (int s = 0; s < n; s++) {
				for (int t = 0; t < n; t++)
					sb.append(String.format("%5d", board[s][t]));
				sb.append("\n");
			}
			log.debug(sb.toString());
		} else
			for (int k = 0; k <= 7; k++) { // We have 8 possibilities (the different types of jumps of the chess horse).
											// It's the length of a and b

				// Calculate new position:
				int u = x + a[k]; // Target coordinate x of the horse
				int v = y + b[k]; // Target coordinate y of the horse

				// Check coordinates & that the tile hasn't been visited
				if (!found && u >= 0 && u < n && v >= 0 && v < n && board[u][v] == 0) { // !found -> a solution hasn't
																						// been found; u >= 0 && u < n
																						// && v >= 0 && v < n -> check
																						// valid positions; board[u][v]
																						// == 0 -> haven't go there
					board[u][v] = jumpNumber; // We mark the position with the number of the jump

					backtracking(jumpNumber + 1, u, v);

					board[u][v] = 0; // We leave it as it was (available to be used)
				}
			} // for
	} // method

	/**
	 * Returns whether there is a solution
	 * 
	 * @return True is there is a solution. False otherwise
	 */
	public boolean isSolution() {
		return found;
	}
}
