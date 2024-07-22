package topics.seminar_4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FloydTest {
	private static Logger log = LoggerFactory.getLogger(FloydTest.class);
	private Floyd floyd;

	@Test
	public void testFloyd() {
		int n = 5;
		int source = 2;
		int target = 4;

		String[] v = new String[n];
		for (int i = 0; i < n; i++)
			v[i] = "NODE" + i; // cities or whatever

		int[][] w = new int[n][n];
		w[0][1] = 3;
		w[0][2] = 8;
		w[0][4] = -4;
		w[1][3] = 1;
		w[1][4] = 7;
		w[2][1] = 4;
		w[3][0] = 2;
		w[3][2] = -5;
		w[4][3] = 6;

		int[][] c = new int[n][n];
		int[][] p = new int[n][n];

		floyd = new Floyd(v, w, c, p);
		floyd.fillInWeights(w);
		log.debug("WEIGHT MATRIX:");
		log.debug(floyd.writeMatrix(w));
		floyd.floyd();

		log.debug("MINIMUM COST MATRIX:");
		log.debug(floyd.writeMatrix(c));

		log.debug("P MATRIX:");
		log.debug(floyd.writeMatrix(p));

		log.debug("Cost from V2 to V4: " + c[source][target]);
		log.debug("Path from V2 to V4: " + floyd.minimumPath(source, target));

		assertEquals(3, c[source][target]);
		assertEquals("NODE2---NODE1---NODE3---NODE0---NODE4", floyd.minimumPath(source, target));
	}
}
