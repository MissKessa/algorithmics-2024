package topics.practicingLab;

public class Backtracking {

	int v[] = { 0, 1, 2, 3, 4, 5 };
	int[] solution = new int[v.length];
	boolean[] used = new boolean[v.length];

	public void permutations() {
		permutationsBacktracking(0);
	}

	public void permutationsBacktracking(int level) {
		if (level == v.length) {
			for (int i = 0; i < solution.length; i++) {
				System.out.print(solution[i]);
			}
			System.out.println("-------");
		} else {
			for (int i = 0; i < v.length; i++) {
				if (!used[i]) {
					solution[level] = v[i];
					used[i] = true;
					permutationsBacktracking(level + 1);
					solution[level] = 0;
					used[i] = false;
				}
			}
		}
	}

	int valueForSum = 10;

	public void subsetsSumBacktracking(int level, int sum) {
		if (level == v.length) {
			for (int i = 0; i < v.length; i++) {
				if (used[i]) {
					System.out.print(v[i]);
				}
			}
			System.out.println("-------");
		} else {
			for (int i = 0; i < 2; i++) {
				if (i == 0) { // not take number
					subsetsSumBacktracking(level + 1, sum);
				} else {
					if (sum + v[level] <= valueForSum) {
						used[level] = true;
						subsetsSumBacktracking(level + 1, sum + v[level]);
						used[level] = false;
					}
				}
			}
		}

	}

	// for example baord is 5x5
	int n = 5;
	boolean[] horizontal = new boolean[n];
	boolean[] leftUpDiagonal = new boolean[2 * n + 1];
	boolean[] rightUpDiagonal = new boolean[2 * n + 1];
	boolean solutionFound = false;
	int[] queens = new int[n];

	public void queensOneSolutionBacktracking(int j) { // j=column
		if (j == n) {
			solutionFound = true;

		} else {
			for (int i = 0; i < n && !solutionFound; i++) {
				if (!horizontal[i] && !leftUpDiagonal[i + j] && !rightUpDiagonal[i - j + (n - 1)]) {
					queens[j] = i;
					horizontal[i] = true;
					leftUpDiagonal[i + j] = true;
					rightUpDiagonal[i - j + (n - 1)] = true;
					queensOneSolutionBacktracking(j + 1);
					horizontal[i] = false;
					leftUpDiagonal[i + j] = false;
					rightUpDiagonal[i - j + (n - 1)] = false;
					queens[j] = i;
				}
			}
		}
	}

	int[] horizontalMovements = { 2, 1, -1, -2, -2, -1, 1, 2 };
	int[] verticalMovements = { 1, 2, 2, 1, -1, -2, -2, -1 };
	int[][] horseBoard = new int[n][n];

	public void horseBacktracking(int x, int y, int movements) {
		if (movements == n * n + 1) {
			solutionFound = true;
		} else {
			for (int i = 0; i < horizontalMovements.length && !solutionFound; i++) {
				int newX = x + horizontalMovements[i];
				int newY = y + verticalMovements[i];
				if (newX >= 0 && newX < n && newY >= 0 && newY < n && horseBoard[newX][newY] == 0) {
					horseBoard[newX][newY] = movements;
					horseBacktracking(newX, newY, movements + 1);
					horseBoard[newX][newY] = 0;
				}
			}
		}
	}

	int[][] boardAgentsTasks = new int[n][n];
	boolean[] tasksMarked = new boolean[n];

	public void assigningTasksToAgentsBacktracking(int i) {
		if (i == n) {
			solutionFound = true;
		} else {
			for (int j = 0; j < n; j++) {
				if (!tasksMarked[j]) {
					tasksMarked[j] = true;
					assigningTasksToAgentsBacktracking(i + 1);
					tasksMarked[j] = false;
				}
			}
		}
	}
}
