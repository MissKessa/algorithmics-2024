package topics.practicingLab;

public class DynamicProgramming {

	int[] fibbonacci;

	public void FibbonacciDynamic() {
		fibbonacci = new int[100];
		fibbonacci[0] = 0;
		fibbonacci[1] = 1;
	}

	public int calculateFibbonacci(int numberElement) {
		if (numberElement != 1 && fibbonacci[numberElement - 1] == 0) {
			fibbonacci[numberElement - 1] = calculateFibbonacci(numberElement - 1)
					+ calculateFibbonacci(numberElement - 2);
			return fibbonacci[numberElement - 1];

		} else {
			return fibbonacci[numberElement];
		}
	}

	public int CombinationDynamic(int n, int k) {
		int[][] combinations = new int[n + 1][k + 1];
		// n<k -> 0
		// n, 0 -> 1 (first column)
		// n,n -> 1 (diagonal)
		for (int i = 0; i < n; i++) {
			combinations[i][0] = 1;
			if (i != 0) {
				combinations[i][1] = i;
			}
			combinations[i][i] = 1;
			for (int j = 2; j < i; j++) {
				combinations[i][j] = combinations[i - 1][j - 1] + combinations[i - 1][j];
			}
		}
		return combinations[n + 1][k + 1];
	}

	public int KnapsackDynamic(int maxWeight) {
		int[] valueObjects = { 1, 3, 3 };
		int[] weightObjects = { 20, 50, 30 };
		int[][] v = new int[valueObjects.length][maxWeight + 1];

		for (int j = weightObjects[0]; j < maxWeight; j++) { // first object
			v[0][j] = weightObjects[0];
		}

		for (int i = 1; i < valueObjects.length; i++) { // rest of objects
			for (int j = 0; j <= maxWeight; j++) {
				int notThisObject = v[i - 1][j];
				int thisObject = Integer.MIN_VALUE;

				if (j >= weightObjects[i]) {
					thisObject = v[i - 1][j - weightObjects[i]] + valueObjects[i];
				}
				v[i][j] = Math.max(notThisObject, thisObject);
			}
		}

		return v[valueObjects.length - 1][maxWeight];
	}

	public int ChangeMoneyDynamic(int amountToPay) {
		int[] coins = { 1, 2, 5, 10, 20 };
		int[][] v = new int[coins.length][amountToPay + 1];

		for (int j = 0; j < amountToPay; j++) { // first coin is one cent/euro
			v[0][j] = 1;
		}

		for (int i = 1; i < coins.length; i++) { // rest of objects
			for (int j = 0; j <= amountToPay; j++) {
				int notThisCoin = v[i - 1][j];
				int thisCoin = Integer.MAX_VALUE;

				if (j >= coins[i]) {
					thisCoin = v[i][j - coins[i]] + 1;
				}
				v[i][j] = Math.min(notThisCoin, thisCoin);
			}
		}

		return v[coins.length - 1][amountToPay];
	}

	public void CheaperTravelDynamic() {
		int[][] tableCosts = { { -1, 3, 8, 9, 20 }, { -1, -1, 5, 5, 2 }, { -1, -1, -1, 3, 6 }, { -1, -1, -1, -1, 2 },
				{ -1, -1, -1, -1, -1 } };
		int[][] pivots = { { -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1 } };

		for (int i = 1; i < tableCosts.length; i++) {
			for (int j = i + 1; j < tableCosts.length; j++) {
				for (int k = 0; k < tableCosts.length; k++) {
					if (tableCosts[i][j] > tableCosts[i][k] + tableCosts[k][j]) {
						tableCosts[i][j] = tableCosts[i][k] + tableCosts[k][j];
						pivots[i][j] = k;
					}
				}
			}
		}

	}
}
