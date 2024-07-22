package mines;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class LandminesTests {

	@Test
	void test1() throws IOException {
		Landmines l= new Landmines("test/mines/case1.txt");
		float t1=System.currentTimeMillis();
		l.backtracking();
		float t2=System.currentTimeMillis();
		System.out.println("Time in ms: "+(t2-t1));
		l.printSolution();
		
	}
	
	@Test
	void test2() throws IOException {
		Landmines l= new Landmines("test/mines/case2.txt");
		float t1=System.currentTimeMillis();
		l.backtracking();
		float t2=System.currentTimeMillis();
		System.out.println("Time in ms: "+(t2-t1));
		l.printSolution();
		
	}
	
	@Test
	void test3() throws IOException {
		Landmines l= new Landmines("test/mines/case3.txt");
		float t1=System.currentTimeMillis();
		l.backtracking();
		float t2=System.currentTimeMillis();
		System.out.println("Time in ms: "+(t2-t1));
		l.printSolution();
		
	}

}
