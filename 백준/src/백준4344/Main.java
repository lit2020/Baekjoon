/**
 * SOLVED : Solved
 * DATE : 2022.05.26
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/4344 (평균은 넘겠지)
 * ALGORITHM : Math
 * NOTE : String.format 사용
 */

package 백준4344;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public String solution(int[] score) {
        int sumScore = 0;
        double avg;
        for (int s : score)
            sumScore += s;
        avg = (double) sumScore / score.length;
        int cnt = 0;
        for (int s : score) {
            if ((double) s > avg)
                ++cnt;
        }
        return String.format("%.3f", (double) cnt / score.length * 100) + "%";
    }
}

public class Main {
    static int nTest;
    static int[] score;
    static ArrayList<String> answers;

    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nTest = Integer.parseInt(br.readLine());
        answers = new ArrayList<>(nTest);
        String[] line;
        for (int i = 0; i < nTest; i++) {
            line = br.readLine().split(" ");
            score = Arrays.stream(line).mapToInt(Integer::parseInt).toArray();
            answers.add(sol.solution(Arrays.copyOfRange(score, 1, score.length)));
        }
        for (String ans : answers) {
            System.out.println(ans);
        }
    }
}
