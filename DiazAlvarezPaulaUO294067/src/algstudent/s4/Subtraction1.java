package algstudent.s4;

/* Class that models T(n)=T(n-1)+O(1)
 * The time complexity is O(n) 
 * and the waste of stack is O(n)
 * In this case => the stack overflows 
 */
public class Subtraction1 {

	static long cont;

	public static void rec1(int n) { // a=1, b=1, k=0 -> O(n^1)=O(n)
		if (n <= 0)
			cont++;
		else {
			cont++; // O(1)=O(n^0)
			rec1(n - 1);
		}
	}

	public static void main(String arg[]) {
		long t1, t2 = 0;
		int nTimes = Integer.parseInt(arg[0]);

		for (int n = 1; n <= 100000; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int reps = 1; reps <= nTimes; reps++) {
				cont = 0;
				rec1(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + cont);
		} // for
	} // main
} // class