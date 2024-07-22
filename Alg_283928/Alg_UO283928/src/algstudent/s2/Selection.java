package algstudent.s2;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the SELECTION */
public class Selection extends Vector {
	public Selection(int nElements) {
		super(nElements);
	}
	
	@Override
	public void sort() {
		int lowest_index;
        for ( int i = 0; i < elements.length ; i++ ) {
        	lowest_index = i; // Find the minimum element in unsorted array
            for ( int j = i+1; j < elements.length ; j++ )
                if ( elements[ j ] < elements[ lowest_index ] ) lowest_index = j;
  
            super.interchange(lowest_index, i);
        }
	}  
	
	@Override
	public String getName() {
		return "Selection";
	} 
} 
