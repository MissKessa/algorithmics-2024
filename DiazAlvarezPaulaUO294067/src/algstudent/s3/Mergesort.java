package algstudent.s3;

/* This program is used to order n elements with Quicksort */
public class Mergesort {

	public static void mergesort(int[] a) {
		mergesort(a, 0, a.length - 1);
	}

	public static void mergesort(int[] a, int left, int right) {
		if (right > left) {
			int center = (left + right) / 2;
			mergesort(a, left, center);
			mergesort(a, center + 1, right);
			combine(a, left, center, center + 1, right);
		}

	}

	private static void combine(int[] a, int xi, int xf, int yi, int yf) {
		int intervalXSize = xf - xi + 1;
		int intervalYSize = yf - yi + 1;
		int counterX = 0;
		int counterY = 0;

		int[] x = new int[intervalXSize];
		int[] y = new int[intervalYSize];

		for (int i = 0; i < intervalXSize; i++) {
			x[i] = a[xi + i];
		}

		for (int i = 0; i < intervalYSize; i++) {
			y[i] = a[yi + i];
		}

		int elemX = 0;
		int elemY = 0;

		while (counterX < intervalXSize || counterY < intervalYSize) {
			if (counterX >= intervalXSize) {
				a[xi + counterX + counterY] = elemY;
				counterY++;
			} else if (counterY >= intervalYSize) {
				a[xi + counterX + counterY] = elemX;
				counterX++;
			} else {
				elemX = x[counterX];
				elemY = y[counterY];

				if (elemX > elemY) {
					a[xi + counterX + counterY] = elemY;
					counterY++;
				} else {
					a[xi + counterX + counterY] = elemX;
					counterX++;
				}
			}
		}
	}

}
