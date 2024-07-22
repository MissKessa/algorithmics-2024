package algstudent.s2;

/** This program is used to order n elements with a QUICKSORT algorithm.
However, as you select as the pivot the first element of the partition, 
the ordered array has a bad behavior (quadratic). */
public class QuicksortFateful extends Vector {
	public static void main (String arg [])	{
		System.out.println("WE ARE GOING TO TEST THAT IT WORKS");
		System.out.println("QUADRATIC TIMES USING A BAD PIVOT");	
		long t1,t2;
		int n;

		for (n=1000;n<10000;n*=2)
		{
			Vector v = new QuicksortFateful(n);

			v.directlySorted();

			t1 = System.currentTimeMillis();

			for (int nTimes=1;nTimes<=1000;nTimes++)
				v.sort(); // microseconds

			t2 = System.currentTimeMillis();

			System.out.println("n="+n+"**TIME="+(t2-t1)+" MICROSECONDS"); 

		} //for

	} //main

	public QuicksortFateful(int nElementos) {
		super(nElementos);
	}
	
	private void quickSort(int left, int right) {
		int i = left;
		int j = right - 1;
		int pivot;
		
		if (left < right){ //if there is one element it is not necessary
			int toParticionate = left; //instead of the median of three, here we are using the first element (the first or the last is usually a bad choice)
			pivot = elements[toParticionate]; //choose the pivot
			interchange(toParticionate, right); //hide the pivot
				
			do {         
		    	while (elements[i] <= pivot && i < right) i++; //first element > pivot
		    	while (elements[j] >= pivot && j > left) j--; //first element < pivot
		        if (i < j) interchange(i, j);
		    } while (i < j);   //end while
				
			//we set the position of the pivot
			interchange(i, right);
			quickSort(left, i-1);
			quickSort(i+1, right);		
		} 
	}

	@Override
	public void sort() {
		quickSort(0,this.elements.length-1);		
	}
	
	@Override
	public String getName() {
		return "Quicksort - Fateful";
	} 
} 

