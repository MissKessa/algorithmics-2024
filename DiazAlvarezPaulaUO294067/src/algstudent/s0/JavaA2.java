package algstudent.s0;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class JavaA2 {
	public static void main(String[] args) {
		int n = 10000;
		Instant t1;
		Instant t2;
		for (int i = 0; i < 7; i++) {
			t1 = Instant.now();

			ArrayList<Integer> primes = listadoPrimos(n);
			t2 = Instant.now();
			System.out.println("n =" + n + "***" + "time =" + (Duration.between(t1, t2).toMillis()) + " milliseconds)");
			// print(primes)
			n = n * 2;
		}
	}

	/**
	 * Calculates and returns all primes up to n
	 * 
	 * @param n
	 * @return
	 */
	public static ArrayList<Integer> listadoPrimos(int n) {
		ArrayList<Integer> primes = new ArrayList<>(n);

		for (int i = 2; i < n + 1; i++) {
			if (primoA2(i)) {
				primes.add(i);
			}
		}
		return primes;
	}

	/**
	 * 
	 * @return whether m is prime or not
	 */
	public static boolean primoA2(int m) {
		for (int i = 2; i < m; i++) {
			if (m % i == 0) {
				return false;
			}
		}
		return true;
	}

}