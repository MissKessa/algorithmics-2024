package algstudent.s3;

import java.util.Random;

public class Tromino {

	private static final int STARTING_POINT = -1;
	private static final int STARTING_TROMINO = 1;
	private static final int FIRST_CUADRANT = 1;
	private static final int THIRD_CUADRANT = 3;
	private static final int FOURTH_CUADRANT = 4;
	private static final int SECOND_CUADRANT = 2;
	private static final int ERROR = -2;
	private static int[][] array;
	private static int counter = 1;

	public static void main(String[] args) {
		int size = 16;
		Random r = new Random();
		tromino(size, r.nextInt(size), r.nextInt(size) );
		print();
	}

	static void tromino(int size, int x, int y) {
		initialize(size, x, y);
		int startingCuad = printStartingTromino(size, x, y);
		switch (startingCuad) {
		case FIRST_CUADRANT:
			tromino(size / 2, 0, size / 2 - 1, 0, size / 2, FIRST_CUADRANT); // second cuad
			tromino(size / 2, x, y, 0, 0, SECOND_CUADRANT);
			tromino(size / 2, size / 2 - 1, 0, 0, size / 2, THIRD_CUADRANT); // third cuad
			tromino(size / 2, 0, 0, size / 2, size / 2, FOURTH_CUADRANT); // fourth cuad
			break;

		case SECOND_CUADRANT:
			tromino(size / 2, size / 2 - 1, size / 2 - 1, 0, 0, FIRST_CUADRANT); // first cuad
			tromino(size / 2, x - size / 2, y, size / 2, 0, SECOND_CUADRANT); // second cuad
			tromino(size / 2, size / 2 - 1, 0, 0, size / 2, THIRD_CUADRANT); // third cuad
			tromino(size / 2, 0, 0, size / 2, size / 2, FOURTH_CUADRANT); // fourth cuad
			break;

		case THIRD_CUADRANT:
			tromino(size / 2, size / 2 - 1, size / 2 - 1, 0, 0, FIRST_CUADRANT); // first cuad
			tromino(size / 2, 0, size / 2 - 1, size / 2, 0, SECOND_CUADRANT); // second cuad
			tromino(size / 2, x, y - size / 2, 0, size / 2, THIRD_CUADRANT); // third cuad
			tromino(size / 2, 0, 0, size / 2, size / 2, FOURTH_CUADRANT); // fourth cuad
			break;

		case FOURTH_CUADRANT:
			tromino(size / 2, size / 2 - 1, size / 2 - 1, 0, 0, FIRST_CUADRANT); // first cuad
			tromino(size / 2, 0, size / 2 - 1, size / 2, 0, SECOND_CUADRANT); // second cuad
			tromino(size / 2, size / 2 - 1, 0, 0, size / 2, THIRD_CUADRANT); // third cuad
			tromino(size / 2, x - size / 2, y - size / 2, size / 2, size / 2, FOURTH_CUADRANT); // fourth cuad
			break;

		default:
			System.err.println("THERE'S BEEN AN ERROR: ");
			System.err.println(" Starting cuad: " + startingCuad);

			break;
		}
//		print();

	}

