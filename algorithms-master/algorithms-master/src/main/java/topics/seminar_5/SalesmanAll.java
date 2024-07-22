//BACKTRACKING PROBLEM: THE TRAVELING SALESMAN PROBLEM
package topics.seminar_5;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* The traveling salesman problem is a NP problem, 
 * i.e., it has not a polynomial solution. 
 * 
 * This class calculates, using backtracking, all 
 * the cycles that the salesman can follow. They
 * are cycles of length n. That is, they run 
 * through all nodes (cities).
 * 
 * The total number of cycles is (n-1)! 
 * 
 * The time complexity is factorial (untreatable)
 * so, it is not a polynomial complexity */
public class SalesmanAll {
	protected static Logger log = LoggerFactory.getLogger(SalesmanAll.class);
	protected int n; // number of nodes (cities)
	protected String[] nodes; // nodes (cities)
	protected int[][] weights; // weights (distances between cities)
	protected int source; // the initial node (city of departure)
	protected boolean[] mark; // to mark the already visited cities and not repeat them
	protected int[] path; // one of the possible paths (either the best or not) - in progress
	protected int cost; // cost the one of the possible paths - in progress
	protected int length; // size (level or length) of each of the paths
	protected int nsol; // the total number of cycles

	public SalesmanAll(int n, int source, int[][] weights) {
		this.n = n;

		nodes = new String[n];
		for (int i = 0; i < n; i++)
			nodes[i] = "NODE" + i;

		if (weights != null)
			this.weights = weights;
		else { // if weights is null, we assign random values
			this.weights = new int[n][n];
			giveValuesEdgeWeights();
		}

		mark = new boolean[n]; // nodes (cities) are not visited yet

		path = new int[n + 1]; // all the nodes +1 to return to the starting node
		path[0] = source; // we start in the "source city"
		length = 0;
		cost = 0; // we have not moved yet

		nsol = 0; // number of solutions
	}

	public int getNumberSolutions() {
		return nsol;
	}

	public void backtracking() {
		backtracking(source);
	}

	public String writeWeights() {
		StringBuilder sb = new StringBuilder();
		int n = weights.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				sb.append(String.format("%12d", weights[i][j]));
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	// random weight matrix
	protected void giveValuesEdgeWeights() {
		Random r = new Random();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < i; j++) {
				weights[i][j] = r.nextInt(99) + 1;
				weights[j][i] = weights[i][j];
			}

		for (int i = 0; i < n; i++)
			weights[i][i] = -1; // you cannot go from a city to itself
	}

	protected void backtracking(int current) {
		if (current == source && length == n) { // it is a solution state
			nsol++;
			StringBuilder result = new StringBuilder();
			for (int l = 0; l <= length; l++)
				result.append(nodes[path[l]] + "*");
			log.debug(result.toString());
			log.debug("ITS COST IS = " + cost + "\n");
		} else
			for (int j = 0; j < n; j++)
				if (!mark[j] && weights[current][j] != -1) { // child j of the current node
					length++;
					cost = cost + weights[current][j];
					mark[j] = true;
					path[length] = j; // the city j is visited at the position "length"

					backtracking(j); // call on the child node j of the node i

					// we leave it as it was (available to be visited)
					length--;
					cost = cost - weights[current][j];
					mark[j] = false;
				}
	} // backtracking

}