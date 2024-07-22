package algstudent.s4;

/* Params: a=2;b=2;k=0
 * The time complexity is O(n) 
 * and the waste of stack is O(log n)
 * Regardless the growth of n => the stack does not overflow 
 */
public class Division5 {

	static long cont;

	public static void rec5(int n) // a=4, b=2, k=0 -> O(n^2)
	{
		if (n <= 0)
			cont++;
		else {
			cont++; // O(1)
			rec5(n / 2);
			rec5(n / 2);
			rec5(n / 2);
			rec5(n / 2);
		}
	}

	public static void main(String arg[]) {
		long t1, t2 = 0;
		int nTimes = Integer.parseInt(arg[0]);

		for (int n = 1000; n <= 10000000; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int reps = 1; reps <= nTimes; reps++) {
				cont = 0;
				rec5(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + cont);
		} // for
	} // main
} // class