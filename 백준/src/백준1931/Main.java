/**
 * SOLVED : True
 * DATE : 2022.05.11
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1931 (회의실 배정)
 * ALGORITMN : Greedy, Sorting
 * NOTE : 
 */

package 백준1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {

    private void swap(int[][] arr, int i, int j) {
	int[] temp = arr[i];
	arr[i] = arr[j];
	arr[j] = temp;
    }

    private int partition(int[][] arr, int left, int right) {
	int pivotIdx = (left + right) / 2;
	int pivot = arr[pivotIdx][1];
	int toRight = left + 1;
	int toLeft = right;
	swap(arr, left, pivotIdx);
	while (toLeft > toRight) {
	    while (arr[toRight][1] <= pivot && toRight < right)
		++toRight;
	    while (arr[toLeft][1] > pivot)
		--toLeft;
	    if (toLeft > toRight)
		swap(arr, toLeft, toRight);
	}
	swap(arr, toLeft, left);
	return toLeft;

    }

    protected void quickSort(int[][] arr, int left, int right) {
	// target array has less than 2 elements;
	if (left >= right)
	    return;

	int mid = partition(arr, left, right);
	quickSort(arr, left, mid - 1);
	quickSort(arr, mid + 1, right);

    }

    protected int solution(int[][] confs) {

	// sort conference by end time by ascending order
	quickSort(confs, 0, confs.length - 1);
	// select a conference that end most early
	int endOflastConf = confs[0][1];
	int possibleCount = 1;
	for (int i = 1; i < confs.length; i++) {
	    int[] conf = confs[i];
	    if (conf[0] >= endOflastConf) {
		if (conf[0] <= conf[1]) {
		    ++possibleCount;
		    endOflastConf = conf[1];
		}
	    }
	}
	return possibleCount;
    }
}


public class Main {

    private static int[][] conference;

    private static void input() throws NumberFormatException, IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int N = Integer.parseInt(br.readLine());
	conference = new int[N][2];
	for (int i = 0; i < N; i++) {
	    String[] line = br.readLine().split(" ");
	    conference[i] = Arrays.stream(line).mapToInt(Integer::parseInt).toArray();
	}
    }

    private static void printAnswer(int answer) {
	System.out.print(answer);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
	input();

	int answer = (new Solution()).solution(conference);

	printAnswer(answer);
    }
}
