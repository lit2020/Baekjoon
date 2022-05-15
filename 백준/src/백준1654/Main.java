/**
 * SOLVED : True
 * DATE : 2022.05.15
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1654 (랜선 자르기)
 * ALGORITMN : Binary search, Parametric search
 * NOTE : a, b <= 2^31-1일때 int c = a + b에서 overflow 발생 -> 임시 타입캐스팅
 * 	   주의할 반례 { 1 1 / 2147483647} : 이분탐색시 overflow발생
 */

package 백준1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {

    // : O(n)
    private int max(int[] arr) {
	int max = arr[0];
	for (int i : arr) {
	    if (i > max)
		max = i;
	}
	return max;
    }

    // : O(n)
    private int obtainedCableNumber(int[] cable, int pieceLen) {
	int obtainedNum = 0;
	for (int cableLen : cable) {
	    obtainedNum += Integer.divideUnsigned(cableLen, pieceLen);
	}

	return obtainedNum;
    }

    // : O(nlgn)
    protected int solution(int[] cable, int required) {
	int low = 1;
	int high = max(cable); // : O(n)
	int mid;
	int obtainedCable;
	
	/* low == high == mid == Integer.maxValue인 상황에서
	 * mid가 if문의 조건을 만족시키면 low = mid + 1가 되므로 overflow 발생
	 * 따라서 low > 0 조건을 추가하여 그러한 경우에 while문을 빠져 나오게 함.
	 */
	// Parametric search : O(lgn)
	while (low <= high && low > 0) {
	    // 0 <= high <= Integr.maxValue 이므로 mid를 int로 사용하면 오버플로우 발생
	    long temp = ((long)low + (long)high) / 2;
	    mid = (int) temp;
	    obtainedCable = obtainedCableNumber(cable, mid);
	    if (obtainedCable >= required)
		low = mid + 1;
	    else
		high = mid - 1;
	}
	return (int)high;

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
	for (int i = 0; i < nCable; i++) {
	    cableLength[i] = Integer.parseInt(br.readLine());
	}
    }

    private static void printAnswer(int answer) {
	System.out.print(answer);
    }

}
