/**
 * DATE : 2022.05.10 
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/9935 (문자열 폭발)
 * ALGORITMN : data structure, stack, string
 * NOTE : 메모리 초과 문제 발생했음. String을 반복적으로 Concatenation할때  
 * 	  '+' 연산 대신 StringBuilder를 사용하여 메모리사용량 최소화
 */

package 백준9935;

import java.util.Scanner;
import java.util.Stack;


class Solution {

    /**
     * @param str       user input string where chain-reaction is conducted
     * @param explosion explosion string
     * @return the string remaining after all chain-explosion
     */
    protected String solution(String str, String explosion) {
	String answer = "";
	char[] string = str.toCharArray();
	Stack<String> stack = new Stack<String>();

	if (explosion.length() == 1) { // explosion string is just single chararter
	    char targetChar = explosion.charAt(0);
	    for(char ch : string) {
		if(ch != targetChar) {
		    stack.push(String.valueOf(ch));
		}
	    }
	} else {
	    stack.push(Character.toString(string[0]));
	    for (int i = 1; i < string.length; i++) {
		String ich = Character.toString(string[i]);
		if (stack.isEmpty()) {
		    stack.push(ich);
		} else {
		    String prefix = stack.peek() + Character.toString(string[i]);
		    if (explosion.startsWith(prefix)) {
			String temp = stack.pop() + ich;
			stack.push(temp);
			if (stack.peek().equals(explosion)) {
			    stack.pop();
			}
		    } else {
			stack.push(ich);
		    }
		}
	    }
	}

	if (stack.isEmpty())
	    return "FRULA";

	Stack<String> answerStack = new Stack<String>();
	while (!stack.isEmpty()) {
	    answerStack.push(stack.pop());
	}
	
	/*
	 * StringBuilder를 사용하지 않고 + 연산을 사용하면 메모리 초과 발생 
	 */
	StringBuilder sb = new StringBuilder();
	while (!answerStack.isEmpty()) {
	    sb.append(answerStack.pop());
	}
	answer = sb.toString();
	// sb = null;  // not necessary, Garbage Collector take it over after this function ends
	return answer;
    }
}

public class Main {

    private static String input;
    private static String target;

    private static void input() {
	Scanner scanner = new Scanner(System.in);
	input = scanner.nextLine();
	target = scanner.nextLine();
	scanner.close();
    }

    private static void printAnswer(String answer) {
	System.out.print(answer);
    }

    public static void main(String[] args) {

	input();
	String answer = (new Solution()).solution(input, target);
	printAnswer(answer);
	
	/*
	 * Check memory usage
	 */
	//Runtime.getRuntime().gc();
	//long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	//System.out.print(usedMemory + " bytes");
    }
}
