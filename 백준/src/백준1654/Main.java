/**
 * SOLVED : False
 * DATE : 2022.05.15
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1654 (랜선 자르기)
 * ALGORITMN : Binary search, Parametric search
 * NOTE : 
 */

package 백준1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    
    private int max(int[] arr) {
	int max = arr[0];
	for(int i : arr) {
	    if(i > max)
		max = i;
	}
	return max;
    }
    
    private int obtainedCableNumber(int[] cable, int pieceLen) {
	int obtainedNum = 0;
	for(int cableLen : cable) {
	    obtainedNum += Integer.divideUnsigned(cableLen, pieceLen);
	}
	
	return obtainedNum;
    }
    
    protected int solution(int[] cable, int required) {
	int low = 1;
	int high = max(cable);
	int mid, obtainedCable;
	while(high - low > 1) {
	    mid = (low + high) / 2;
	    obtainedCable = obtainedCableNumber(cable, mid);
	    if(obtainedCable >= required)
		low = mid;
	    else
		high = mid - 1;
	}
	
	if(obtainedCableNumber(cable, high) > required)
	    return high;
	return low;
	
    }
}
public class Main {
    
    private static int nCable;
    private static int nCableNeeded;
    private static int[] cableLength;
    public static void main(String[] args) {
	try {
	    input();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	int answer = (new Solution()).solution(cableLength, nCableNeeded);
	printAnswer(answer);
    }
    
    private static void input() throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] fline = br.readLine().split(" ");
	nCable = Integer.parseInt(fline[0]);
	nCableNeeded = Integer.parseInt(fline[1]);
	cableLength = new int[nCable];
	for(int i = 0; i < nCable; i++) {
	    cableLength[i] = Integer.parseInt(br.readLine());
	}
    }
    
    private static void printAnswer(int answer) {
	System.out.print(answer);
    }

}
