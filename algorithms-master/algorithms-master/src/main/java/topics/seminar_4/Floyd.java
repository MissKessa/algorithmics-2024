//Shortest paths in a graph by Floyd

package topics.seminar_4;

import java.util.Random;

public class Floyd {
	String[] v; // vector of nodes
	int[][] w; // weights matrix
	int[][] c; // costs matrix
	int[][] p; // path matrix

	public Floyd(String[] v, int[][] w, int[][] c, int[][] p) {
		this.v = v;
		this.w = w;
		this.c = c;
		this.p = p;
	}

	/* Iterative with cubic complexity O(n^3) */
	public void floyd() {
		int n = w.length;

		// initializes c
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				c[i][j] = w[i][j];

		// initializes p
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				p[i][j] = -1;

		// applying Floyd
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if ((c[i][k] != Integer.MAX_VALUE) && (c[k][j] != Integer.MAX_VALUE)) { // if so, you will not
																							// improve the previous
																							// solution because for us
																							// Integer.MAX_VALUE ==
																							// INFINITE
						if (c[i][k] + c[k][j] < c[i][j]) { // if we improve the previous solution
							c[i][j] = c[i][k] + c[k][j]; // ...we change the cost
							p[i][j] = k; // ...and we indicate the new predecessor
						}
					}
	}

	public String minimumPath(int source, int target) {
		StringBuilder sb = new StringBuilder();
		if (c[source][target] == Integer.MAX_VALUE)
			sb.append("THERE IS NO PATH");
		else {
			sb.append(v[source] + "---");
			path(sb, source, target);
			sb.append(v[target]);
		}
		return sb.toString();
	}

	/*
	 * It is recursive and the worst case is O(n) It is O(n) if it writes all the
	 * nodes
	 */
	private void path(StringBuilder sb, int i, int j) {
		int c = p[i][j];
		if (c >= 0) {
			path(sb, i, c);
			sb.append(v[c] + "---");
			path(sb, c, j);
		}
	}

	/* Load the example cost matrix */
	public void fillInWeights(int[][] w) {
		for (int i = 0; i < w.length; i++)
			for (int j = 0; j < w.length; j++)
				if (w[i][j] == 0)
					w[i][j] = Integer.MAX_VALUE; // a very high value
	}

	public void fillInRandomWeights(int[][] w) {
		Random r = new Random();
		for (int i = 0; i < w.length; i++)
			for (int j = 0; j < w.length; j++)
				w[i][j] = r.nextInt(999) + 1;
	}

	public String writeMatrix(int[][] a) {
		StringBuilder sb = new StringBuilder();
		int n = a.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				if (a[i][j] == Integer.MAX_VALUE)
					sb.append(String.format("%10s", "INF"));
				else
					sb.append(String.format("%10d", a[i][j]));
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}

}