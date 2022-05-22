/**
 * SOLVED : True
 * DATE : 2022.05.21
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1013 (Contact)
 * ALGORITMN : String, Regular expression (Regex)
 * NOTE : Pattern, Matcher 클래스를 통해서 더 간단하게 구현할 수 있다 (*백준1013.Test 참조)
 */

package 백준1013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

class Solution {
    public boolean solution(String pattern) {
	String remain = pattern;

	int len = remain.length();
	if (len == 0 || len == 1 || len == 3)
	    return false;

	// pattern의 길이 >= 2
	if (remain.startsWith("01")) {
	    if (len == 2)
		return true;
	    return solution(remain.substring(2));
	} else if (remain.startsWith("100")) {
	    int ptr0 = 3;
	    int ptr1;
	    while (ptr0 < len && remain.charAt(ptr0) == '0')
		++ptr0;
	    if (ptr0 == len) // 1000...00으로 문자열이 끝남
		return false;
	    else { // 100...00111...
		ptr1 = ptr0;
		while (ptr1 < len && remain.charAt(ptr1) == '1') {
		    ++ptr1;
		    // 100..0011..11 / 100+1+
		    if (remain.substring(ptr1).startsWith("100")) {
			break;
		    }
		}
		if (ptr1 == len)
		    return true; // 100...0011...11로 문자열이 끝남
		else {
		    return solution(remain.substring(ptr1));
		}
	    }
	} else { // 01 또는 100으로 시작하지 않음
	    return false;
	}

    }
}

public class Main {
    // Random Debug
    private static boolean RANDOM_MODE = false;
    private static int RANDOM_TEST = 100;
    private static int RANDOM_MAXLEN = 200;
    private static String[] RANDOM_INPUTS = new String[RANDOM_TEST];
    private static Random rd = new Random();

    private static int nTest;
    private static String[] inputs;
    private static boolean[] answers;

    public static void main(String[] args) throws NumberFormatException, IOException {
	Solution sol = new Solution();
	if (RANDOM_MODE) {
	    for (int i = 0; i < RANDOM_TEST; i++) {
		String str = "";
		while (str.length() < RANDOM_MAXLEN) {
		    String add = "";
		    if (rd.nextInt(100) % 2 == 0) { // 100+1+
			add = "100";
			int additionalZero = rd.nextInt(10);
			for (int j = 0; j < additionalZero; j++)
			    add = add + "0";
			add = add + "1";
			int additionalOne = rd.nextInt(10);
			for (int j = 0; j < additionalOne; j++)
			    add = add + "1";
			str = str + add;
		    } else { // 01
			str = str + "01";
		    }
		}
		RANDOM_INPUTS[i] = str;
		System.out.println(RANDOM_INPUTS[i]);
		System.out.println(sol.solution(RANDOM_INPUTS[i]));
	    }
	} 
	else {
	    input();
	    for (int t = 0; t < nTest; t++) {
		answers[t] = sol.solution(inputs[t]);
	    }
	    printAnswer(answers);
	}
    }

    private static void input() throws NumberFormatException, IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	nTest = Integer.parseInt(br.readLine());
	inputs = new String[nTest];
	answers = new boolean[nTest];
	for (int i = 0; i < nTest; i++) {
	    inputs[i] = br.readLine();
	}
    }

    private static void printAnswer(boolean[] answers) {
	for (boolean ans : answers) {
	    if (ans)
		System.out.println("YES");
	    else
		System.out.println("NO");
	}
    }
}
