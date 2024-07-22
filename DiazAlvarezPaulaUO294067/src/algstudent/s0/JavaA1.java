package algstudent.s0;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class JavaA1 {
	public static void main(String[] args) {
		int n = 10000;
		Instant t1;
		Instant t2;
		for (int i = 0; i < 7; i++) {
			t1 = Instant.now();

			ArrayList<Integer> primes = JavaA1v1.listadoPrimos(n);
			t2 = Instant.now();
			System.out.println("n =" + n + "***" + "time =" + (Duration.between(t1, t2).toMillis()) + "milliseconds)");
			// print(primes)
			n = n * 2;
		}
	}
}
