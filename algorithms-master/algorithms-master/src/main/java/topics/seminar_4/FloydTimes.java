/* Shortest paths in a graph by Floyd. 
 * Measure times incrementing n. Cubic
 * complexity O(n^3) */
package topics.seminar_4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FloydTimes {
	private static Logger log = LoggerFactory.getLogger(FloydTimes.class);

	public static void main(String arg[]) {
		long t1, t2;
		for (int n = 50; n <= 100000; n *= 2) {
			String[] v = new String[n]; // vector of nodes
			for (int i = 0; i < n; i++)
				v[i] = "NODE" + i;

			int[][] w = new int[n][n]; // weights matrix
			int[][] c = new int[n][n]; // costs matrix
			int[][] p = new int[n][n]; // path matrix

			Floyd floyd = new Floyd(v, w, c, p);
			floyd.fillInRandomWeights(w);

			t1 = System.currentTimeMillis();
			floyd.floyd();
			t2 = System.currentTimeMillis();

			floyd.minimumPath(0, n - 1);

			log.debug("n=" + n + "***TIME=" + (t2 - t1));
		}
	}

}