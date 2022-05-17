/**
 * SOLVED : True
 * DATE : 2022.05.17
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/10988 (펠린드롬인지 확인하기)
 * ALGORITMN : String, Implementation
 * NOTE : 
 */

package 백준10988;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    protected boolean solution(String[] input) {
	boolean isPalindrome = true;
 	int len = input.length;
	for(int i = 0; i < len / 2; i++) {
	    if(!input[i].equals(input[len - i - 1])) {
		isPalindrome = false;
		break;
	    }
	}	
	return isPalindrome;
    }
}

public class Main {

    private static String input;
    
    private static String[] inputArr;
    
    public static void main(String[] args) {
	try {
	    input();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
	boolean answer = (new Solution()).solution(inputArr);
	printAnswer(answer);

    }
    
    private static void input() throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	input = br.readLine();
	inputArr = input.split("");
    }
    
    private static void printAnswer(boolean answer) {
	if(answer) System.out.print(1);
	else System.out.print(0);
    }
}
