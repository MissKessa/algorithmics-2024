package algstudent.s1;
public class JavaTimes {	
	public static void linear(long n) { //* O(n) 
		long cont = 0;
		for (long i=1; i<=n; i++)
			cont++;
		System.out.print("counter="+cont);
	}
	
	public static void quadratic(long n) { //* O(n**2) 
		long cont = 0;
		for (long i=1; i<=n; i++)
			for (long j=1; j<=n; j++)
				cont++;
		System.out.print("counter="+cont);
	}
		
	public static void main(String arg[]) {
		System.out.println("Linear times in Java (milliseconds)");
		long t1 = 0;
		long t2 = 0;
		long n = 1000000;
		while (t2-t1 < 5000) { //5 sec.
		   t1 = System.currentTimeMillis();
		   linear(n);
		   t2 = System.currentTimeMillis();
		   System.out.println(" n="+n+ "  Time="+(t2-t1));
		   n=n*2;
		}
		
		System.out.println("Quadratic times in Java (milliseconds)");
		t1 = 0;
		t2 = 0;
		n = 100;
		while (t2-t1 < 5000) { //5 sec.
		   t1 = System.currentTimeMillis();
		   quadratic(n);
		   t2 = System.currentTimeMillis();
		   System.out.println(" n="+n+ "  Time="+(t2-t1));
		   n=n*2;
		}
	}//main
} //class