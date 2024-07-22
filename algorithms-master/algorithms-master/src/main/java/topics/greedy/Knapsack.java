package topics.greedy;

/**
 * GREEDY ALGORITHM PROBLEM: THE KNAPSACK PROBLEM (BREAKABLE) it has an optimal
 * solution
 * 
 * @author viceg
 */
public class Knapsack {
	private int[] weights; // Weight of each of the objects
	private float[] relations; // Values/weights
	private float[] solution; // The solution, that is, the amount of each of the objects we take from the
								// backpack

	/**
	 * Constructor for Knapsack objects
	 * 
	 * @param weights  Weights that can be used
	 * @param values   Values of the objects
	 * @param solution Array to save the amount of each object taken as a solution
	 */
	public Knapsack(int[] weights, int[] values, float[] solution) {
		this.weights = weights;
		this.solution = solution;
		this.relations = new float[values.length];

		// Calculate the relation values/weight:
		for (int i = 0; i < relations.length; i++) { // O(n)
			relations[i] = (float) values[i] / weights[i];
		}
	}

	/**
	 * This algorithm can have a complexity from O(n*log n) to O(n^2). It depends on
	 * the heuristic method since the main loop is repeated at most n times
	 * 
	 * @param maxWeight Max weight that we can take from objects
	 */
	public void findObjects(int maxWeight) {
		int i = 0;
		float currentWeight = 0; // Actual weight of the backpack (weight carried)
		// soluyion is the units or fragments of each object taken (the descendant v/w)

		do { // Builds and initializes arrays
			i = bestObject(); // Heuristic for selection O(n) in this case. It could be O(logn). If findsthe
								// position of the best object to pick according to the heuristic
			if (currentWeight + weights[i] <= maxWeight) { // check if the object picked can be carried
				solution[i] = 1; // You take the whole object currentWeight += weights[i];
				currentWeight += weights[i];
			} else {
				solution[i] = (maxWeight - currentWeight) / weights[i]; // You take only a part of the object until we
																		// reach the limit (a fragment)
				currentWeight = maxWeight; // we cannot fit anymore objects
			}
		} while (currentWeight < maxWeight); // O(n) Check if we reach the maximum weight of the backpack
	}

	/**
	 * This method has a complexity O(n) --> We could try a binary search with a
	 * complexity O(logn)
	 * 
	 * @return The position of the best available object
	 */
	private int bestObject() { // O(n)
		float max = Float.MIN_VALUE;
		int position = Integer.MIN_VALUE;
		for (int i = 0; i < relations.length; i++) { // O(n)
			if (relations[i] > max) { // The most valuable object is the one with the best value/weight (maximum
										// value)
				max = relations[i];
				position = i;
			}
		}
		relations[position] = Float.MIN_VALUE; // This element is already used. We cannot used it again as the most
												// valuable object, as we will always search for one with the max value
												// (that is greater than the MIN_VALUE)
		return position;
	}

}
