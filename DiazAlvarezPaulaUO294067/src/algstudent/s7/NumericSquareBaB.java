package algstudent.s7;

import java.util.ArrayList;

public class NumericSquareBaB {
	protected Heap notUsedNodes;
	protected Node bestNode; // final node for the best solution
	protected Node rootNode; // initial node
	protected int pruneLimit; // to prune nodes above this value

	/**
	 * Constructor for NumericSquareBab
	 */
	public NumericSquareBaB() {
		notUsedNodes = new Heap();
	}

	public void branchAndBound(Node rootNode) {
		int numberNodes = 0;
		notUsedNodes.insert(rootNode); // First node to be explored
		pruneLimit = rootNode.initialValuePruneLimit() + 1;
//		boolean found = false;

		while (!notUsedNodes.empty() && notUsedNodes.estimateBest() < pruneLimit) {
			Node node = notUsedNodes.extractBestNode();

			ArrayList<Node> children = node.expand();

			for (Node child : children) {

				if (child.isSolution()) {
					int cost = child.getHeuristicValue();
					if (cost < pruneLimit) {
						pruneLimit = cost;
						bestNode = child;
//						found = true;
//						break;
					}

				} else if (child.getHeuristicValue() < pruneLimit) {
					notUsedNodes.insert(child);
				}
				numberNodes++;
			}
		} // while

		System.out.println("Number of developed nodes: " + numberNodes);
	}

	/**
	 * Gets the root node
	 * 
	 * @return The root node
	 */
	public Node getRootNode() {
		return rootNode;
	}

	/**
	 * Gets the best node
	 * 
	 * @return The best node
	 */
	public Node getBestNode() {
		return bestNode;
	}

	/**
	 * Prints the solution from the root node to the best node
	 */
	public void printSolutionTrace() {
		if (bestNode == null) {
			System.out.println("Original:");
			System.out.println(rootNode.toString());
			System.out.println("THERE IS NO SOLUTION");
		} else {
			// Extract the path of the used nodes from bestNode to the rootNode
			ArrayList<Node> result = notUsedNodes.extractUsedNodesFrom(bestNode);

			for (int i = 0; i < result.size(); i++) {
				if (i == 0)
					System.out.println("Original:");
				else
					System.out.println("Step " + i + ":");
				System.out.println(result.get(result.size() - i - 1).toString());
			}
			System.out.println("\nSolution with " + bestNode.getDepth() + " step(s).");
		}
	}
}
