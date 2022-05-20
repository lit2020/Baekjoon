/**
 * SOLVED : Solved
 * DATE : 2022.05.21
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/12100 (2048 (easy))
 * ALGORITMN : Backtracking, Bruteforcing, Graph traversal, Implementation, Simulation
 * NOTE : 백트래킹, 브루트포스로 모든 경로를 탐색하여 최대값을 찾는다.
 */

package 백준12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

class Board {
    int size;
    int[][] values;
    int maxValue = -1;
    int swipeCnt = 0;
    
    protected Board(int size) {
	this.size = size;
	this.values = new int[size][size];
    }

    private Board(int size, int[][] values, int max, int swipeCnt) {
	this.size = size;
	this.values = values;
	this.maxValue = max;
	this.swipeCnt = swipeCnt;
    }
    
    protected ArrayList<Board> createchildren() {
	ArrayList<Board> children = new ArrayList<Board>();
	children.add(this.swipeRight());
	children.add(this.swipeLeft());
	children.add(this.swipeUp());
	children.add(this.swipeDown());
	return children;
    }
    
    private Board swipeRight() {

	Board b = new Board(this.size, null, this.maxValue, this.swipeCnt + 1);
	int[][] deepCopy = new int[b.size][b.size];
	for (int i = 0; i < b.size; ++i) {
	    deepCopy[i] = Arrays.copyOf(this.values[i], b.size);
	}
	b.values = deepCopy;
	
	for (int row = 0; row < b.size; ++row) {
	    boolean[] mergedBlock = new boolean[b.size];
	    int i = b.size - 1;
	    for (int col = b.size - 1; col >= 0; --col) {
		if (b.values[row][col] != 0) {  // 0이 아니면 오른쪽으로 스윕
		    // 같은 수 두개 만나면 합쳐짐
		    if(i < b.size - 1 && (b.values[row][i+1] == b.values[row][col])
			    		&& !mergedBlock[i+1]) { // 합쳐져서 만들어진 블럭은 다시 합칠수 없음
			b.values[row][i+1] += b.values[row][col];
			b.values[row][col] = 0;
			// 머지로 만들어진 블럭값이 스윕 이전의 max보다 크면 업데이트
			if (b.values[row][i+1] > b.maxValue) {
			    b.maxValue = b.values[row][i+1]; 
			}
			mergedBlock[i+1] = true;
			
		    } else {
			b.values[row][i] = b.values[row][col];
			if (i != col) {
			    b.values[row][col] = 0;
			}
			--i;
		    }
		}
	    }
	}
	return b;
    }
    
    private Board swipeLeft() {
	Board b = new Board(this.size, this.values, this.maxValue, this.swipeCnt);
	int[][] temp = new int[b.size][b.size];
	for (int row = 0; row < b.size; ++row) {
	    for (int col = 0; col < b.size; ++col) {
		temp[row][col] = b.values[row][b.size - 1 - col];
	    }
	}
	b.values = temp;
	
	b = b.swipeRight();
	
	int[][] temp2 = new int[b.size][b.size];
	for (int row = 0; row < size; ++row) {
	    for (int col = 0; col < size; ++col) {
		temp2[row][col] = b.values[row][b.size - 1 - col];
	    }
	}
	b.values = temp2;
	return b;
    }
    
    private Board swipeUp() {
	Board b = new Board(this.size, this.values, this.maxValue, this.swipeCnt);
	int[][] temp = new int[b.size][b.size];
	for (int row = 0; row < b.size; ++row) {
	    for (int col = 0; col < b.size; ++col) {
		temp[col][b.size - 1 - row] = b.values[row][col];
	    }
	}
	b.values = temp;
	b = b.swipeRight();
	
	int[][] temp2 = new int[b.size][b.size];
	for (int row = 0; row < b.size; ++row) {
	    for (int col = 0; col < b.size; ++col) {
		temp2[b.size - 1 - col][row] = b.values[row][col];
	    }
	}
	b.values = temp2;
	return b;
    }
    
    private Board swipeDown() {
	Board b = new Board(this.size, this.values, this.maxValue, this.swipeCnt);
	int[][] temp = new int[b.size][b.size];
	for (int row = 0; row < b.size; ++row) {
	    for (int col = 0; col < b.size; ++col) {
		temp[b.size - 1 - col][row] = b.values[row][col];
	    }
	}
	b.values = temp;
	b = b.swipeRight();
	
	int[][] temp2 = new int[b.size][b.size];
	for (int row = 0; row < b.size; ++row) {
	    for (int col = 0; col < b.size; ++col) {
		temp2[col][b.size - 1 -row] = b.values[row][col];
	    }
	}
	b.values = temp2;
	return b;
    }
   
    
    protected int findMax() {
	int max = -1;
	for (int i = 0; i < size; i++) {
	    for (int j = 0; j < size; j++) {
		if (values[i][j] > max)
		    max = values[i][j];
	    }
	}
	this.maxValue = max;
	return this.maxValue;
    }

    protected boolean equals(Board another) {
	if (this.size != another.size)
	    return false;
	if (this.maxValue != another.maxValue)
	    return false;
	for (int i = 0; i < this.size; ++i) {
	    for (int j = 0; j < this.size; ++j) {
		if (this.values[i][j] != another.values[i][j])
		    return false;
	    }
	}
	return true;
    }
    
    protected void print() {
	for (int i = 0; i < size; i++) {
	    for (int j = 0; j < size; j++) {
		System.out.print(values[i][j] + " ");
	    }
	    System.out.println();
	}
	System.out.println("MAX = " + this.maxValue);
	System.out.println("SWP = " + this.swipeCnt);
    }
}


class Solution {
    protected int solution(Board initialBoard) {
	int answer = initialBoard.maxValue;
	int maxAnswer = 32 * initialBoard.maxValue;
	Stack<Board> stack = new Stack<Board>();
	stack.push(initialBoard);
	Board b;
	while (!stack.isEmpty()) {
	    b = stack.pop();
	    if (b.maxValue > answer) {
		answer = b.maxValue;
		if (answer == maxAnswer) {
		    return answer;
		}
	    }
	    if (b.swipeCnt < 5) {
		ArrayList<Board> nextStates = b.createchildren();
		for (Board nextState : nextStates) {
		    if (!nextState.equals(b)) {
		    	stack.push(nextState);
		    }
	    	}
	    }
	}
	return answer;
    }
}


public class Main {
    private static Board board;

    public static void main(String[] args) throws IOException {
	input();
	System.out.println((new Solution()).solution(board));

    }

    private static void input() throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int size = Integer.parseInt(br.readLine());
	board = new Board(size);
	for (int i = 0; i < size; i++) {
	    String[] line = br.readLine().split(" ");
	    int[] arr = Arrays.stream(line).mapToInt(Integer::parseInt).toArray();
	    board.values[i] = arr;
	}
	board.findMax();
    }

}