package algstudent.s3;

/* Class that models T(n)=2 T(n-1)+O(1)
 * Params: a=2;b=1;k=0
 * The time complexity is quadratic O(2^n) 
 * and the waste of stack is O(n)
 * In this case => the stack does not overflow because 
 * long before the execution time is untreatable 
 */
public class Subtraction4 {

	static long cont;

	public static void rec4(int n) { // a=1, b=1, k=2 -> O(n^3)
		if (n <= 0)
			cont++;
		else {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					cont++;
				}
			}
			rec4(n - 1);
		}
	}

	public static void main(String arg[]) {
		long t1, t2 = 0;
		int nTimes = Integer.parseInt(arg[0]);

		for (int n = 100; n <= 100000; n *= 2) {
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