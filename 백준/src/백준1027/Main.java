/**
 * SOLVED : Solved
 * DATE : 2022.05.23
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1027 (고층 건물)
 * ALGORITMN : Math, Bruteforcing, Geometric
 * NOTE : 입력값 N이 50이하로 상당히 작아 완전탐색, 브루트포스를 이용할 수 있다
 * 	    기하학, 수학 문제이므로 상황을 이해하고 있는 그대로 구현하면 어렵지않다
 */

package 백준1027;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {
    public int solution(int[] arr) {
	int n = arr.length;
	if (n <= 3)
	    return n - 1;

	int max = 0; // 리턴값
	int h; // i번째 빌딩의 높이
	for (int i = 0; i < n; i++) {
	    h = arr[i];
	    // i번째 빌딩의 왼쪽으로 진행, 왼쪽으로 보이는 빌딩 수 카운트
	    int left = 0;
	    if (i > 0) {
		left = 1;
		if (i > 1) {
		    double minSlope = h - arr[i - 1];
		    double slope;
		    for (int j = i - 2; j >= 0; j--) {
			slope = (double) (h - arr[j]) / (double) (i - j);
			if (slope < minSlope) {
			    ++left;
			    minSlope = slope;
			}
		    }
		}
	    }
	    // i번째 빌딩의 오른쪽으로 진행, 오른쪽으로 보이는 빌딩 수 카운트
	    int right = 0;
	    if (i < n - 1) {
		right = 1;
		if (i < n - 2) {
		    double maxSlope = arr[i + 1] - h;
		    double slope;
		    for (int j = i + 2; j <= n - 1; j++) {
			slope = (double) (h - arr[j]) / (double) (i - j);
			if (slope > maxSlope) {
			    ++right;
			    maxSlope = slope;
			}
		    }
		}
	    }
	    max = Math.max(left + right, max);
	}
	return max;
    }
}

public class Main {
    private static int N;
    private static int[] height;

    public static void main(String[] args) throws Exception {
	input();
	System.out.print((new Solution()).solution(height));
    }

    private static void input() throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	N = Integer.parseInt(br.readLine());
	height = new int[N];
	height = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
