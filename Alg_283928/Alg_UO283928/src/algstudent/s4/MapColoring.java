package algstudent.s4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import util.FileUtil;

public class MapColoring {
	
	private static List<String> allColors = FileUtil.loadColors( "files/colors.txt" );
	private static HashMap<Node, List<String>> allCountries = FileUtil.loadCoutries("files/borders.txt");
	static List<String> colorsUsed = new ArrayList<String>();
	
	public static void main( String[] args ) {
		assignColor();
		print();
	}
	
	private static void print() {
		int counter = 1;
		for( Node country: allCountries.keySet() ) {
			System.out.println( counter + "  " + country.toString() );
			for( String border : allCountries.get(country)  ) 
				if ( Graph.conversion.get(border) != null )
					System.out.println("\t" + Graph.conversion.get(border).toString() );
			System.out.println();
			counter++;
		}
	}


	/**
	 * Algorithm for assigning colors to different countries.
	 */
	private static void assignColor() {
		
		boolean used; // for knowing if a certain color has been used for any border of a certain country.
					  // It is first assumed to be true and then, it is proven false.
		for ( Node country : allCountries.keySet() )
			for ( String color : allColors ) {
				used = false;
				for ( String border : allCountries.get(country) ) {
					if( Graph.conversion.containsKey(border) && // NullPointerException check
							color.equals( Graph.conversion.get(border).getColor() ) ) {	// If color equals, used is true.
						used = true;
						break;	//Exit for
					}
				}
				if ( !used ) {
					country.setColor( color );  // The first not used color will be used
					used = true;
					if ( !(colorsUsed.contains(color)) ) colorsUsed.add(color);
					break;
				}
			}
		
	}
}
