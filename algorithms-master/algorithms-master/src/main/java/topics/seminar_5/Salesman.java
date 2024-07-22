//GREEDY ALGORITHM PROBLEM: THE TRAVELING SALESMAN PROBLEM
package topics.seminar_5;

/* This program is used to solve the traveling salesman 
 * problem by using a greedy algorithm */
public class Salesman {
	protected String[] nodes; // vector of nodes
	protected int[][] w; // edge weight matrix
	protected int[] sol; // solution

	public Salesman(int n, int source) {
		nodes = new String[n];

		for (int i = 0; i < n; i++)
			nodes[i] = "NODE" + i; // not real names for cities: called NODE0, NODE1, ...

		w = new int[n][n]; // weight matrix
		sol = new int[n + 1]; // vector with the solution (+1 because in the end you have to return home)

		sol[0] = source;
	}

	public int salesmanGreedy() {
		int n = w.length;
		boolean[] mark = new boolean[n]; // to mark the nodes through which we have already passed (and thus not to use
											// them again)

		mark[sol[0]] = true; // we mark the initial node (the 0) as visited
		int current = sol[0]; // at this time we are still in the first node
		int cost = 0; // at this time we have not moved yet => current cost is 0

		for (int i = 1; i < n; i++) { // it is looking for nodes (cities) from 1 to n-1
			int min = Integer.MAX_VALUE;
			int pos = -1;
			for (int j = 0; j < n; j++) // finds the shortest path from the current node to another UNVISITED one
				if (!mark[j] && w[current][j] < min) {
					min = w[current][j]; // temporarily stores the minimum cost
					pos = j; // temporarily stores the position of the node which would cost less to get
				}
			cost = cost + min; // value is increased since we add a new edge
			sol[i] = pos; // a new node is assigned as part of the solution
			mark[pos] = true; // the node is marked, so it is not revisit
			current = pos; // current is updated
		}

		sol[n] = sol[0]; // cycle is closed (goes to the first city of all)
		return cost + w[sol[n - 1]][sol[n]]; // returns the cycle cost (the calculated + the cost of closing the cycle
												// from the last node to the first one)
	}

	/* Loads the weight matrix (from the practice guide example) */
	public void fillInWeights(int[][] w) {
		this.w = w;
		for (int i = 0; i < w.length; i++)
			this.w[i][i] = Integer.MAX_VALUE; // you cannot go from a place to itself
	}

	/* Writes the weight matrix */
	public String writeWeights() {
		StringBuilder sb = new StringBuilder();
		int n = w.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				if (w[i][j] == Integer.MAX_VALUE)
					sb.append(String.format("%12s", "INF"));
				else
					sb.append(String.format("%12d", w[i][j]));
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	/* Writes the cycle to be followed */
	public String getSolution() {
		StringBuilder sb = new StringBuilder();
		int m = sol.length;
		for (int i = 0; i < m; i++)
			sb.append(nodes[sol[i]] + "--");
		return sb.toString();
	}
}