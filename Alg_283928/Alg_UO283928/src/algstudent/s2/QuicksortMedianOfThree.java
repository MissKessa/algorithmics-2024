package algstudent.s2;

/* This program can be used to sort n elements with 
 * the best algorithm. It is the QUICKSORT */
public class QuicksortMedianOfThree extends Vector {
	public QuicksortMedianOfThree(int nElements) {
		super(nElements);
	}
	
	/*get the position of the median of the three (left, right and 
	 the element which position is in the center between them, and
	 move the elements to order them */
	private int median_of_three(int left, int right) { 
		int center = (left + right) / 2;
		if (elements[left] > elements[center])
			interchange(left, center);
		if (elements[left] > elements[right])
			interchange(left, right);
		if (elements[center] > elements[right])
			interchange(center, right);
		return center;
	}
	
	private void quickSort(int left, int right) {
		int i = left;
		int j = right - 1;
		int pivot;
		
		if (left < right){ //if there is one element it is not necessary
			int center = median_of_three(left, right);
			//if there are less than or equal to 3 elements, there are just ordered
			if ((right - left) >= 3){ 
				pivot = elements[center]; //choose the pivot
				interchange(center, right); //hide the pivot

				do {         
			    	while (elements[i] <= pivot && i < right) i++; //first element > pivot
			    	while (elements[j] >= pivot && j > left) j--; //first element < pivot
			        if (i < j) interchange(i, j);
			    } while (i < j);   //end while
				
				//we set the position of the pivot
				interchange(i, right);
				quickSort(left, i-1);
				quickSort(i+1, right);		
			} //if
		} //if
	}


	@Override
	public void sort() {
		quickSort(0, elements.length-1);
	}
	
	@Override
	public String getName() {
		return "Quicksort - Median of three";
	} 
} 
