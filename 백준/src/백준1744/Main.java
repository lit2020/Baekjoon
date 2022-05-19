/**
 * SOLVED : Solved
 * DATE : 2022.05.18
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1744 (수 묶기)
 * ALGORITMN : Greedy, Sorting, Data structure, Priority queue, Many branch (많은 조건 분기)
 * 
 * NOTE : lowerBound, upperBound 구현
 */

package 백준1744;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

class Solution {

    private void swap(int[] arr, int i, int j) {
	int temp = arr[i];
	arr[i] = arr[j];
	arr[j] = temp;
    }

    private int partition(int[] arr, int low, int high) {
	int idxPivot = (low + high) / 2;
	int pivot = arr[idxPivot];
	int left = low;
	int right = high + 1;
	this.swap(arr, left, idxPivot);

	do {
	    do {
		++left;
	    } while (left < high && arr[left] < pivot);
	    do {
		--right;
	    } while (arr[right] > pivot);

	    if (left < right)
		this.swap(arr, left, right);
	} while (left < right);
	this.swap(arr, right, low);
	return right;
    }

    private void quickSort(int[] arr, int left, int right) {
	if (left < right) {
	    int mid = this.partition(arr, left, right);
	    quickSort(arr, left, mid - 1);
	    quickSort(arr, mid + 1, right);
	}
    }


    protected int lowerBound(int sorted[], int target) {
	int low = 0;
	int high = sorted.length;
	int mid;
	while (low < high) {
	    mid = (low + high) / 2;
	    if (sorted[mid] < target) {
		low = mid + 1;
	    } else if (sorted[mid] >= target) {
		high = mid;
	    }
	}
	return high;
    }

    protected int upperBound(int sorted[], int target) {
	int low = 0;
	int high = sorted.length;
	int mid;
	while (low < high) {
	    mid = (low + high) / 2;
	    if (sorted[mid] <= target) {
		low = mid + 1;
	    } else if (sorted[mid] > target) {
		high = mid;
	    }
	}
	return high;
    }

    private int indexOfFirstNonnegative(int[] sorted) {
	int low = 0;
	int high = sorted.length - 1;
	int mid;
	while (low < high) {
	    mid = (low + high) / 2;
	    if (sorted[mid] >= 0) {
		high = mid;
	    } else if (sorted[mid] < 0) {
		low = mid + 1;
	    }
	}
	return (sorted[high] >= 0) ? high : -1;
    }

    protected int solution(int[] arr) {
	if(arr.length == 1)
	    return arr[0];
	
	int answer = 0;
	// sort @arr ascending order : O(nlgn)
	this.quickSort(arr, 0, arr.length - 1);

	List<Integer> al = Arrays.asList(Arrays.stream(arr).boxed().toArray(Integer[]::new));
	int zeroBegin = al.indexOf(0);
	int zeroEnd = al.lastIndexOf(0);
	int zeroCnt = (zeroBegin == -1) ? 0 : zeroEnd - zeroBegin + 1;
	int positiveBegin = (zeroCnt == 0) ? this.indexOfFirstNonnegative(arr) : zeroEnd+1;
	int positiveCnt = (positiveBegin > -1) ? arr.length - positiveBegin : 0;
	int negativeEnd = (zeroCnt == 0) ? positiveBegin - 1 : zeroBegin - 1;
	int negativeCnt = arr.length - zeroCnt - positiveCnt;
	for (int i = 0; i < negativeCnt / 2; i++) {
	    answer += arr[2*i] * arr[2*i+1];
	}
	if (negativeCnt % 2 == 1 && zeroCnt == 0) {
	    answer += arr[negativeEnd];
	}
	while ((positiveCnt > 0) && (arr[positiveBegin] == 1)) {
	    ++answer;
	    --positiveCnt;
	    ++positiveBegin;
	}
	if ((positiveCnt > 0) && (positiveCnt % 2 == 1)) {
	    answer += arr[positiveBegin++];
	    --positiveCnt;
	}
	for (int i = 0; i < positiveCnt / 2; i++) {
	    answer += arr[positiveBegin + 2*i] * arr[positiveBegin + 2*i + 1];
	    
 	}
	
	return answer;
    }
}

public class Main {

    private static int[] input;
    private static int answer;

    public static void main(String[] args) {
	try {
	    input();
	} catch (NumberFormatException | IOException e) {
	}
	Solution sol = new Solution();
	answer = sol.solution(input);
	printAnswer();
    }

    private static void input() throws NumberFormatException, IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int len = Integer.parseInt(br.readLine());
	input = new int[len];
	for (int i = 0; i < len; i++) {
	    input[i] = Integer.parseInt(br.readLine());
	}
    }
    
    private static void printAnswer() {
	System.out.print(answer);
    }

}
