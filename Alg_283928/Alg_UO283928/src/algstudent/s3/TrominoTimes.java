package algstudent.s3;

import java.util.Random;

public class TrominoTimes {

	public static void main(String[] args) {
		long t1,t2;
		Random r = new Random();
		 for (int n=16;n<=32768;n*=2)
		 {
		  t1 = System.currentTimeMillis();
		   
		  Tromino.tromino(n, r.nextInt(n), r.nextInt(n) );
		 
		  t2 = System.currentTimeMillis();
		
		  System.out.println("n="+n+ "**TIME="+(t2-t1));
		 }  // for
	}
}
