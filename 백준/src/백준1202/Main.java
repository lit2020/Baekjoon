/**
 * SOLVED : Time Over
 * DATE : 2022.05.13
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1202
 * ALGORITMN : Greedy, Sorting
 * NOTE :
 */

package 백준1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

class QuickSort {
    private boolean reverseOrder;

    protected QuickSort() {
	this(false);
    }

    protected QuickSort(boolean reverseOrder) {
	this.reverseOrder = reverseOrder;
    }

    private static void swap(int[][] arr, int i, int j) {
	int[] tmp = arr[i];
	arr[i] = arr[j];
	arr[j] = tmp;
    }

    private int compare(int a, int b) {
	int r;
	if (a < b)
	    r = -1;
	else if (a == b)
	    r = 0;
	else
	    r = 1;
	if (this.reverseOrder)
	    r = -r;
	return r;
    }

    private int partition(int[][] arr, int left, int right) {
	int pivotIdx = (left + right) / 2;
	int pivot = arr[pivotIdx][1];
	swap(arr, left, pivotIdx);

	int toRight = left + 1;
	int toLeft = right;
	while (true) {
	    while (compare(arr[toRight][1], pivot) < 0 && toRight < right)
		++toRight;

	    while (compare(arr[toLeft][1], pivot) > 0)
		--toLeft;

	    if (toRight < toLeft)
		swap(arr, toRight, toLeft);
	    else
		break;
	}
	swap(arr, toLeft, left);
	return toLeft;
    }

    private void quickSort(int[][] arr, int left, int right) {
	if (left < right) {
	    int mid = partition(arr, left, right);
	    quickSort(arr, left, mid - 1);
	    quickSort(arr, mid + 1, right);
	}
    }

    protected void sort(int[][] arr) {
	this.quickSort(arr, 0, arr.length - 1);
    }

}

class Solution {

    protected int solution(int[][] jew, int[] bag) {
	int answer = 0;

	// 보석정보(무게,가치)와 가방정보(중량)을 각각 정렬
	// 보석은 가치의 내림차순, 가방은 오름차순으로 정렬
	Arrays.sort(jew, new Comparator<int[]>() {

	    @Override
	    public int compare(int[] o1, int[] o2) {
		if(o1[1] > o2[1]) return -1;
		if(o1[1] < o2[1]) return 1;
		else return 0;
	    }
	    
	});
	Arrays.sort(bag);
	
	// 가장 가치가 큰 보석부터 선택하한다
	boolean[] isUsed = new boolean[bag.length];
	int nUsableBag = bag.length;
	for(int i = 0; i < jew.length; i++) {
	    if(nUsableBag == 0) // 모든 가방을 사용함
		break;
	    
	    int[] jewelry = jew[i];
	    int mass = jewelry[0];
	    int value = jewelry[1];
	    
	    // 선택한 보석을 넣을 가방을 찾는다.
	    // 크기가 작은 가방부터 순서대로 탐색하여, 보석을 넣을수 있는 첫번째 가방에 넣는다.
	    for(int j = 0; j < bag.length; j++) {
		// 넣은 보석의 가치를 answer에 더하고
		// 사용한 가방은 사용한 것으로 체크한다 (가방에는 최대 1개의 보석만)
		if(bag[j] >= mass && !isUsed[j]) {
		    answer += value;
		    isUsed[j] = true;
		    --nUsableBag;
		    break;
		}
	    }
	}	
	
	return answer;
    }
}

public class Main {
    private static int nJewelry;
    private static int nBag;
    private static int dbg_T;
    private static int[][] jewelry;
    private static int[] bag_size;

    private static void input() throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] number = br.readLine().split(" ");
	nJewelry = Integer.parseInt(number[0]);
	nBag = Integer.parseInt(number[1]);
	jewelry = new int[nJewelry][2];
	bag_size = new int[nBag];

	String[] line = null;
	for (int i = 0; i < nJewelry; i++) {
	    line = br.readLine().split(" ");
	    jewelry[i][0] = Integer.parseInt(line[0]);
	    jewelry[i][1] = Integer.parseInt(line[1]);
	}

	for (int i = 0; i < nBag; i++) {
	    bag_size[i] = Integer.parseInt(br.readLine());
	}
    }

    private static void printAnswer(int answer) {
	System.out.print(answer);
    }
    public static void main(String[] args) throws IOException {
	input();
	int answer = (new Solution()).solution(jewelry, bag_size);
	printAnswer(answer);
	/*
	Random random = new Random();
	for(int i = 0; i < T; i++) {
	    jewelry[i][0] = i;
	    jewelry[i][1] = random.nextInt(10000);
	}
	QuickSort qs = new QuickSort(true);
	int[][] copied = jewelry.clone();
	qs.sort(jewelry);
	Arrays.sort(copied, new Comparator<int[]>() {
	    @Override
	    public int compare(int[] o1, int[] o2) {
		if(o1[1] > o2[1]) return -1;
		if(o1[1] < o2[1]) return 1;
		return 0;
	    }
	    
	});
	boolean correct = true;
	for(int i = 0; i < T; i++) {
	    if(jewelry[i][0] != copied[i][0] || jewelry[i][1] != copied[i][1])
		correct = false;
	}
	System.out.println(correct);
	*/

    }

}
