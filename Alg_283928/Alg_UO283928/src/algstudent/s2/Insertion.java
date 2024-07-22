

package algstudent.s2;


/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the DIRECT INSERTION */
public class Insertion extends Vector {	
	public Insertion(int nElements) {
		super(nElements);
	}

	/**
	 * Iterate from the index1 to the end.
	 * 	Extracting the element in the current pos
	 * 	Comparing it to its closest left element
	 * 	Swapping if necesary or continue iterating back
	 * 	Pick the next
	 */
	@Override
	public void sort(){
		for ( int i = 1; i < super.elements.length; i++ ) { //Iterate from index1 till the end
			if ( ! ( super.elements[ i ] > super.elements[ i - 1 ] ) ) { //Checking if already sorted
				for (int j = i ; j > 0; j-- ) { //Iterate from the current to the first
					if ( super.elements[ j ] < super.elements[ j - 1 ] )
						super.interchange(j-1, j);
				}
				
			}
			
		}
		
	} 
	
	@Override
	public String getName() {
		return "Insertion";
	} 
} 
