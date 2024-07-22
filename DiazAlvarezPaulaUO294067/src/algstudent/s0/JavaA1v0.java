package algstudent.s0;

public class JavaA1v0 {

	/**
	 * 
	 * @return whether m is prime or not
	 */
	public static boolean primoA1(int m) {
		boolean p = true;
		for (int i = 2; i < m; i++) {
			if (m % i == 0) {
				p = false;
			}
		}
		return p;
	}
}
