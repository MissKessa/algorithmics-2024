package topics.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * ChessQueensAll JUnit tests
 * 
 * @author viceg
 */
public class ChessHorseAllTest {
	private ChessHorseAll chess;

	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		System.out.println("Chess Horse All Tests - Setup");
	}

	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		System.out.println("Chess Horse All - Teardown");
	}

	/**
	 * Indicates whether it is possible move the horse through the complete board
	 * 5x5
	 */
	@Test
	public void testChessHorseOk() {
		int startingX = 0;
		int startingY = 0;

		chess = new ChessHorseAll(5, startingX, startingY);
		chess.backtracking(2, startingX, startingY);
		int result = chess.getNumberOfSolutions();
		assertEquals(304, result);
	}

	/**
	 * Indicates whether it is possible move the horse through the complete board
	 * 5x5
	 */
	@Test
	public void testChessHorseNo() {
		int startingX = 1;
		int startingY = 4;

		chess = new ChessHorseAll(5, startingX, startingY);
		chess.backtracking(2, startingX, startingY);
		int result = chess.getNumberOfSolutions();
		assertEquals(0, result);
	}

}
