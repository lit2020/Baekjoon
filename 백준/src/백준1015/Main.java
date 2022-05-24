/**
 * SOLVED : Solved
 * DATE : 2022.05.25
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1015 (수열 정렬)
 * ALGORITHM : Sorting
 * NOTE : 최소값을 n번 찾는다
 */

package 백준1015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] A) {
        int[] answer = new int[A.length];
        int minValue;
        int minIdx;
        for (int i = 0; i < A.length; i++) {
            minIdx = 0;
            minValue = A[0];
            int j;
            for (j = 0; j < A.length; j++) {
                if (A[j] < minValue) {
                    minIdx = j;
                    minValue = A[j];
                }
            }
            answer[minIdx] = i;
            A[minIdx] = Integer.MAX_VALUE;
        }
        return answer;
    }
}

public class Main {
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = new int[Integer.parseInt(br.readLine())];
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] answer = (new Solution()).solution(A);
        for (int a : answer)
            System.out.print(a + " ");
    }
}
