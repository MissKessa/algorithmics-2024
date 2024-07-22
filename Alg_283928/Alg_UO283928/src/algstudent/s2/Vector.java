package algstudent.s2;

import java.io.PrintStream;
import java.util.Random;

/*
It is the class that generates a vector for its three 
possible orderings (sorted, inversely sorted, random). 
It also writes the contents of the vector
*/
public abstract class Vector implements ISortingProblem {
	protected int[] elements;
	
	public Vector(int nElements) {
		inicialize(nElements);
	}
	
	/**	This method initialize the vector attribute with random values
	 *  Could be used to change vector size
	 */
	public void inicialize(int n) {
		this.elements = new int[n];
		this.randomlySorted();
	}

	/* 	This method fills with values sorted in ascending order
	*/
	public void directlySorted() {
		int n = this.elements.length;
		for(int i=0; i<n; i++)
			this.elements[i] = i;
	}

	/* 	This method fills with values sorted in descending order
	*/
	public void inverselySorted() {
		int n = this.elements.length;
		for(int i=0; i<n; i++)
			this.elements[i] = n-i-1;
	}     

	/* 	This method gives random values to a vector of integers.
	 * It uses the Random class from the java.util package  */
	public void randomlySorted() {
		randomlySorted(1000000);
	}
	
	public void randomlySorted(int maxRandomValue) {
		Random r  = new Random ();
		int n = this.elements.length;
		for(int i=0; i<n; i++)
			this.elements[i] = r.nextInt(maxRandomValue);//values in range 0 and maxRandomValue
	}     

	/* 	This method writes the vector elements
	*/
	public void write(PrintStream outStream) {
		int n = this.elements.length;
		outStream.print("(");
		for (int i=0; i<n; i++)
			outStream.print(String.format("%s, ", this.elements[i]));
		outStream.println(")");
	}
	
	/**
	 * Interchange element i and element j
	 * @param i
	 * @param j
	 */
	protected void interchange(int i, int j) {
		int t;
		t = this.elements[i];
		this.elements[i] = this.elements[j];
		this.elements[j] = t;
	}
}
