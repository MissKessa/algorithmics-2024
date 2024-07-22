package algstudent.s2;

public class SortingTests {

	/** This program is used to check that sorting algorithms are working
	 * */
	public static void main(String arg []) {
		int n = Integer.parseInt(arg[0]);  //problem size
		
//		testSortingAlgorithm(new Insertion(n));
		
//		testSortingAlgorithm(new Selection(n));
//		
		testSortingAlgorithm(new Bubble(n)); //TODO
//		
//		testSortingAlgorithm(new QuicksortFateful(n));
//		
//		testSortingAlgorithm(new QuicksortCentralElement(n));
//		
//		testSortingAlgorithm(new QuicksortMedianOfThree(n));
	}
	
	public static void testSortingAlgorithm(Vector v) {
		System.out.println("\n\nSorting test: "+ v.getName());
		
		System.out.println("\nSorting an already-sorted vector");
		v.directlySorted();
		System.out.println("Vector to be sorted:");
		v.write(System.out);	
		v.sort();
		System.out.println("Sorted vector");
		v.write(System.out);

		System.out.println("\nSorting an inverse vector");
		v.inverselySorted();
		System.out.println("Vector to be sorted:");
		v.write(System.out);	
		v.sort();
		System.out.println("Sorted vector");
		v.write(System.out);

		System.out.println("\nSorting a random vector");
		v.randomlySorted();
		System.out.println("Vector to be sorted:");
		v.write(System.out);	
		v.sort();
		System.out.println("Sorted vector");
		v.write(System.out);
	}
	
}
