package algstudent.test;

public class PatternMatching {
	private String text; // The text to check the patterns with
	private boolean isEmptyText;
	private boolean[][] table;
	private String pattern;

	public PatternMatching(String text) {
		this.text = text;
		if (text.isEmpty()) {
			isEmptyText = true;
		}
	}

	public boolean checkPattern(String pattern) {
		this.pattern=pattern;
		if (pattern.isEmpty()) {
			return emptyPattern();
		}
		if (isEmptyText) {
			table = new boolean[1][pattern.length()];
		} else {
			table = new boolean[text.length()][pattern.length()];
		}

		char letterText = '\0';
		for (int i = 0; i < table.length && table != null; i++) {
			if (!isEmptyText) {
				letterText = text.charAt(i);
			}

			for (int j = 0; j < table[i].length; j++) {

				char letterPattern = pattern.charAt(j);
				switch (letterPattern) {
				case '?':
					if (i == 0 || j == 0)
						table[i][j] = true;
					else if (table[i - 1][j - 1] || table[i][j - 1])
						table[i][j] = true;
					break;

				case '*':
					if (i == 0 || j == 0)
						table[i][j] = true;
					else if (table[i - 1][j - 1] || table[i][j - 1] || table[i - 1][j])
						table[i][j] = true;
					break;
				case '&':
					if(j>i && pattern.charAt(j-1)!='?'&& pattern.charAt(j-1)!='*') {
						break;
					}
					if (i == 0 || j == 0) {
						table[i][j] = true;
						if(i+1< table.length) {
							table[i+1][j] = true;
							if(i-1>=0 && table[i-1][j]) {
								table[i+1][j] = false;
							}
						}
						if(j==0 && i>=2) {
							table[i][j] = false;
							if(i+1< table.length) {
								table[i+1][j] = false;
							}
						}
					}
					else if(table[i - 1][j - 1]) {
						table[i][j] = true;
						if(i+1< table.length) {
//							if(i-1>=0 && table[i-1][j]) {
//								table[i+1][j] = false;
//							} else {
//								table[i+1][j] = true;
//							}
							table[i+1][j] = true;
						}
						
					}
					break;

				default: // a normal letter
					if (i == 0 || j == 0) 
						table[i][j] = letterPattern == letterText;
						
					
					else if (table[i - 1][j - 1])
						table[i][j] = letterPattern == letterText;
					break;
				}
			}
		}
		return table[table.length - 1][table[0].length - 1] && table[0][0];
	}

	private boolean emptyPattern() {
		table = new boolean[1][1];
		if (text.isEmpty()) {
			table[0][0] = true;
			return true;
		}
		table[0][0] = false;
		return false;
	}
	
	public void printsTable() {
		System.out.print(" ");
		for (int i=0; i<pattern.length();i++) {
			System.out.print(" "+pattern.charAt(i));
		}
		System.out.println();
		for (int i = 0; i < table.length && table != null; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (j==0) {
					System.out.print(text.charAt(i)+" ");
				}
				if(table[i][j]) {
					System.out.print("T ");
				} else {
					System.out.print("F ");
				}
			}
			System.out.println();
		}
		System.out.println("----------");

	}

//	public void printsTable() {
//		for (int i = 0; i < table.length && table != null; i++) {
//			for (int j = 0; j < table[i].length; j++) {
//				System.out.print(table[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("----------");
//
//	}

}
