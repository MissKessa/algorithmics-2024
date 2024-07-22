package algstudent.s2;

public class SortingMeasurements {
	private static int differentSizes = 8; //how many sizes do you want to use in the measurements 
	private static int repetitions = 1; //number of repetitions of the executions
	private static String option = "inversely sorted"; //working with sorted/inversely sorted/random vectors

	/** This program could be used to test all the sorting algorithms
	 * */
	public static void main(String arg []) {		
		int[] it = new int[differentSizes];
		int i = 0;
		for (int size = 10000; size <= Integer.MAX_VALUE && i < it.length; size*= 2) {
			it[i] = size;
			i++;
		}
		int n = it[0];
		System.out.println("Different sizes = " + i);
		
//		measureTimes(new Insertion(n), it);
//		
		measureTimes(new Selection(n), it);
//		
//		measureTimes(new Bubble(n), it);
		
//		measureTimes(new QuicksortFateful(n), it);
//		
//		measureTimes(new QuicksortCentralElement(n), it);
		
//		measureTimes(new QuicksortMedianOfThree(n), it);
	}
	
	public static void measureTimes(Vector v, int[] iterations) {
		long t,t1,t2;
		
		System.out.println("\n\nTime Measurement: " + v.getName());
		for (int n : iterations) {
			t = 0;
			for (int repetition = 1; repetition <= repetitions; repetition++) {
				v.inicialize(n);
				if (option.compareTo("sorted")==0)
					v.directlySorted();
				else if (option.compareTo("inverse")==0)
					v.inverselySorted();
				t1 = System.currentTimeMillis();
				v.sort();
				t2 = System.currentTimeMillis();
				t += t2-t1;
			}

			System.out.println("n=" + n + "**TIME=" + t);
		}
	}
	
}