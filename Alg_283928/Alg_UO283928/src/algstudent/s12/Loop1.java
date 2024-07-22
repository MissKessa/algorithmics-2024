package algstudent.s12;

import java.util.Random;

public class Loop1 {	
	public static long loop1(int n){
		Random rn = new Random();
		long cont = 0;
		for (int i=1; i<=n; i++)
			for (int j=1; j<=n; j*=2)
				cont = rn.nextInt();
		return cont;
	}
	
	public static void main(String arg[]){
		long t1, t2;
		long cont = 0;
		int repetitions = Integer.parseInt(arg[0]);
	
		for (int n=1; n<=Integer.MAX_VALUE; n*=2){
			t1 = System.currentTimeMillis();
	 
			for (int repetition=1; repetition<=repetitions;repetition++) {
				cont = loop1(n);
			} 
	 
			t2 = System.currentTimeMillis();
			System.out.println("n="+n+ "\tTIME="+(t2-t1)+"\tCONT="+cont+"\trepetitions="+repetitions);
		} //for
	
	}//main
} //class