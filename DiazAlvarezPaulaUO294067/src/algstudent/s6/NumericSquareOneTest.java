package algstudent.s6;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class NumericSquareOneTest {

	@Test
	void test00() {
		NumericSquareOne s = new NumericSquareOne("src/algstudent/s6/test00.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		for (long i = 0; i < 1000; i++) {
			s.findSolution();
		}
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
		Assert.assertEquals(true, s.checkSolution());
	}

	@Test
	void test08() {
		NumericSquareOne s = new NumericSquareOne("src/algstudent/s6/test08.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		s.findSolution();
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
		Assert.assertEquals(true, s.checkSolution());
	}

	@Test
	void test01() {
		NumericSquareOne s = new NumericSquareOne("src/algstudent/s6/test01.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		for (long i = 0; i < 500; i++) {
			s.findSolution();
		}
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
		Assert.assertEquals(true, s.checkSolution());
	}

	@Test
	void test02() {
		NumericSquareOne s = new NumericSquareOne("src/algstudent/s6/test02.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		for (long i = 0; i < 500; i++) {
			s.findSolution();
		}
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
		Assert.assertEquals(true, s.checkSolution());
	}

	@Test
	void test03() {
		NumericSquareOne s = new NumericSquareOne("src/algstudent/s6/test03.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		for (long i = 0; i < 150; i++) {
			s.findSolution();
		}
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
		Assert.assertEquals(true, s.checkSolution());
	}

	@Test
	void test04() {
		NumericSquareOne s = new NumericSquareOne("src/algstudent/s6/test04.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		s.findSolution();
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
		Assert.assertEquals(true, s.checkSolution());
	}

	@Test
	void test05() {
		NumericSquareOne s = new NumericSquareOne("src/algstudent/s6/test05.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		for (long i = 0; i < 150; i++) {
			s.findSolution();
		}
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
		Assert.assertEquals(true, s.checkSolution());
	}

	@Test
	void test06() {
		NumericSquareOne s = new NumericSquareOne("src/algstudent/s6/test06.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		for (long i = 0; i < 200; i++) {
			s.findSolution();
		}
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
		Assert.assertEquals(true, s.checkSolution());
	}

	@Test
	void test07() {
		NumericSquareOne s = new NumericSquareOne("src/algstudent/s6/test07.txt");
		s.printBoard();
		long t3 = System.currentTimeMillis();
		s.findSolution();
		long t4 = System.currentTimeMillis();
		System.out.printf("Time solution:" + (t4 - t3) + "ms");
		Assert.assertEquals(true, s.checkSolution());
	}

}
