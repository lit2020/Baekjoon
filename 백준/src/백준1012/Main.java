/**
 * SOLVED : Solved
 * DATE : 2022.05.20
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1012 (유기농 배추)
 * ALGORITMN : Graph traversal, DFS, BFS
 * NOTE : 
 */

package 백준1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

class Map {
    private int[][] map;
    int width;
    int height;
    int nTarget;
    LinkedList<int[]> targets;
    
    protected Map(int width, int height, int nTarget) {
	this.width = width;
	this.height = height;
	this.map = new int[height][width];
	this.nTarget = nTarget;
	this.targets = new LinkedList<int[]>();
    }

    protected int getValueOf(int x, int y) {
	if (this.isOutOfMap(x, y))
	    return -1;
	return this.map[y][x];
    }

    protected boolean setValueOf(int x, int y, int value) {
	if (this.isOutOfMap(x, y))
	    return false;
	this.map[y][x] = value;
	return true;
    }
    
    private boolean isOutOfMap(int x, int y) {
	return !(((x >= 0) && (x < this.width)) && 
		((y >= 0) && y < (this.height)));
    }
    
    protected ArrayList<int[]> adjacencyOf(int x, int y) {
	ArrayList<int[]> adjacency = new ArrayList<int[]>();
	int[] above, below, left, right;
	above = this.aboveOf(x, y);
	if (above != null) adjacency.add(above);
	below = this.belowOf(x, y);
	if (below != null) adjacency.add(below);
	left = this.leftOf(x, y);
	if (left != null) adjacency.add(left);
	right = this.rightOf(x, y);
	if (right != null) adjacency.add(right);
	
	return adjacency;
    }
    
    private int[] aboveOf(int x, int y) {
	if (y == 0)
	    return null;
	else {
	    int[] above = new int[2];
	    above[0] = x; 
	    above[1] = y - 1;
	    return above;
	}
    }
    
    private int[] belowOf(int x, int y) {
	if (y == this.height)
	    return null;
	else {
	    int[] below = new int[2];
	    below[0] = x; 
	    below[1] = y + 1;
	    return below;
	}
    }
    
    private int[] leftOf(int x, int y) {
	if(x == 0)
	    return null;
	else {
	    int[] left = new int[2];
	    left[0] = x - 1; 
	    left[1] = y;
	    return left;
	}
    }
    
    private int[] rightOf(int x, int y) {
	if (x == this.width)
	    return null;
	else {
	    int[] right = new int[2];
	    right[0] = x + 1; 
	    right[1] = y;
	    return right;
	}
    }

    protected int[] nextTarget() {
	return this.targets.removeFirst();
    }
    
    protected void printMap() {
	for(int row = 0; row < this.height; row++) {
	    for(int col = 0; col < this.width; col++) {
		System.out.print(this.map[row][col]);
	    }
	    System.out.println();
	}
	System.out.println("target List");
	for(int[] index : targets) {
	    System.out.println(index[0] + " " + index[1]);
	}
    }
}


class Solution {
    protected int solution(Map map) {
	int answer = 0;
	Stack<int[]> dfs = new Stack<int[]>();
	int remainTarget = map.nTarget;
	
	while(remainTarget > 0) {
	    int[] newTarget = map.nextTarget();
	    while (map.getValueOf(newTarget[0], newTarget[1]) != 1) {
		newTarget = map.nextTarget();
	    }
	    dfs.push(newTarget);
	    ++answer;
	    while(!dfs.isEmpty()) {
		int[] coord = dfs.pop();
		int x = coord[0];
		int y = coord[1];
		if (map.getValueOf(x, y) == 1) {
		    --remainTarget; // visit
		    map.setValueOf(x, y, -1); // marking it was visited
		    ArrayList<int[]> adjacency = map.adjacencyOf(x, y);
		    for(int[] neighbor : adjacency) {
			if (neighbor == null)
			    continue;
			if (map.getValueOf(neighbor[0], neighbor[1]) == 1) {
			    dfs.push(neighbor); // push not visited node
			}
		    }
		}
	    } 
	}
	return answer;
    }
}


public class Main {

    private static int nTest;
    private static ArrayList<Map> maps;
    private static int[] answer;
    public static void main(String[] args) throws NumberFormatException, IOException {
	input();
	for(int t = 0; t < nTest; t++) {
	    answer[t] = (new Solution()).solution(maps.get(t));
	}
	printAnswer();
    }

    private static void input() throws NumberFormatException, IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	nTest = Integer.parseInt(br.readLine());
	maps = new ArrayList<Map>(nTest);
	answer = new int[nTest];
	for (int t = 0; t < nTest; t++) {
	    String[] fline = br.readLine().split(" ");
	    int M = Integer.parseInt(fline[0]);
	    int N = Integer.parseInt(fline[1]);
	    int K = Integer.parseInt(fline[2]);
	    Map map = new Map(M, N, K);

	    for (int i = 0; i < K; i++) {
		String[] line = br.readLine().split(" ");
		int x = Integer.parseInt(line[0]);
		int y = Integer.parseInt(line[1]);
		map.setValueOf(x, y, 1);
		int[] targetIdx = {x, y};
		map.targets.add(targetIdx);
	    }
	    maps.add(map);
	}
    }
    
    private static void printAnswer() {
	for(int ans : answer) {
	    System.out.println(ans);
	}
    }
}
