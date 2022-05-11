/**
 * DATE : 2022.05.10 
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1212 (8진수 2진수)
 * ALGORITMN : String, Math
 * NOTE : 입력에 중복이 없다는 조건이 있으므로 HashSet 자료구조를 이용하면 add와 search
 * 	    연산 모두 상수시간 O(1)에 수행이 가능하다. 
 */

package 백준1212;

import java.util.Scanner;

class Solution {
    protected static String solution(String octal) {
	/*
	 * return "0" if input octal number if 0
	 */
	if(octal.equals("0"))
	    return "0";
	
	String answer = "";
	String[] octalToBinary = { "000", "001", "010", "011", "100", "101", "110", "111" };

	StringBuilder sb = new StringBuilder();
	int digit;
	for (int i = 0; i < octal.length(); i++) {
	    digit = Integer.parseInt(String.valueOf(octal.charAt(i)));
	    sb.append(octalToBinary[digit]);
	}
	answer = sb.toString();
	
	/*
	 * remove leading '0's from answer
	 */
	int i = 0;
	for(i = 0; i < answer.length(); i++) {
	    if(answer.charAt(i) != '0')
		break;
	}
	answer = answer.substring(i);
	return answer;
    }
}

public class Main {
    private static String octal;

    private static void input() {
	Scanner scanner = new Scanner(System.in);
	octal = scanner.nextLine();
	scanner.close();
    }

    private static void printAnswer(String answer) {
	System.out.print(answer);
    }

    public static void main(String[] args) {
	input();
	String answer = Solution.solution(octal);
	printAnswer(answer);
    }

}
