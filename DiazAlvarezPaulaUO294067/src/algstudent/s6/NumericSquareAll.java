package algstudent.s6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class NumericSquareAll {
	private ArrayList<String> lines;
	private int size;
	private int linesWithOperations;
	private String[][] board;
	private int counterSol = 0;
	private int emptySlots = 0;
	private boolean[][] canBeModified;
	private int numberOfSolutions;

	public NumericSquareAll(String path) {
		readFromFile(path);
		board = new String[size][size];
		canBeModified = new boolean[size][size];
		emptySlots = linesWithOperations * linesWithOperations;

		for (int i = 0; i < size; i++) {
			String[] line = lines.get(i).split(" ");
			int posOperator = 0;

			for (int j = 0; j < size; j++) {
				if (i % 2 == 0 && i != size - 1) { // ? _ ? _ = ?
					board[i][j] = line[j];
					if (j % 2 == 0 && !board[i][j].equals("?") && !board[i][j].equals("=") && !board[i][j].equals("=")
							&& j != size - 1) {
						emptySlots--;

					} else if (board[i][j].equals("?")) {
						canBeModified[i][j] = true;
					}

				} else { // _ _
					if (j % 2 == 0 && posOperator < line.length) { // Write operator
						board[i][j] = line[posOperator];
						posOperator++;

					} else {
						board[i][j] = " ";
					}
				}
			}
		}
	}

	public void findSolution() {
		numberOfSolutions = 0;
		backtracking(0, 0);
		System.out.println("Number of solutions: " + numberOfSolutions);
	}

	private void backtracking(int i, int j) {
		if (j >= size - 2) { // we reached the = number
			i = i + 2;
			j = 0;
		}
		if (counterSol == emptySlots) { // Solution found
			numberOfSolutions++;
			printSolution(); // O(n^2)
		} else {
			if (canBeModified[i][j]) {
				for (int k = 0; k <= 9; k++) { // Possible numbers O(n)
					board[i][j] = "" + k;
					if (validRow(i) && validColumn(j)) {
						counterSol++;
						backtracking(i, j + 2); // Recursive call

						board[i][j] = "?";
						counterSol--;

					} else {
						board[i][j] = "?";
					}
				}
			} else { // already given number or operation
				backtracking(i, j + 2);
			}
		}
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

	public void printBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void printSolution() {
		System.out.println("SOLUTION " + numberOfSolutions + ":");
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
