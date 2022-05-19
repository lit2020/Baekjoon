/**
 * SOLVED : Solved
 * DATE : 2022.05.18
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1202 (보석 도둑)
 * ALGORITMN : Greedy, Sorting, Data structure, PriorityQueue
 * NOTE : 
 */

package 백준1202v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    protected long solution(int[][] jew, int[] bag) {
	long answer = 0;
	PriorityQueue<Integer> pq = new PriorityQueue<Integer>(jew.length, Collections.reverseOrder());
	// sort jewelries ascending order of its weight
	Arrays.sort(jew, new Comparator<int[]>() {
	    @Override
	    public int compare(int[] o1, int[] o2) {
		return o1[0] - o2[0];
	    }
	});
	// sort bags ascending order of its maximum capacity
	Arrays.sort(bag);

	int idx_jew = 0;
	// choose from small bag in order
	// : O(K)
	for (int bag_size : bag) {
	    // choose from light jewelry, push its value in priority queue (max heap)
	    while (idx_jew < jew.length && jew[idx_jew][0] <= bag_size) {
		pq.add(jew[idx_jew++][1]);
	    }
	    if (pq.isEmpty())
		continue;
	    answer += pq.poll();
	}
	return answer;
    }
}

public class Main {

    private static int nJewelry, nBag;
    private static int[][] jewelry;
    private static int[] bag_size;
    private static long answer;

    public static void main(String[] args) {
	try {
	    input();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	answer = (new Solution()).solution(jewelry, bag_size);
	printAnswer();
    }

    public static void input() throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] fline = br.readLine().split(" ");
	nJewelry = Integer.parseInt(fline[0]);
	nBag = Integer.parseInt(fline[1]);
	Main.jewelry = new int[nJewelry][2];
	Main.bag_size = new int[nBag];
	String[] line;
	for (int i = 0; i < nJewelry; i++) {
	    line = br.readLine().split(" ");
	    jewelry[i][0] = Integer.parseInt(line[0]);
	    jewelry[i][1] = Integer.parseInt(line[1]);
	}
	for (int i = 0; i < nBag; i++) {
	    bag_size[i] = Integer.parseInt(br.readLine());
	}
    }

    public static void printAnswer() {
	System.out.print(answer);
    }
}
