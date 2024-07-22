package algstudent.s5;

import util.StringGenerator;

public class LevestheinDistanceTimes {

	public static void main(String[] args) {
		int workload = 200;
		while( true ) {
			test( workload );
			workload *= 2;
		}
		
	}
	
	private static void test( int size ) {
		String str1 = StringGenerator.generate(size);
		String str2 = StringGenerator.generate(size);

		new LeveshteinDistance(str1, str2);
	}

	
}
