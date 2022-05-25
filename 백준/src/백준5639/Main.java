/**
 * SOLVED : Solved
 * DATE : 2022.05.25
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/5639 (이진 검색 트리)
 * ALGORITHM : Tree, Graph traversal, Recursion
 * NOTE :
 */

package 백준5639;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Solution {
    protected void solution(int[] preOrder, int left, int right) {
        if (left > right)
            return;
        int root = preOrder[left];
        int idx = -1;
        for (int i = left + 1; i <= right; i++) {
            if (preOrder[i] > root) {
                idx = i;
                break;
            }
        }
        if (idx == -1)
            idx = right + 1;
        solution(preOrder, left + 1, idx - 1);
        solution(preOrder, idx, right);
        System.out.println(root);
    }
}

public class Main {
    static ArrayList<Integer> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (true) {
            s = br.readLine();
            if (s == null || s.length() <= 0)
                break;
            arr.add(Integer.parseInt(s));
        }
        int[] preOrder = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            preOrder[i] = arr.get(i);
        }
        (new Solution()).solution(preOrder, 0, preOrder.length- 1);
    }
}



















