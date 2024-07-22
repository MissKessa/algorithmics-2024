package topics.practicingLab.BranchAndBound;

import java.util.ArrayList;
import java.util.UUID;

import topics.practicingLab.BranchAndBound.util.BranchAndBound;
import topics.practicingLab.BranchAndBound.util.Node;

//this class extends BranchAndBound -> just constructor rootNode=initialConstructorNode of Table
//Table extends Node -> all methods:
//--2 constructors: normal & child
//--toSring (override)
//--initialValuePruneLimit (override)
//--calculateHeuristicValue (override)
//--expand (override) -> create children
//--isSolution (override)
public class AgentTasksBaB extends BranchAndBound {

	public AgentTasksBaB(int n, int[][] tableAgentsTasks) {
		rootNode = new Table(n, tableAgentsTasks);
	}

	public class Table extends Node {
		private int[][] tableAgentsTasks;
		private int n;
		private int[] solution;
		private boolean[] markedTasks;

		public Table(int n, int[][] tableAgentsTasks) {
			this.tableAgentsTasks = tableAgentsTasks;
			this.markedTasks = new boolean[n];
			this.n = n;
			depth = 0;
			for (int i = 0; i < solution.length; i++) {
				solution[i] = -1;
			}
			calculateHeuristicValue();
		}

		public Table(int n, int[][] tableAgentsTasks, int[] solution, boolean[] markedTasks, UUID parent, int depth) {
			this.tableAgentsTasks = tableAgentsTasks;
			this.solution = solution.clone();
			this.markedTasks = markedTasks.clone();
			parentID = parent;
			this.n = n;
			this.depth = depth;
			calculateHeuristicValue();
		}

		@Override
		public int initialValuePruneLimit() {
			int leftDiagonal = 0;
			int rightDiagonal = 0;
			for (int i = 0; i < n; i++) {
				leftDiagonal += tableAgentsTasks[i][i];
				rightDiagonal += tableAgentsTasks[n - 1 - i][i];
			}
			return Math.max(leftDiagonal, rightDiagonal);
		}

		@Override
		public void calculateHeuristicValue() {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				if (solution[i] == -1) {
					// find minimum between columns not marked
					int min = Integer.MAX_VALUE;
					for (int j = 0; j < n; j++) {
						if (min > tableAgentsTasks[i][j] && !markedTasks[j]) {
							min = tableAgentsTasks[i][j];
						}
					}
					sum += min;
				} else {
					sum += tableAgentsTasks[i][solution[i]];
				}
			}
			heuristicValue = sum;
		}

		@Override
		public ArrayList<Node> expand() {
			ArrayList<Node> children = new ArrayList<Node>();
			for (int j = 0; j < n; j++) {
				if (!markedTasks[j]) {
					solution[depth] = j;
					markedTasks[j] = true;
					children.add(new Table(n, tableAgentsTasks, solution, markedTasks, ID, depth + 1));
					solution[depth] = -1;
					markedTasks[j] = false;
				}
			}
			return children;
		}

		@Override
		public boolean isSolution() {
			return depth == n;
		}

	}
}
