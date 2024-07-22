//BACKTRACKING PROBLEM: THE TRAVELING SALESMAN PROBLEM
package topics.seminar_5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SalesmanBestTimes {
	private static Logger log = LoggerFactory.getLogger(SalesmanBestTimes.class);

	public static void main(String arg[]) {
		for (int n = 3; n < Integer.MAX_VALUE; n += 1) {
			long t1 = System.currentTimeMillis();

			SalesmanBest salesmanBest = new SalesmanBest(n, 0, null);
			salesmanBest.backtracking();

			long t2 = System.currentTimeMillis();
			log.debug(String.format("SalesmanBest - size: %d time: %d ms", n, t2 - t1));

			t1 = System.currentTimeMillis();

			SalesmanBestPruning salesmanBestPruning = new SalesmanBestPruning(n, 0, null);
			salesmanBestPruning.backtracking();

			t2 = System.currentTimeMillis();
			log.debug(String.format("SalesmanBestPruning - size: %d time: %d ms\n", n, t2 - t1));
		}
	}
}
