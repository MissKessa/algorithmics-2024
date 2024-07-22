package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import algstudent.s4.Graph;
import algstudent.s4.Node;

public abstract class FileUtil {

	private static final int ERROR = -1;
	

	public static int[][] loadFile (String fileName) {
		int [][] array = null;
	    String line;
	    String[] row;
	    
	    try {
	    	   BufferedReader file = new BufferedReader(new FileReader(fileName));
	    	   int size = ERROR;
	    	   if ( file.ready() ) size = Integer.parseInt( file.readLine() );
	    	   array = new int[ size ][ size ];
	    	   int counter = 0;
	    		while ( file.ready() ) {
	    			line = file.readLine();
	    			row = line.split("	");
	    			for ( int i = 0; i < row.length; i++ ) 
	    				array[ counter ][ i ] = Integer.parseInt( row[ i ] );
    				counter++;
	    		}
	    		file.close();
	    }
	    catch (FileNotFoundException fnfe) {
	      System.out.println("File not found.");
	    }
	    catch (IOException ioe) {
	      new RuntimeException("I/O Error.");
	    } 
	    return array;
	}
	
	
	public static HashMap<Node, List<String>> loadCoutries (String fileName) {
		HashMap<Node, List<String>> dictionary= new HashMap<Node, List<String>>();
	    String line;
	    String[] row;
	    
	    List<String> borders = new ArrayList<String>();
	    Node countryN;
	    try {
	    	   BufferedReader file = new BufferedReader(new FileReader(fileName));
	    	   while ( file.ready() ) {
	    			line = file.readLine();
	    			row = line.split(": ");

	    			countryN = new Node( row[ 0 ] ); // current country
	    			for( String str : row[ 1 ].split( "," ) ) {  // its borders
	    				borders.add( str.trim() ); 				 // adding current 
	    													     // country's borders 
	    													     // to a list
	    			
	    				Graph.conversion.put( str, countryN );
	    			}
	    			dictionary.put( countryN, borders );
	    			borders = new ArrayList<String>();
	    	   }
	    		file.close();
	    }
	    catch (FileNotFoundException fnfe) {
	      System.out.println("File not found.");
	    }
	    catch (IOException ioe) {
	      new RuntimeException("I/O Error.");
	    } 
	    return dictionary;
	}
	

	public static List<String> loadColors( String fileName ) {
		    List<String> colors = new ArrayList<String>();
		    try {
		    	   BufferedReader file = new BufferedReader(new FileReader(fileName));
		    	   while ( file.ready() ) {
		    			colors.add(file.readLine());
		    	   }
		    		file.close();
		    }
		    catch (FileNotFoundException fnfe) {
		      System.out.println("File not found.");
		    }
		    catch (IOException ioe) {
		      new RuntimeException("I/O Error.");
		    } 
		    return colors;
	} 
	
}
