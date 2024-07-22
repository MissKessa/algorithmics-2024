package algstudent.s12;

public class Loop2 {

	public static long loop2(long n) {
		long cont = 0;
		long n1 = n;
		do {
			for (long i = 1; i <= n; i++) // n
				for (long j = n; j >= 0; j -= 2) // n
					cont++;
			n1 = n1 / 3;
		} while (n1 >= 1); // log n

		return cont;
	}

	public static void main(String arg[]) {
		long t1, t2;
		int nTimes = Integer.parseInt(arg[0]);

		System.out.println("n\ttime\trepetions\tcounter");

		for (long n = 100; n <= 819200; n *= 2) {
			long c = 0;
			t1 = System.currentTimeMillis();

			for (int repetitions = 1; repetitions <= nTimes; repetitions++)
				c = loop2(n);

			t2 = System.currentTimeMillis();

			System.out.println(n + "\t" + (t2 - t1) + "\t" + nTimes + "\t\t" + c);

		} // for
	} // main

}