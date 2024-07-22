package algstudent.s4;

import algstudent.s2.Vector;

/* This class measures times for the Bubble method
for the 3 assumptions: (already ordered, reverse ordered and random ordered) */
public class MergesortTimes {
	static int[] v;

	public static void main(String arg[]) {
		long t1, t2;
		String opcion = arg[0];

		for (int n = 250000; n <= 1000000000; n *= 2) {
			v = new int[n];

			if (opcion.compareTo("ordered") == 0)
				Vector.sorted(v);
			else if (opcion.compareTo("reverse") == 0)
				Vector.reverseSorted(v);
			else
				Vector.randomSorted(v);

			t1 = System.currentTimeMillis();

			Mergesort.mergesort(v);

			t2 = System.currentTimeMillis();

			System.out.println(n + "\t" + (t2 - t1));
		}
	}
}