	private static void tromino(int size, int xPos, int yPos, int xOrig, int yOrig, int cuadrant) {
		if (size == 2) {
			for (int i = 0; i < 2; i++)
				for (int j = 0; j < 2; j++)
					if (array[xOrig + i][yOrig + j] == 0)
						array[xOrig + i][yOrig + j] = counter;
			counter++;
		} else {
			switch (cuadrant) {
			case FIRST_CUADRANT:

				tromino(size / 2, yOrig, xOrig, yOrig + size / 2, xOrig + size / 2, THIRD_CUADRANT); // fourth cuad
				printStartingTromino(size, yOrig, xOrig, yOrig, xOrig);
				tromino(size / 2, yOrig, xOrig, yOrig + size / 2, xOrig, FIRST_CUADRANT); // fourth cuad
				tromino(size / 2, yOrig, xOrig, yOrig, xOrig + size / 2, SECOND_CUADRANT); // fourth cuad
				tromino(size / 2, yOrig, xOrig, yOrig, xOrig, FOURTH_CUADRANT); // fourth cuad
				break;

			case SECOND_CUADRANT:

				tromino(size / 2, yOrig, xOrig, yOrig + size / 2, xOrig, FIRST_CUADRANT); // fourth cuad
				printStartingTromino(size, yOrig, xOrig, yOrig, xOrig);
				tromino(size / 2, yOrig, xOrig, yOrig, xOrig + size / 2, SECOND_CUADRANT); // fourth cuad
				tromino(size / 2, yOrig, xOrig, yOrig, xOrig, FOURTH_CUADRANT); // fourth cuad
				tromino(size / 2, yOrig, xOrig, yOrig + size / 2, xOrig + size / 2, THIRD_CUADRANT); // fourth cuad

				break;

			case THIRD_CUADRANT:

				tromino(size / 2, size, 0, yOrig, size / 2, FIRST_CUADRANT); // fourth cuad
				printStartingTromino(size, size / 4 + yOrig, size / 4, yOrig, 0);
				tromino(size / 2, yOrig, size / 4, yOrig, xOrig, FOURTH_CUADRANT);
				tromino(size / 2, yOrig, size / 4, yOrig + size / 2, xOrig, THIRD_CUADRANT); // second cuad
				tromino(size / 2, yOrig, size / 4, yOrig + size / 2, xOrig + size / 2, FOURTH_CUADRANT); // third cuad
				break;

			case FOURTH_CUADRANT:
				tromino(size / 2, yOrig, xOrig, yOrig, xOrig, SECOND_CUADRANT); // first cuad

				printStartingTromino(size, yOrig, xOrig, yOrig, xOrig);
				tromino(size / 2, yOrig, xOrig, yOrig + size / 2, xOrig + size / 2, SECOND_CUADRANT); // second cuad
				tromino(size / 2, yOrig, xOrig, yOrig + size / 2, xOrig, THIRD_CUADRANT); // third cuad
				tromino(size / 2, yOrig, xOrig, yOrig, xOrig + size / 2, FOURTH_CUADRANT); // fourth cuad
				break;

			default:
				break;
			}
		}

	}

	private static void printStartingTromino(int size, int xPos, int yPos, int xOrig, int yOrig) {
		for (int i = size / 2 - 1; i <= size / 2; i++)
			for (int j = size / 2 - 1; j <= size / 2; j++)
				if (array[xOrig + i][yOrig + j] == 0)
					array[xOrig + i][yOrig + j] = counter;
		counter++;
	}

	private static int printStartingTromino(int size, int xPos, int yPos) {
		if (xPos < size / 2 && yPos < size / 2)
			return placeTrominoStartingSecondCuad(size);
		if (xPos >= size / 2 && yPos < size / 2)
			return placeTrominoStartingFirstCuad(size);
		if (xPos < size / 2 && yPos >= size / 2)
			return placeTrominoStartingThirdCuad(size);
		if (xPos >= size / 2 && yPos >= size / 2)
			return placeTrominoStartingForthCuad(size);

		return ERROR;

	}

	private static int placeTrominoStartingForthCuad(int size) {
		array[size / 2 - 1][size / 2 - 1] = STARTING_TROMINO; // placing second quad
		array[size / 2][size / 2 - 1] = STARTING_TROMINO; // placing first quad
		array[size / 2 - 1][size / 2] = STARTING_TROMINO; // placing third quad
		counter++;

		return FOURTH_CUADRANT;

	}

	private static int placeTrominoStartingThirdCuad(int size) {
		array[size / 2 - 1][size / 2 - 1] = STARTING_TROMINO; // placing second quad
		array[size / 2][size / 2 - 1] = STARTING_TROMINO; // placing first quad
		array[size / 2][size / 2] = STARTING_TROMINO; // placing fourth quad
		counter++;

		return THIRD_CUADRANT;
	}

	private static int placeTrominoStartingFirstCuad(int size) {
		array[size / 2 - 1][size / 2 - 1] = STARTING_TROMINO; // placing second quad
		array[size / 2 - 1][size / 2] = STARTING_TROMINO; // placing third quad
		array[size / 2][size / 2] = STARTING_TROMINO; // placing fourth quad

		counter++;

		return FIRST_CUADRANT;
	}

	private static int placeTrominoStartingSecondCuad(int size) {
		array[size / 2][size / 2 - 1] = STARTING_TROMINO; // placing first quad
		array[size / 2 - 1][size / 2] = STARTING_TROMINO; // placing third quad
		array[size / 2][size / 2] = STARTING_TROMINO; // placing fourth quad
		counter++;

		return SECOND_CUADRANT;
	}

	private static void initialize(int size, int x, int y) {
		array = new int[size][size];
		array[x][y] = STARTING_POINT;

	}

	public static void print() {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println();

	}
}
