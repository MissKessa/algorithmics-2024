package algstudent.s0;

import java.util.Random;

import util.FileUtil;

public class MatrixOperations {
	private static final int NULL_MATRIX = -1;
	private static final int INITIAL_VALUE = 0;
	private static final int ALREADY_VISITED = -1;
	private int[][] matrix;
	private Random random = new Random();

	public void matrixOperations( int size, int minValue, int maxValue ) {
		matrix = new int[ size ][ size ];
		for (int i = 0; i < matrix.length; i++) 
			for (int j = 0; j < matrix.length; j++) 
				matrix[ i ][ j ] = random.nextInt( maxValue - minValue + 1 ) + minValue;
			
	}

	public void matrixOperations( String filename ) {
		matrix = FileUtil.loadFile(filename);
	
	}
	
	public int getSize() {
		return matrix == null ? NULL_MATRIX : matrix.length;
		
	}
	
	public void write() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) 
				System.out.print( matrix[ i ][ j ] + "  " );
			System.out.println();
		}
		
	}
	
	public int sumDiagonal1() {
		int ret = 0;
		for (int i = 0; i < matrix.length; i++) 
			for (int j = 0; j < matrix.length; j++) 
				if ( i == j ) ret += matrix[ i ][ j ];
		
		return ret;
	}
	
	public int sumDiagonal2() {
		int ret = 0;
		for (int i = 0; i < matrix.length; i++) 
			ret += matrix[ i ][ i ];
		
		return ret;
	}
	
	public void travelPath( int i, int j ) {
		int amountOfMovements = travelPath( i, j, INITIAL_VALUE );
		
		System.out.println( "Amount of movements: " + amountOfMovements );
	}

	private int travelPath(int i, int j, int amountOfMovements) {
		if( matrix[ i ][ j ] == ALREADY_VISITED) return amountOfMovements;
		
		matrix[ i ][ j ] = ALREADY_VISITED;
		
		try {
			if ( matrix[ i ][ j ] == 1 )  travelPath( i - 1 , j, ++ amountOfMovements );
			if ( matrix[ i ][ j ] == 2 ) travelPath( i, j + 1 , ++ amountOfMovements );
			if ( matrix[ i ][ j ] == 3 ) travelPath( i + 1, j, ++ amountOfMovements );
			if ( matrix[ i ][ j ] == 4 ) travelPath( i, j - 1, ++ amountOfMovements );
		
		} catch ( IndexOutOfBoundsException e ) {
			return amountOfMovements;
		
		}
		
		return amountOfMovements;
	}
	
	
}
