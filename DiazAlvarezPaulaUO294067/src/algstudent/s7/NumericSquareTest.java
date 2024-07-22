package algstudent.s7;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

/**
 * JUnit Test for Pyramid Puzzle
 */
public class NumericSquareTest {
	@Test
	public void test00() {
		boolean result = executeFromFile("src/algstudent/s7/test00.txt");
		assertEquals(true, result);
	}

	@Test
	public void test01() {
		boolean result = executeFromFile("src/algstudent/s7/test01.txt");
		assertEquals(true, result);
	}

	@Test
	public void test02() {
		boolean result = executeFromFile("src/algstudent/s7/test02.txt");
		assertEquals(true, result);
	}

	@Test
	public void test03() {
		boolean result = executeFromFile("src/algstudent/s7/test03.txt");
		assertEquals(true, result);
	}

	@Test
	public void test04() {
		boolean result = executeFromFile("src/algstudent/s7/test04.txt");
		assertEquals(true, result);
	}

	@Test
	public void test05() {
		boolean result = executeFromFile("src/algstudent/s7/test05.txt");
		assertEquals(true, result);
	}

	@Test
	public void test06() {
		boolean result = executeFromFile("src/algstudent/s7/test06.txt");
		assertEquals(true, result);
	}

	@Test
	public void test07() {
		boolean result = executeFromFile("src/algstudent/s7/test07.txt");
		assertEquals(true, result);
	}

	/**
	 * Reads the initial pyramid from a text file and creates an object to deal with
	 * the problem
	 * 
	 * @param file File from which
	 * @return True if we find a solution for the problem, false otherwise
	 */
	private boolean executeFromFile(String file) {
		boolean result = false;
		// input
		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			while (reader.ready()) {
				lines.add(reader.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		int size = Integer.parseInt(lines.get(0)) * 2 + 1;
		int linesWithOperations = Integer.parseInt(lines.get(0));
		lines.remove(0);

		NumericSquareBoard board = new NumericSquareBoard(linesWithOperations, size);

		// next lines
		for (int i = 0; i < size; i++) {
			String[] values = lines.get(i).split(" ");
			board.insertValues(values, i);
		}

		NumericSquarePuzzle puzzle = new NumericSquarePuzzle(board);

		long t3 = System.currentTimeMillis();
		puzzle.branchAndBound(puzzle.getRootNode());
		long t4 = System.currentTimeMillis();
		System.out.println("Time solution:" + (t4 - t3) + "ms");
		puzzle.printSolutionTrace();

		result = puzzle.getBestNode() != null ? true : false;

		return result;
	}
//	private void readFromFile(String fileName) {
//		lines = new ArrayList<String>();
//		BufferedReader reader = null;
//		try {
//			reader = new BufferedReader(new FileReader(fileName));
//			while (reader.ready()) {
//				lines.add(reader.readLine());
//			}
//			size = Integer.parseInt(lines.get(0)) * 2 + 1;
//			linesWithOperations = Integer.parseInt(lines.get(0));
//			lines.remove(0);
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//	}
}
