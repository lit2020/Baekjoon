/**
 * SOLVED : True
 * DATE : 2022.05.15
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/2805 (나무 자르기)
 * ALGORITMN : Binary search, Parametric search
 * NOTE :
 */

package 백준2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {
    private int max(int[] arr) {
	int max = arr[0];
	for (int i = 1; i < arr.length; i++) {
	    if (max < arr[i])
		max = arr[i];
	}
	return max;
    }
    
    // O(nlgn)
    protected int solution(int[] tree, int target) {
	int low = 0;
	int high = max(tree); // : O(n)
	int mid;
	long len;
	// Parametric search : O(lgn)
	while (high - low > 1) {
	    mid = (low + high) / 2;
	    // mid에서 잘랐을 때 얻는 나무의 총 길이 : O(n)
	    len = 0;
	    for (int i = 0; i < tree.length; i++) {
		len += (long)Math.max((tree[i] - mid), 0);
		if(len > (long)target) break;
	    }
	    if (len > target)
		low = mid;
	    if (len < target)
		high = mid - 1;
	    if (len == target)
		return mid;
	}
	
	long upper = 0; // high에서 잘랐을 때의 얻을수 있는 나무의 총길이
	for (int i = 0; i < tree.length; i++) {
	    upper += Math.max((tree[i] - high), 0);
	    if(upper >= (long)target) break;
	}
	if(upper >= (long)target) return high;
	return low;
    }

}

public class Main {

    private static int nTree;

    private static int target;

    private static int[] tree;

    public static void main(String[] args) {
	try {
	    input();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	int answer = (new Solution()).solution(tree, target);
	System.out.print(answer);
    }

    private static void input() throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] fline = br.readLine().split(" ");
	nTree = Integer.parseInt(fline[0]);
	target = Integer.parseInt(fline[1]);
	tree = new int[nTree];
	String[] treeHeight = br.readLine().split(" ");
	tree = Arrays.stream(treeHeight).mapToInt(Integer::parseInt).toArray();
    }
}
