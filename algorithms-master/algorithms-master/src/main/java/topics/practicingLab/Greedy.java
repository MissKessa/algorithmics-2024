package topics.practicingLab;

public class Greedy {

	public int changeGreedy(int amount) {
		int[] valueCoins = { 1, 2, 5, 10, 20 };
		int numberCoins = 0;
		int positionCoin = valueCoins.length - 1;

		while (amount > 0 && positionCoin >= 0) {
			if (amount < valueCoins[positionCoin]) {
				positionCoin--;
			} else {
				amount -= valueCoins[positionCoin];
				numberCoins++;
			}
		}

		return numberCoins;
	}

	public int maxNumberFilesGreedy(int diskCapacity) {
		int[] filesWeight = { 10, 25, 50, 70, 80 };
		int numberFiles = 0;
		int positionFiles = 0;

		while (diskCapacity > 0 && positionFiles < filesWeight.length) {
			if (diskCapacity < filesWeight[positionFiles]) {
				break;
			} else {
				diskCapacity -= filesWeight[positionFiles];
				positionFiles++;
				numberFiles++;
			}
		}

		return numberFiles;
	}

	public int minizimeFreeSpaceGreedy(int diskCapacity) {
		int[] filesWeight = { 10, 25, 50, 70, 80 };
		int numberFiles = 0;
		int positionFiles = filesWeight.length - 1;

		while (diskCapacity > 0 && positionFiles > 0) {
			if (diskCapacity < filesWeight[positionFiles]) {
				break;
			} else {
				diskCapacity -= filesWeight[positionFiles];
				positionFiles++;
				numberFiles++;
			}
		}

		return numberFiles;
	}

	public int knapsackGreedy(int maxWeight) {
		int[] values = { 1, 3, 3 };
		int[] weights = { 10, 5, 15 };
		float[] valueWeight = { 1 / 10, 3 / 5, 3 / 15 };

		int totalValue = 0;
		int currentWeight = 0;
		while (currentWeight != maxWeight) {
			int position = bestValueWeight(valueWeight);
			if (position == -1) {
				break;

			} else {
				if (currentWeight + weights[position] <= maxWeight) {
					valueWeight[position] = Integer.MAX_VALUE;
					currentWeight += weights[position];
					totalValue += values[position];
				}
			}
		}

		return totalValue;
	}

	private int bestValueWeight(float[] valueWeight) {
		int minPos = -1;
		float minValueWeight = Integer.MAX_VALUE;

		for (int i = 0; i < valueWeight.length; i++) {
			if (valueWeight[i] < minValueWeight) {
				minPos = i;
				minValueWeight = valueWeight[i];
			}
		}
		return minPos;
	}

	public int oneDiligentPlumberGreedy() {
		int[] tasksDuration = { 2, 3, 4, 8, 10 };
		int waitingTime = 0;

		for (int i = 0; i < tasksDuration.length; i++) {
			waitingTime += waitingTime + tasksDuration[i];
		}
		return waitingTime;
	}

	public int someDiligentPlumbersGreedy(int plumbers) {
		int[] tasksDuration = { 2, 3, 4, 8, 10 };
		int[] waitingTimes = new int[plumbers];
		int positionPlumber = 0;

		for (int i = 0; i < tasksDuration.length; i++) {
			waitingTimes[positionPlumber] += waitingTimes[positionPlumber] + tasksDuration[i];
			positionPlumber++;
			if (positionPlumber == plumbers) {
				positionPlumber = 0;
			}
		}

		int totalWaitingTime = 0;
		for (int i = 0; i < waitingTimes.length; i++) {
			totalWaitingTime += waitingTimes[i];
		}
		return totalWaitingTime;
	}

	public void horse() {
		// TO DO
	}

	public int[] assigningTasksToAgentsGreedy(int[][] board) {
		int[] agentsTasks = new int[board.length];
		boolean[] columnsUsed = new boolean[board.length];

		for (int i = 0; i < board.length; i++) {
			int minTasks = Integer.MAX_VALUE;
			int columnTask = Integer.MAX_VALUE;
			for (int j = 0; j < board.length; j++) {
				if (minTasks > board[i][j] && !columnsUsed[j]) {
					columnTask = j;
					minTasks = board[i][j];
				}
			}
			columnsUsed[columnTask] = true;
			agentsTasks[i] = minTasks;
		}

		return agentsTasks;
	}
}
