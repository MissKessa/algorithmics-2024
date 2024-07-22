package topics.branchandbound;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.branchandbound.util.BranchAndBound;
import topics.branchandbound.util.Node;

/**
 * BRANCH AND BOUND PROBLEM: THE PROBLEM OF ASSIGNING N TASK TO AGENTS
 * 
 * @author viceg
 */
public class AgentsTasks extends BranchAndBound {
	/**
	 * Constructor for AgentsTasks objects
	 * 
	 * @param n Size of the matrix of costs
	 * @param c Matrix of costs
	 */
	public AgentsTasks(int n, int[][] c) {
		rootNode = new Table(n, c); // We create the table to start computing
	}

}

/***************************************************/

/***************************************************/
class Table extends Node {
	private static Logger log = LoggerFactory.getLogger(Table.class);
	private int[] partialSolution; // Vector to save the partial solution so far.
	// partialSolution[i] = j. Task j has been assigned to worker i
	private int[][] c; // Cost matrix
	private int n; // Size of the problem
	private boolean[] taskWithWorker; // To mark the tasks that need to be assigned

	// Generates a new costs matrix and an empty partial solution (ROOT NODE)
	public Table(int n, int[][] c) {
		super();
		this.n = n;
		this.c = c;

		// PRINT COSTS MATRIX:
		log.debug("COSTS MATRIX");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				sb.append(String.format("%5d", c[i][j]));
			sb.append("\n");
		}
		log.debug(sb.toString());

		partialSolution = new int[n];
		for (int i = 0; i < partialSolution.length; i++)
			partialSolution[i] = -1; // Initially, no assignments

		taskWithWorker = new boolean[n];
	}

	public Table(Table parent, int j) { // To create a state from the parent when we expead it to create chuldren
		super();

		// The cost and the size is the same:
		n = parent.n;
		c = parent.c;

		// Partial solution and marked tasks are also the same
		partialSolution = parent.partialSolution.clone();
		taskWithWorker = parent.taskWithWorker.clone();

		// Depth/level is also the same:
		depth = parent.depth;
		parentID = parent.getID();

		// Mark task:
		partialSolution[depth] = j; // Depth corresponds to the first agent that is not assigned
		taskWithWorker[j] = true; // We mark the task as assigned
		depth++;

		// Calculate new heuristic for child:
		calculateHeuristicValue();

		if (depth == n - 1) { // Join the last two levels of the tree of states
								// (if there are only one more task to be assigned) -> at last level there is
								// only one task free for last worker
			int taskToBeAssigned = -1;

			for (int k = 0; k < n; k++) // Look for the last task to be assigned
				if (!taskWithWorker[k])
					taskToBeAssigned = k;

			// Assign last task:
			partialSolution[depth] = taskToBeAssigned;// Depth corresponds to the first agent that is not assigned
			taskWithWorker[taskToBeAssigned] = true; // We mark the task as assigned
			depth++; // At this point depth == n
			calculateHeuristicValue();
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("===============\n");
		for (int i = 0; i < partialSolution.length; i++) {
			if (partialSolution[i] != -1)
				sb.append("THE TASK " + partialSolution[i] + " IS ASSIGNED TO THE WORKER " + i + "\n");
			else
				sb.append("THE TASK FOR WORKER " + i + " IS NOT ASSIGNED YET \n");
		}
		sb.append("Heuristic value = " + heuristicValue + "\n");
		sb.append("===============\n");
		return sb.toString();
	}

	/*
	 * PRUNING METHOD (bounding) heuristic. Initially, the limit of pruning is the
	 * smallest of the sums of the two diagonals of the matrix of costs
	 */
	@Override
	public int initialValuePruneLimit() {
		int diagonal1 = 0;
		for (int i = 0; i < c.length; i++)
			diagonal1 += c[i][i];

		int diagonal2 = 0;
		for (int i = 0; i < c.length; i++)
			diagonal2 += c[c.length - 1 - i][i];

		return (diagonal1 < diagonal2) ? diagonal1 : diagonal2; // Returns the smallest one
	}

	@Override
	public void calculateHeuristicValue() {
		heuristicValue = 0;

		// Sum the costs of the agents that has a task assigned
		for (int i = 0; i < depth; i++)
			heuristicValue += c[i][partialSolution[i]];

		// Sum the minimum of the columns (tasks) that are not assigned to a worker
		for (int j = 0; j < n; j++)
			if (!taskWithWorker[j])
				heuristicValue += minimumColumn(j);
	}

	private int minimumColumn(int j) { // Searchs for minimum value at the given column
		int minValueColumn = Integer.MAX_VALUE;
		for (int i = depth; i < n; i++)
			// Calculate the minimum of the column
			if (c[i][j] < minValueColumn)
				minValueColumn = c[i][j];
		return minValueColumn;
	}

	/*
	 * To get the children of the current node. They point to their parent through
	 * the parentID
	 */
	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> result = new ArrayList<Node>();

		// Iterate through the tasks
		for (int j = 0; j < n; j++)
			if (!taskWithWorker[j]) { // Create a child for not assigned tasks
				Node child = new Table(this, j);
				result.add(child);
			}
		return result;
	}

	@Override
	public boolean isSolution() {
		if (depth == n) // reach last level
			return true;
		else
			return false;
	}

} // Table
/***************************************************/
