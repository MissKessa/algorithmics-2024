package algstudent.s3;

/* Params: a=1;b=3;k=1
 * The time complexity is O(n) 
 * and the waste of stack is O(log n)
 * Regardless the growth of n => the stack does not overflow 
 */
public class Division4 {

	static long cont;

	public static void rec4(int n) // a=1, b=2, k=2 -> O(n^2)
	{
		if (n <= 0)
			cont++;
		else {
			for (int i = 1; i < n; i++)
				for (int j = 1; j < n; j++)
					cont++;
			rec4(n / 2);
		}
	}

	public static void main(String arg[]) {
		long t1, t2 = 0;
		int nTimes = Integer.parseInt(arg[0]);

		for (int n = 1000; n <= 10000000; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int reps = 1; reps <= nTimes; reps++) {
				cont = 0;
				rec4(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + cont);
		} // for
	} // main
} // class