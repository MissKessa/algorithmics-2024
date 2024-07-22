package algstudent.s5;

public class LeveshteinDistance {

	private int[][] solution;

	char[] s1;
	char[] s2;

	long t1;
	long t2;
	
	public LeveshteinDistance(String str1, String str2) {
		solution = new int[str1.length()][str2.length()];
		String aux = " " + str1;
		s1 = aux.toCharArray();
		aux = " " + str2;
		s2 = aux.toCharArray();
		
		t1 = System.currentTimeMillis();
		leveshteinDistance();
		t2 = System.currentTimeMillis();
	    
		System.out.println("Size of Strings: " + solution.length + "  Time="+(t2-t1));
//		print();	// Uncomment to see solution
	}

	private void leveshteinDistance() {
		for (int i = 0; i < solution.length; i++)
			solution[i][0] = i;
		for (int j = 0; j < solution[0].length; j++)
			solution[0][j] = j;

		for (int i = 1; i < solution.length; i++)
			for (int j = 1; j < solution[0].length; j++)
				leveshteinDistance(i, j);
	}

	private void leveshteinDistance(int i, int j) {
		if (s1[i] == s2[j])
			solution[i][j] = solution[i - 1][j - 1];
		else
			solution[i][j] = 1 + Math.min(solution[i - 1][j - 1],
					Math.min(solution[i][j - 1], 
							solution[i - 1][j]));
	}

	public void print() {
		System.out.print("   ");
		for (int i = 0; i < solution[0].length; i++)
			System.out.print(s2[i] + "  ");
		System.out.println(); // Printing the second word on the first row

		for (int i = 0; i < solution.length; i++) {
			System.out.print(s1[i] + "  "); // Printing the first word on the
											// first column
			for (int j = 0; j < solution[0].length; j++)
				System.out.print(solution[i][j] + "  ");
			System.out.println();
		}

	}
}
