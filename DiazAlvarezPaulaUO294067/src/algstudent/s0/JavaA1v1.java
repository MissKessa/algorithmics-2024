package algstudent.s0;

import java.util.ArrayList;

public class JavaA1v1 {
	/**
	 * Calculates and returns all primes up to n
	 */
	public static ArrayList<Integer> listadoPrimos(int n) {
		ArrayList<Integer> primes = new ArrayList<>(n);

		for (int i = 2; i < n + 1; i++) {
			if (JavaA1v0.primoA1(i)) {
				primes.add(i);
			}
		}
		return primes;
	}

}