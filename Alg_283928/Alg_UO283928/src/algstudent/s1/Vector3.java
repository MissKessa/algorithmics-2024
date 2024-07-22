package algstudent.s1;

public class Vector3 {

	public static void main(String[] args) {
		long[] allTimes = new long[ args.length ];
		
		for (int i = 0; i < args.length; i++) {
			allTimes[ i ] = Vector2.timeMeasuring( Integer.parseInt( args[ i ] ) );
			
		}
		
		System.out.println( allTimes.toString() );
	}

}
