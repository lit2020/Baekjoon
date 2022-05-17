package 백준2217;

import java.util.Scanner;

public class Main {
    private static int[] sorted = null;
    private static int number_of_rope;
    private static int[] load_of_ropes = null;

    /**
     * User enter the number of rope and maximum load of each rope
     */
    private static void input() {
	Scanner scanner = new Scanner(System.in);
	number_of_rope = Integer.parseInt(scanner.nextLine());
	sorted = new int[number_of_rope];
	load_of_ropes = new int[number_of_rope];
	for (int i = 0; i < number_of_rope; i++) {
	    load_of_ropes[i] = Integer.parseInt(scanner.nextLine());
	}
	scanner.close();
    }

    /*
     * merge by ascending order
     */
    public static void merge(int[] arr, int left, int mid, int right) {
	int i = left;
	int j = mid + 1;
	int k = left;

	while (i <= mid && j <= right) {
	    if (arr[i] >= arr[j]) {
		sorted[k++] = arr[i++];
	    } else {
		sorted[k++] = arr[j++];
	    }
	}

	if (i <= mid) {
	    for (int l = i; l <= mid; l++) {
		sorted[k++] = arr[l];
	    }
	} else if (j <= right) {
	    for (int l = j; l <= right; l++) {
		sorted[k++] = arr[l];
	    }
	}

	for (int l = left; l <= right; l++) {
	    arr[l] = sorted[l];
	}
    }

    public static void mergeSort(int[] arr, int left, int right) {
	if (left < right) {
	    int mid = (left + right) / 2;
	    mergeSort(arr, left, mid);
	    mergeSort(arr, mid + 1, right);
	    merge(arr, left, mid, right);
	}
    }

    private static int solution(int[] arr) {
	int max_load = arr[0];
	// int peak_point = 0;
	for (int i = 1; i < number_of_rope; i++) {
	    if ((i + 1) * arr[i] > max_load) {
		max_load = (i + 1) * arr[i];
		// peak_point = i;
	    }
	}
	// System.out.println("peak point = " + peak_point);
	return max_load;
    }

    public static void main(String[] args) {
	input();
	mergeSort(load_of_ropes, 0, number_of_rope - 1);
	int answer = solution(load_of_ropes);
	System.out.println(answer);
	// System.out.println("maximum load (answer) = " + answer);
    }
}
