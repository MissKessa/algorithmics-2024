package algstudent.s1;

public class Vector2 {
	public static void main ( String[] arg ) {
		long t0 = System.currentTimeMillis();	
		int n = Integer.parseInt(arg[0]); //Size of the problem in the first argument
		int[] v = new int[n];
		Vector1.fillIn(v);
		Vector1.sum( v );
		long tf = System.currentTimeMillis();
		tf -= t0;
		System.out.println( "TIME TAKEN: " + tf );
	}
	
	public static long timeMeasuring( int workload ) {
		long t0 = System.currentTimeMillis();	
		int[] v = new int[ workload ];
		Vector1.fillIn(v);
		Vector1.sum( v );
		long tf = System.currentTimeMillis();
		tf -= t0;
		System.out.println( "TIME TAKEN: " + tf );
		return tf;
	}
}

