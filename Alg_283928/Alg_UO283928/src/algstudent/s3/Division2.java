package algstudent.s3;

public class Division2{
	public static long rec2 (int n)
	{
	 long cont = 0;
	 if (n<=0) cont++;
	 else
	  { for (int i=1;i<n;i++) cont++ ;  //O(n)  
	    rec2(n/2);
	    rec2(n/2);
	  }   
	 return cont;
	}
	
	public static void main (String arg []) 
	{
		 long t1,t2,cont = 0;
		 for (int n=1;n<=10000000;n*=2)
		 {
			  t1 = System.currentTimeMillis ();
			   
			  cont=rec2(n);
			 
			  t2 = System.currentTimeMillis ();
			
			  System.out.println("n="+n+ "**TIME="+(t2-t1)+"**cont="+cont);
		 }  // for
	} // main
} //class