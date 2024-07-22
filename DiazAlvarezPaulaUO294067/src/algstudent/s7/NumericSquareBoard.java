package algstudent.s7;

import java.util.ArrayList;
import java.util.UUID;

public class NumericSquareBoard extends Node {
	private ArrayList<String> lines;
	private int linesWithOperations;
	private String[][] board;
	private boolean[][] canBeModified;
	private int row; // current row of this board
	private int column; // current column of this board
	private int size;
	private int counterSol = 0;
	private int emptySlots = 0;
	private int numberOfSolutions;

	public NumericSquareBoard(int linesWithOperations, int n) {
		this.linesWithOperations = linesWithOperations;

		this.size = n;
		this.row = 0;
		this.column = 0;
		board = new String[n][n];
		canBeModified = new boolean[n][n];
		// emptySlots = linesWithOperations * linesWithOperations;
	}

	@Override
	public int initialValuePruneLimit() {
		return emptySlots; // Implementation by default
	}

	/**
	 * Constructor for Pyramid puzzle objects (children of the root node)
	 * 
	 * @param board
	 * @param depth
	 * @param parentID
	 */
	public NumericSquareBoard(String[][] board, int depth, UUID parentID, int row, int column, int size,
			int heuristic) {
		this.board = board;
		this.depth = depth;
		this.parentID = parentID;
		this.row = row;
		this.column = column;
		this.size = size;
		calculateHeuristicValue(heuristic);
	}

	public void insertValues(String[] line, int row) {
		int posOperator = 0;

		for (int j = 0; j < size; j++) {
			if (row % 2 == 0 && row != size - 1) { // ? _ ? _ = ?
				board[row][j] = line[j];
				if (j % 2 == 0 && !board[row][j].equals("?") && !board[row][j].equals("=") && !board[row][j].equals("=")
						&& j != column - 1) {
					// emptySlots--;

				} else if (board[row][j].equals("?")) {
					canBeModified[row][j] = true;
					emptySlots++;
				}

			} else { // _ _
				if (j % 2 == 0 && posOperator < line.length) { // Write operator
					board[row][j] = line[posOperator];
					posOperator++;

				} else {
					board[row][j] = " ";
				}
			}
		}

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				sb.append(board[i][j] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Counts the number of blanks that are not yet filled
	 * 
	 * @param heuristic
	 */
	@Override
	public void calculateHeuristicValue(int heuristic) {
//		int counterQuestions = 0;
		if (prune())
			heuristicValue = Integer.MAX_VALUE;
		else {
//			for (int i = 0; i < size - 2; i += 2) {
//				for (int j = 0; j < size - 2; j += 2) {
//					if (board[i][j].equals("?"))
//						counterQuestions++; // count the values that are empty in the pyramid
//				}
//			}
			heuristicValue = heuristic - 1;
		}
	}

	@Override
	public void calculateHeuristicValue() {
		int counterQuestions = 0;
		if (prune())
			heuristicValue = Integer.MAX_VALUE;
		else {
			for (int i = 0; i < size - 2; i += 2) {
				for (int j = 0; j < size - 2; j += 2) {
					if (board[i][j].equals("?"))
						counterQuestions++; // count the values that are empty in the pyramid
				}
			}
			heuristicValue = counterQuestions;
		}
	}

	/**
	 * Checks if we should prune when the conditions are not longer met
	 * 
	 * @return True if we should prune. False otherwise
	 */
	private boolean prune() {
		for (int i = 0; i < size - 2; i += 2) { // last row that I will check are the ones that I already filled
			boolean valid = validRow(i);
			if (!valid)
				return true; // I want to prune, the numbers are not valid
//			for (int j=0; j<size-2;j+=2) {
//				valid=validColumn(j);
//				if(!valid) return true; //I want to prune, the numbers are not valid
//			}
			// NEW:
			valid = validColumn(i);
			if (!valid)
				return true; // I want to prune, the numbers are not valid
		}
		return false; // everything OK, don't prune
	}

	@Override
	public boolean isSolution() {
		return (heuristicValue == 0) ? true : false; // Fill all the numbers
	}

	/**
	 * To get the children of the current node. They point to their parent through
	 * the parentID
	 */
	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> result = new ArrayList<>();
		String[][] newBoard;
		NumericSquareBoard temp;
		while (!board[row][column].equals("?")) { // if it's a fixed position, we move to the next
			if (column < size - 2) { // we go to the next column in the same row
				column++;
			} else { // if column==0 it means we should go to the previous row
				row++;
				column = 0;
			}
		}
		for (int k = 0; k <= 9; k++) { // ) child nodes
			newBoard = copyBoard(row, column, ("" + k));
			// depth is to know the depth of the tree (the root is depth 0, the next level
			// is 1...)
			// the id is a universal id, so we cannot have 2 nodes with the same id
			temp = new NumericSquareBoard(newBoard, depth + 1, this.getID(), row, column, size, heuristicValue);
			result.add(temp);
		}
		return result;
	}

	private String[][] copyBoard(int row, int column, String k) {
		String[][] newBoard = new String[size][size];

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				newBoard[i][j] = board[i][j];

		newBoard[row][column] = k;
		return newBoard;
	}

	protected boolean validRow(int i) {
		int result = 0;
		int sol = Integer.parseInt(board[i][size - 1]);
		for (int j = 0; j < size - 1; j += 2) {
			if (board[i][j].equals("?")) // empty slots
				return true;
			if (j == 0) // first number
				result = Integer.parseInt(board[i][j]);
			else {
				char op = board[i][j - 1].charAt(0); // get the operation
				switch (op) {
				case '+':
					result += Integer.parseInt(board[i][j]);
					break;
				case '-':
					result -= Integer.parseInt(board[i][j]);
					break;
				case '*':
					result *= Integer.parseInt(board[i][j]);
					break;
				case '/':
					if (Integer.parseInt(board[i][j]) == 0) {
						return false;
					}
					if (result % Integer.parseInt(board[i][j]) != 0)
						return false;
					result /= Integer.parseInt(board[i][j]);
					break;
				}
			}
		}

		if (result != sol) {
			return false;
		}
		return true;
	}

	protected boolean validColumn(int j) {
		int result = 0;
		int sol = Integer.parseInt(board[size - 1][j]);

		for (int i = 0; i < size - 1; i += 2) {
			if (board[i][j].equals("?")) // empty slots
				return true;
			if (i == 0)
				result = Integer.parseInt(board[i][j]);
			else {
				char op = board[i - 1][j].charAt(0);
				switch (op) {
				case '+':
					result += Integer.parseInt(board[i][j]);
					break;
				case '-':
					result -= Integer.parseInt(board[i][j]);
					break;
				case '*':
					result *= Integer.parseInt(board[i][j]);
					break;
				case '/':
					if (Integer.parseInt(board[i][j]) == 0) {
						return false;
					}
					if (result % Integer.parseInt(board[i][j]) != 0)
						return false;
					result /= Integer.parseInt(board[i][j]);
					break;
				}
			}
		}

		if (result != sol) {
			return false;
		}
		return true;
	}

	@Override
	protected String Board(int i, int j) {
		return board[i][j];
	}
}
