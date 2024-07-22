package algstudent.s6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class NumericSquareOne {
	private ArrayList<String> lines;
	private int size;
	private int linesWithOperations;
	private String[][] board;
	private boolean solutionFound;
	private int counterSol = 0;
	private int emptySlots = 0;
	// private int[] solution;
	private boolean[][] canBeModified;
	private int numberNodes;

//	// VERSION 2
//	private String[][] boardSolved;
//	private int[] resultsRows;
//	private int[] resultsCols;

	public NumericSquareOne(String path) {
		readFromFile(path);
		board = new String[size][size];
		// solution = new int[linesWithOperations * linesWithOperations];
		canBeModified = new boolean[size][size];
		emptySlots = linesWithOperations * linesWithOperations;

		// VERSION 2
//		boardSolved = new String[size][size];
//		resultsRows = new int[linesWithOperations];
//		resultsCols = new int[linesWithOperations];

		for (int i = 0; i < size; i++) {
			String[] line = lines.get(i).split(" ");
			int posOperator = 0;
//			if (i < solution.length) {
//				solution[i] = -1;
//			}
//			int counterSol = 0;
			for (int j = 0; j < size; j++) {
				if (i % 2 == 0 && i != size - 1) { // ? _ ? _ = ?
					board[i][j] = line[j];
//					boardSolved[i][j] = line[j];// Version 2
					if (j % 2 == 0 && !board[i][j].equals("?") && !board[i][j].equals("=") && !board[i][j].equals("=")
							&& j != size - 1) {
//						solution[counterSol] = Integer.parseInt(board[i][j]);
//						counterSol++;
						emptySlots--;

					} else if (board[i][j].equals("?")) {
//						boardSolved[i][j] = "0";
						canBeModified[i][j] = true;
					}

				} else { // _ _
					if (j % 2 == 0 && posOperator < line.length) { // Write operator
						board[i][j] = line[posOperator];
//						boardSolved[i][j] = line[posOperator];// Version 2
						posOperator++;

					} else {
						board[i][j] = " ";
//						boardSolved[i][j] = " ";
					}
				}
			}
		}

	}

//	int i = 0;
//	int j = 0;

	public void findSolution() {
		numberNodes = 0;
		counterSol = 0;
		solutionFound = false;
//		backtracking();
		backtracking(0, 0, 0);
	}

	private void backtracking(int i, int j, int numberNodes) {
//		int currentRow = elementPos / size;
//		int currentCol = elementPos % size;
		if (j >= size - 2) { // we reached the = number
			i = i + 2;
			j = 0;
		}
		if (counterSol == emptySlots) { // Solution found
			System.out.println("Number of developed nodes: " + numberNodes);
			solutionFound = true;
			printSolution(); // O(n^2)
		} else {
			if (canBeModified[i][j]) {
				for (int k = 0; k <= 9; k++) { // Possible numbers O(n)
					numberNodes++;
					board[i][j] = "" + k;
					if (!solutionFound && validRow(i) && validColumn(j)) {
						counterSol++;
						backtracking(i, j + 2, numberNodes); // Recursive call
						if (solutionFound) {
							break;
						}
						// solution[counterSol] = -1;
						board[i][j] = "?";
						counterSol--;

					} else {
						board[i][j] = "?";
					}

				}
			} else if (!solutionFound) { // already given number or operation
				backtracking(i, j + 2, numberNodes);
			}
		}
	}

	protected boolean validRow(int i) {
//		int counter = 0;
		int result = 0;
		int sol = Integer.parseInt(board[i][size - 1]);
		for (int j = 0; j < size - 1; j += 2) {
			// int pos = i * (counterSol - 1) + counter;
//			int pos = i * linesWithOperations / 2 + counter;
			if (board[i][j].equals("?")) // empty slots
				return true;
			if (j == 0) // first number
				result = Integer.parseInt(board[i][j]);
//				result = solution[pos];
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
//			counter++;
		}

		if (result != sol) {
			return false;
		}
		return true;
	}

	protected boolean validColumn(int j) {
//		int counter = 0;
		int result = 0;
		int sol = Integer.parseInt(board[size - 1][j]);
		// int pos = j % linesWithOperations;
		// int pos = j / linesWithOperations;
		// nt pos = j - j / linesWithOperations;
//		int pos = counterSol % linesWithOperations;
		for (int i = 0; i < size - 1; i += 2) {
			// int pos = counter * linesWithOperations + j;
			// int pos = counter * linesWithOperations / 2 + j;

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
//			counter++;
//			pos += linesWithOperations;
		}

		if (result != sol) {
			return false;
		}
		return true;
	}

	public boolean checkSolution() {
		for (int i = 0; i < size - 1; i += 2) {
			if (!validRow(i))
				return false;
			if (!validColumn(i))
				return false;
		}
		return true;
	}

	public void printBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void printSolution() {
		if (!solutionFound) {
			System.out.println("SOLUTION NOT FOUND");
			return;
		}
		System.out.println("SOLUTION FOUND");
//		int counterSol = 0;
//		for (int i = 0; i < size; i++) {
//			for (int j = 0; j < size; j++) {
////				if (board[i][j].equals("?")) {
////					System.out.print(solution[counterSol] + " ");
////					counterSol++;
//				if (i % 2 == 0 && i < size - 2 && j % linesWithOperations == 0 && j < size - 2) {
//					System.out.print(solution[counterSol] + " ");
//					counterSol++;
//				} else {
//					System.out.print(board[i][j] + " ");
//				}
//			}
//			System.out.println();
//		}
		printBoard();
	}

	private void readFromFile(String fileName) {
		lines = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			while (reader.ready()) {
				lines.add(reader.readLine());
			}
			size = Integer.parseInt(lines.get(0)) * 2 + 1;
			linesWithOperations = Integer.parseInt(lines.get(0));
			lines.remove(0);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
