package algstudent.s6;

import org.junit.jupiter.api.Test;

class NumericSquareAllTest {

	@Test
	void test00() {
		NumericSquareAll s = new NumericSquareAll("src/algstudent/s6/test00.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		for (long i = 0; i < 1000; i++) {
			s.findSolution();
		}
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
	}

	@Test
	void test08() {
		NumericSquareAll s = new NumericSquareAll("src/algstudent/s6/test08.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		s.findSolution();
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
	}

	@Test
	void test01() {
		NumericSquareAll s = new NumericSquareAll("src/algstudent/s6/test01.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		for (long i = 0; i < 10; i++) {
			s.findSolution();
		}
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
	}

	@Test
	void test02() {
		NumericSquareAll s = new NumericSquareAll("src/algstudent/s6/test02.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		for (long i = 0; i < 150; i++) {
			s.findSolution();
		}
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
	}

	@Test
	void test03() {
		NumericSquareAll s = new NumericSquareAll("src/algstudent/s6/test03.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		for (long i = 0; i < 3; i++) {
			s.findSolution();
		}
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
	}

	@Test
	void test04() {
		NumericSquareAll s = new NumericSquareAll("src/algstudent/s6/test04.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		s.findSolution();
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
	}

	@Test
	void test05() {
		NumericSquareAll s = new NumericSquareAll("src/algstudent/s6/test05.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		for (long i = 0; i < 2; i++) {
			s.findSolution();
		}
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
	}

	@Test
	void test06() {
		NumericSquareAll s = new NumericSquareAll("src/algstudent/s6/test06.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		s.findSolution();
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
	}

	@Test
	void test07() {
		NumericSquareAll s = new NumericSquareAll("src/algstudent/s6/test07.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		s.findSolution();
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
	}

}
