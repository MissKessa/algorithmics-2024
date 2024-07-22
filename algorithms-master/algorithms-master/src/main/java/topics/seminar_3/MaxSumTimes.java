package topics.seminar_3;

import java.util.Random;

public class MaxSumTimes {

	public static void main(String[] args) {
		MaxSum m = new MaxSum();
		Random r = new Random();
		int[] v;
		long t1, t2, t3, t4, t5, t6;

		System.out.printf("%15s %15s %15s\n", "MaxSum1", "MaxSum2", "MaxSum3");
		for (int n = 10; n < Integer.MAX_VALUE; n *= 2) {
			v = new int[n];
			for (int i = 0; i < n; i++) {
				v[i] = r.nextInt(10000);
			}
			t1 = System.currentTimeMillis();
			m.maxSum1(v);
			t2 = System.currentTimeMillis();
			t3 = System.currentTimeMillis();
			m.maxSum2(v);
			t4 = System.currentTimeMillis();
			t5 = System.currentTimeMillis();
			m.maxSum3(v);
			t6 = System.currentTimeMillis();
			System.out.printf("%15d %15d %15d\n", t2 - t1, t4 - t3, t6 - t5);
		}
	}

}
