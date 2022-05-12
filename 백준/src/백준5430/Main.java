/**
 * DATE : 2022.05.09 
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/5430 (AC)
 * 
 * NOTE :
 */
package 백준5430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {
    int nTestCase;

    String[] command;

    int[][] arrays;

    String[] answer;

    
    private void input() throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	this.nTestCase = Integer.parseInt(br.readLine());
	this.command = new String[this.nTestCase];
	this.arrays = new int[this.nTestCase][];
	this.answer = new String[this.nTestCase];
	String line = "";
	String[] splitLine;
	for (int i = 0; i < this.nTestCase; i++) {
	    this.command[i] = br.readLine();
	    line = br.readLine(); // "[1,2,3,4,5]"
	    line = line.substring(1, line.length() - 1); // "1,2,3,4,5"
	    if (line.equals("")) { // entered array is empty
		this.arrays[i] = new int[0];
		continue;
	    }
	    splitLine = line.split(","); // {"1", "2", "3", "4", "5"}
	    this.arrays[i] = // {1, 2, 3, 4, 5}
		    Arrays.stream(splitLine).mapToInt(Integer::parseInt).toArray();
	}
    }

    private void solution() {
	int[] arr;
	String com;
	int firstIndex, lastIndex;
	boolean isReversed;
	boolean checkError;
	for (int i = 0; i < this.arrays.length; i++) {
	    arr = this.arrays[i];
	    com = this.command[i];
	    firstIndex = 0;
	    lastIndex = arr.length - 1;
	    isReversed = false;
	    checkError = false;
	    for (int c = 0; c < com.length(); c++) {
		if (com.charAt(c) == 'D') { // Delete first element of array
		    if (firstIndex > lastIndex) { // the array is empty
			checkError = true;
			break;
		    } else { // the array is not empty
			if (!isReversed)
			    ++firstIndex;
			else
			    --lastIndex;
		    }
		} else if (com.charAt(c) == 'R') // Reverse array
		    isReversed = !isReversed;
	    }

	    int[] subarr = null;
	    if (checkError)
		this.answer[i] = "error";
	    else if (firstIndex > lastIndex) {
		this.answer[i] = "[]";
	    } else {
		subarr = Arrays.copyOfRange(arr, firstIndex, lastIndex + 1);
		if (isReversed) {
		    for (int k = 0; k < subarr.length / 2; k++) {
			int temp = subarr[k];
			subarr[k] = subarr[subarr.length - k - 1];
			subarr[subarr.length - k - 1] = temp;
		    }
		}
		this.answer[i] = Arrays.toString(subarr).replace(" ", "");
	    }

	}
    }

    private void printAnswer() {
	for (String ans : this.answer)
	    System.out.println(ans);
    }

    protected void solve() {
	try {
	    this.input();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	this.solution();
	this.printAnswer();
    }
    
}


public class Main {
    
    public static void main(String[] args) {
	(new Solution()).solve();
    }

}
