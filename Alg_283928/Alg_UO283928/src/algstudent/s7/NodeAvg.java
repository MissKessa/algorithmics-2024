package algstudent.s7;

import java.util.ArrayList;

public class NodeAvg extends Node {

	private static final int NOT_YET_A_SOLUTION = 0;
	private static final int DEFAULT_SIZE = 256;
	ArrayList<Integer> currentCombination = new ArrayList<Integer>(); // For storing current combination of nodes
	private int solutionLength;
	private ImageBnB[] dataset;
	
	public NodeAvg( int depth, int nextCombination,
			ArrayList<Integer> currentCombination, int solutionLength,
			ImageBnB[] dataset) {
		super(); 
		super.depth = depth; 
		
		this.currentCombination = new ArrayList<Integer>( currentCombination );
		
		this.solutionLength = solutionLength;
		this.dataset = dataset;
		
		calculateHeuristicValue();
	}

	@Override
	public void calculateHeuristicValue() {
		if( !isSolution() ) {
			super.heuristicValue = NOT_YET_A_SOLUTION;
		}
		else {
			ImageBnB auxHalf1 = new ImageBnB( DEFAULT_SIZE, DEFAULT_SIZE );
			ImageBnB auxHalf2 = new ImageBnB( DEFAULT_SIZE, DEFAULT_SIZE );
			
			for ( int i = 0; i < solutionLength; i++ ) {
				if( currentCombination.get(i) == 1 ) auxHalf1.addSignal(dataset[i]);
				if( currentCombination.get(i) == 2 ) auxHalf2.addSignal(dataset[i]);
			}
			
			super.heuristicValue = -auxHalf1.zncc(auxHalf2);
		}
		
	}

	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> ret = new ArrayList<Node>();
		ArrayList<Integer> currentCombination2 = new ArrayList<Integer>(currentCombination);
		
		currentCombination2.set(depth, 0);
		ret.add(new NodeAvg(depth+1, 0, currentCombination2,solutionLength, dataset));
		currentCombination2.set(depth, 1);
		ret.add(new NodeAvg(depth+1, 1, currentCombination2,solutionLength, dataset));
		currentCombination2.set(depth, 2);
		ret.add(new NodeAvg(depth+1, 2, currentCombination2,solutionLength, dataset));
		
		return ret;
	}

	@Override
	public boolean isSolution() {
		return depth == solutionLength;
	}

	@Override
	public ArrayList<Integer> getSolution() {
		return currentCombination;
	}

	@Override
	public String toString() {
		return "NodeAvg [currentCombination=" + currentCombination + "]";
	}

	
}
