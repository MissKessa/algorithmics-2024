package algstudent.s6;

public class ImageAveragerBench {

	// Benchmarking settings
	private static String REAL_IMG = "src/algstudent/s6/einstein_1_256.png";
	private static String BAD_IMG = "src/algstudent/s6/einstein_1_256.png";
	private static String OUT_DIR_G = "src/algstudent/s6/out_g/";
	private static String OUT_DIR_B = "src/algstudent/s6/out_bt";
	private static int N_IMGS = 5;
	private static double PERCENTAGE_BAD = 25; // %
	private static double S_NOISE = 5.0; // Noise level - Gaussian sigma

	public static void main(String[] args) {

		int n_real, n_bad;
		ImageAverager img_avger;

		// Generating and testing a single dataset instance
		n_bad = (int) ((PERCENTAGE_BAD / 100.) * N_IMGS);
		n_real = N_IMGS - n_bad;
		img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);

//		System.out.print("TESTING GREEDY:\n");
//		img_avger.splitSubsetsGreedy(N_IMGS);
//		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
//		System.out.printf("  -Counter: %d\n",  img_avger.getCounter());
//		img_avger.saveResults(OUT_DIR_G);
//			
//		System.out.print("TESTING BACKTRACKING UNBALANCING:\n");
//		img_avger.splitSubsetsBacktracking();
//		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
//		System.out.printf("  -Counter: %d\n",  img_avger.getCounter());
//		img_avger.saveResults(OUT_DIR_B);
//		
//		System.out.print("TESTING BACKTRACKING BALANCING:\n");
//		img_avger.splitSubsetsBacktracking(1);
//		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
//		System.out.printf("  -Counter: %d\n",  img_avger.getCounter());
//		img_avger.saveResults(OUT_DIR_B);

		// Measurements
		// TODO

		long t1, t2;
//		for (int n = 2; n <= 13; n++) {
//			n_bad = (int) ((PERCENTAGE_BAD / 100.) * n);
//			n_real = n - n_bad;
//			img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
//
//			t1 = System.currentTimeMillis();
//			img_avger.splitSubsetsGreedy(n);
//			t2 = System.currentTimeMillis();
//			System.out.println("Greedy   n=" + n + "**TIME=" + (t2 - t1));
//		}
//		for (int n = 2; n <= 13; n++) {
//			n_bad = (int) ((PERCENTAGE_BAD / 100.) * n);
//			n_real = n - n_bad;
//			img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
//
//			t1 = System.currentTimeMillis();
//			img_avger.splitSubsetsBacktracking();
//			t2 = System.currentTimeMillis();
//			System.out.println("Backtracking   n=" + n + "**TIME=" + (t2 - t1));
//		}
		for (int n = 2; n <= 13; n++) {
			n_bad = (int) ((PERCENTAGE_BAD / 100.) * n);
			n_real = n - n_bad;
			img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);

			t1 = System.currentTimeMillis();
			img_avger.splitSubsetsBacktracking(1);
			System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
			t2 = System.currentTimeMillis();
			System.out.println("Backtracing with neuristic   n=" + n + "**TIME=" + (t2 - t1));
		}

	}

}