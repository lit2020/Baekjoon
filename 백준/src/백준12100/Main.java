/**
 * SOLVED : Solved
 * DATE : 2022.05.21
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/12100 (2048 (easy))
 * ALGORITMN : Backtracking, Bruteforcing, Graph traversal, Implementation, Simulation
 * NOTE : 탐색할 경우의 수가 4^5로 작으므로 완전탐색을 사용할 수 있다
 *  	  DFS를 통한 백트래킹. 유망한 자식들만 스택에 삽입
 *  	  Board를 네 방향으로 swipe하는 과정의 구현이 약간 까다롭지만 2D 배열을 적절히 회전(rotate)하여
 *  	  swipeRight 함수만으로 나머지 세 함수를 구현할 수 있음 
 */

package 백준12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


class Board {
    int size; // 가로 세로 길이
    int[][] values; // 실제 값을 저장할 배열
    private int maxValue = -1; // 보드의 최댓값
    int swipeCnt = 0; // swipe한 횟수
    
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
    
    /**
     * 보드에서 최댓값을 찾아서 갱신하고 리턴한다
     * 초기보드의
     * @return 보드의 최댓값
     */
    protected int maxValue() {
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
    /**
     * 오른쪽, 왼쪽, 위쪽, 아래쪽으로 swipe하여 만들어지는 다음 상태의 보드들을 리턴함
     * @return
     */
    protected ArrayList<Board> createNextStates() {
	ArrayList<Board> nextStates = new ArrayList<Board>();
	nextStates.add(this.swipeRight());
	nextStates.add(this.swipeLeft());
	nextStates.add(this.swipeUp());
	nextStates.add(this.swipeDown());
	return nextStates;
    }
    
    /**
     * 보드를 오른쪽으로 스윕한다
     * @return 보드를 오른쪽으로 스윕하여 만들어진 새로운 보드
     */
    private Board swipeRight() {
	Board b = new Board(this.size, null, this.maxValue(), this.swipeCnt + 1);
	
	// 현재 보드의 값을 카피
	int[][] deepCopy = new int[b.size][b.size];
	for (int i = 0; i < b.size; ++i) {
	    deepCopy[i] = Arrays.copyOf(this.values[i], b.size);
	}
	b.values = deepCopy;
	
	// Swipe
	// 오른쪽으로 스윕하므로 각 row별로 처리 (서로 다른 row끼리는 상호작용 없음)
	for (int row = 0; row < b.size; ++row) {
	    // 한번의 스윕에서 하나의 블럭은 한번만 병합될 수 있음
	    // i-th column의 블럭이 이미 병합된 블럭인지 체크
	    boolean[] mergedBlock = new boolean[b.size];
	    int i = b.size - 1;
	    for (int col = b.size - 1; col >= 0; --col) {
		if (b.values[row][col] != 0) {  // 0이 아니면 오른쪽으로 스윕
		    // 같은 수 두개 만나면 합쳐짐
		    if(i < b.size - 1 && (b.values[row][i+1] == b.values[row][col])
			    		&& !mergedBlock[i+1]) {
			b.values[row][i+1] += b.values[row][col];
			b.values[row][col] = 0; // 이전에 있던 자리는 빈칸 처리
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
    
    /**
     * 각 row를 reverse - 오른쪽으로 스윕 - 각 row를 reverse
     * @return 보드를 왼쪽으로 스윕하여 만들어진 새로운 보드
     */
    private Board swipeLeft() {
	Board b = new Board(this.size, this.values, this.maxValue(), this.swipeCnt);
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
    
    /**
     * 시계방향 회전 - 오른쪽으로 스윕 - 반시계 방향으로 회전
     * @return 보드를 위쪽으로 스윕하여 만들어진 새로운 보드
     */
    private Board swipeUp() {
	Board b = new Board(this.size, this.values, this.maxValue(), this.swipeCnt);
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
    
    /**
     * 반시계방향 회전 - 오른쪽으로 스윕 - 시계 방향으로 회전
     * @return 보드를 아래쪽으로 스윕하여 만들어진 새로운 보드
     */
    private Board swipeDown() {
	Board b = new Board(this.size, this.values, this.maxValue(), this.swipeCnt);
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
  

    /**
     * another의 보드의 상태가 이 보드와 같으면 true 리턴
     * 즉 보드의 크기가 같고 모든 인덱스의 값이 같을 때에만 true 
     * @param another 비교할 보드
     * @return
     */
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
    
    /**
     * 보드의 상태를 출력
     * 각 인덱스의 값, 보드의 최댓값, 변경 횟수(swipeCnt)
     */
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
	int answer = initialBoard.maxValue();
	int maxAnswer = 32 * answer;
	Stack<Board> stack = new Stack<Board>();
	stack.push(initialBoard);
	Board b;
	while (!stack.isEmpty()) {
	    b = stack.pop();
	    int newMax = b.maxValue();
	    if (newMax > answer) {
		answer = newMax;
		if (answer == maxAnswer) {
		    return answer;
		}
	    }
	    if (b.swipeCnt < 5) {
		ArrayList<Board> nextStates = b.createNextStates();
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
    }
}
