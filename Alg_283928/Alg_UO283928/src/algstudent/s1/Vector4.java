package algstudent.s1;

public class Vector4 {

	public static void main(String[] args) {
		long[] allTimes = new long[ args.length - 1 ];
		int repetitions = Integer.parseInt( args[ 0 ] );
		long[] repetitionTimes = new long[ repetitions ];
		long aux = 0;
		
		for (int i = 1; i < args.length; i++) {
			for ( int j = 0; j < repetitions; j++ ) {
				repetitionTimes[ j ]= Vector2.timeMeasuring( Integer.parseInt( args[ i ] ) );
			}
			for ( int j = 0; j < repetitions; j++ ) {
				aux += repetitionTimes[ j ];
			}
			allTimes[ i - 1 ] = aux / repetitions;
			
		}
		for ( int i = 0; i < allTimes.length ; i ++)
			System.out.println( "TIME TAKEN AT ITERATION " + i + ": " + allTimes[ i ]);
		 
	}

}
