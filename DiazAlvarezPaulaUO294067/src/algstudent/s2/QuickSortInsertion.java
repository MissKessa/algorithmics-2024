package algstudent.s2;

/* This program is used to sort n elements with a quadratic algorithm
except if the vector is initially ordered or nearly ordered,
which is of linear complexity (INSERTION) */
public class QuickSortInsertion {
	static int[] v;

	/* Sorting by the Insertion method */
	public static void insertion(int[] a, int left, int right) {
		int j;
		int pivot;
		int n = right;

		for (int i = left + 1; i < n; i++) {
			pivot = a[i];
			j = i - 1;

			while (j >= left && pivot < a[j]) {
				a[j + 1] = a[j];
				j--;
			}

			a[j + 1] = pivot;
		}
	}

	public static int median_of_three(int[] a, int left, int right) {
		int center = (left + right) / 2;
		if (a[left] > a[center])
			Vector.interchange(a, left, center);
		if (a[left] > a[right])
			Vector.interchange(a, left, right);
		if (a[center] > a[right])
			Vector.interchange(a, center, right);
		return center;
	}

	public static void quicksort(int[] a, int k) {
		quicksort(a, 0, a.length - 1, k);
	}

	public static void quicksort(int[] a, int left, int right, int k) {
		int i = left;
		int j = right - 1;
		int pivot;

		if (right > left) { // if there is one element it is not necessary
			int center = median_of_three(a, left, right);
			// if there are less than or equal to 3 elements, there are just ordered
			if ((right - left) > k) {
				pivot = a[center]; // choose the pivot
				Vector.interchange(a, center, right); // hide the pivot

				do {
					while (a[i] <= pivot && i < right)
						i++; // first element > pivot
					while (a[j] >= pivot && j > left)
						j--; // first element < pivot
					if (i < j)
						Vector.interchange(a, i, j);
				} while (i < j); // end while

				// we set the position of the pivot
				Vector.interchange(a, i, right);
				quicksort(a, left, i - 1, k);
				quicksort(a, i + 1, right, k);
			} // if
			else {
				insertion(a, left, right);
			}
		} // if

	}

}
