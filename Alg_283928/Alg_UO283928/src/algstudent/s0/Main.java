package algstudent.s0;

public class Main {

	public static void main(String[] args) {
		MatrixOperations mo = new MatrixOperations();
		mo.matrixOperations( "files/matrix02.txt" );
		mo.write();
		System.out.println( mo.getSize() );
		System.out.println( mo.sumDiagonal1() );
		System.out.println( mo.sumDiagonal2() );
		
		mo.travelPath(5, 3);
		mo.write();
		
		String aux = "";
		int value = 3;
		for ( int i = 1; i < 31; i ++ ) {
			
			value = 3 *value;
			aux += value + " ";
			
		}
		System.out.println(aux);
	}

}
