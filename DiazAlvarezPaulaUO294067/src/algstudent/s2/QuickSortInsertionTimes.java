package algstudent.s2;

/* This class measures times for the Bubble method
for the 3 assumptions: (already ordered, reverse ordered and random ordered) */
public class QuickSortInsertionTimes {
	static int[] v;
		static int[] k= {0,5,10,20,30,50,100,200,500,1000};

	public static void main(String arg[]) {
		long t1, t2;
		int n=16000000;
		for (int l: k) {
			v = new int[n];

			Vector.randomSorted(v);

			t1 = System.currentTimeMillis();

			QuickSortInsertion.quicksort(v,l);

			t2 = System.currentTimeMillis();

			System.out.println(l + "\t" + (t2 - t1));
		}
	}
}  
