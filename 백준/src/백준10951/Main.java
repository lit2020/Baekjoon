/**
 * SOLVED : True
 * DATE : 2022.05.26
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/10951 (A+B - 4)
 * ALGORITHM : Math, Implementation
 * NOTE :
 */

package 백준10951;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Solution {
    public int solution(int a, int b) {
        return (a + b);
    }
}

public class Main {
    static int a, b;
    static ArrayList<Integer> answers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Solution sol = new Solution();
        String s;
        String[] numbers;
        int answer;
        while (true) {
            s = br.readLine();
            if (s == null || s.length() <= 0)
                break;
            numbers = s.split(" ");
            a = Integer.parseInt(numbers[0]);
            b = Integer.parseInt(numbers[1]);
            answers.add(sol.solution(a, b));
        }
        for (int x : answers) {
            System.out.println(x);
        }
    }
}
