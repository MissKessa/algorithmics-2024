package algstudent.s7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

public class NumericSquarePuzzle extends NumericSquareBaB {
	/**
	 * Constructor for Pyramid Puzzle objects
	 * @param board Representation of the board for playing the puzzle
	 */
    public NumericSquarePuzzle(NumericSquareBoard board) {
    	rootNode = board; //we create the puzzle to start playin
    	rootNode.calculateHeuristicValue();
    }
    
}
