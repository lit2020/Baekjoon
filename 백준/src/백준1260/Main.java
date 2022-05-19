/**
 * SOLVED : Solved
 * DATE : 2022.05.19
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1260 (DFS와 BFS)
 * ALGORITMN : Graph traversal, Data structure, Priority queue, Stack, Queue
 * 
 * NOTE : 인접노드가 여러개인 경우 넘버가 작은 노드부터 탐색해야 하므로 인접노드를 우선순위 큐에 저장
 */


package 백준1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

class Solution {

    private Integer[] DFS(ArrayList<PriorityQueue<Integer>> pqs, int start) {
	ArrayList<Integer> answer = new ArrayList<Integer>();
	boolean[] visited = new boolean[pqs.size()];
	Stack<Integer> stack = new Stack<Integer>();
	stack.push(start);

	int v;
	PriorityQueue<Integer> neighbors;
	int neighbor;
	while (!stack.isEmpty()) {
	    v = stack.pop();
	    if (visited[v]) {
		continue;
	    }
	    answer.add(v); // v를 방문
	    visited[v] = true;

	    neighbors = pqs.get(v); // v의 인접노드 탐색
	    while (!neighbors.isEmpty()) {
		neighbor = neighbors.poll();
		if (!visited[neighbor]) { // 방문하지않은 인접노드 큐에 추가
		    stack.push(neighbor);
		}
	    }
	}

	Integer[] ans = new Integer[answer.size()];
	for (int i = 0; i < answer.size(); i++) {
	    ans[i] = answer.get(i);
	}

	return ans;
    }

    private Integer[] BFS(ArrayList<PriorityQueue<Integer>> pqs, int start) {
	ArrayList<Integer> answer = new ArrayList<Integer>();
	boolean[] visited = new boolean[pqs.size()];
	ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
	queue.add(start);

	int v;
	PriorityQueue<Integer> neighbors;
	int neighbor;
	while (!queue.isEmpty()) {
	    v = queue.poll();
	    if (visited[v]) {
		continue;
	    }
	    answer.add(v); // v를 방문
	    visited[v] = true;

	    neighbors = pqs.get(v); // v의 인접노드 탐색
	    while (!neighbors.isEmpty()) {
		neighbor = neighbors.poll();
		if (!visited[neighbor]) { // 방문하지않은 인접노드 큐에 추가
		    queue.add(neighbor);
		}
	    }
	}

	Integer[] ans = new Integer[answer.size()];
	for (int i = 0; i < answer.size(); i++) {
	    ans[i] = answer.get(i);
	}

	return ans;
    }

    protected Integer[][] solution(int nVertex, Edge[] edges, int start) {
	ArrayList<PriorityQueue<Integer>> dfsQ = new ArrayList<PriorityQueue<Integer>>(nVertex + 1);
	ArrayList<PriorityQueue<Integer>> bfsQ = new ArrayList<PriorityQueue<Integer>>(nVertex + 1);
	for (int i = 0; i <= nVertex; i++) {
	    dfsQ.add(new PriorityQueue<Integer>(Collections.reverseOrder()));
	    bfsQ.add(new PriorityQueue<Integer>());
	}

	// Initialize Priority queues for DFS and BFS
	for (Edge e : edges) {
	    dfsQ.get(e.tail).add(e.head);
	    dfsQ.get(e.head).add(e.tail);
	    bfsQ.get(e.tail).add(e.head);
	    bfsQ.get(e.head).add(e.tail);
	}

	Integer[] dfsResult = this.DFS(dfsQ, start); // DFS
	Integer[] bfsResult = this.BFS(bfsQ, start); // BFS
	Integer[][] answer = new Integer[2][];
	answer[0] = dfsResult;
	answer[1] = bfsResult;

	return answer;
    }
}

public class Main {

    private static int nVertex;
    private static int nEdge;
    private static int startVertex;
    private static Edge[] edge;
    private static Integer[][] answer;

    public static void main(String[] args) throws IOException {
	input();
	answer = (new Solution()).solution(nVertex, edge, startVertex);
	printAnswer(answer);
    }

    private static void input() throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] fline = br.readLine().split(" ");
	nVertex = Integer.parseInt(fline[0]);
	nEdge = Integer.parseInt(fline[1]);
	startVertex = Integer.parseInt(fline[2]);
	edge = new Edge[nEdge];
	String[] e;
	for (int i = 0; i < nEdge; i++) {
	    e = br.readLine().split(" ");
	    edge[i] = new Edge(Integer.parseInt(e[0]), Integer.parseInt(e[1]));
	}
    }

    private static void printAnswer(Integer[][] answer) {
	for (int c = 0; c < answer.length; c++) {
	    for (int i : answer[c]) {
		System.out.print(i + " ");
	    }
	    System.out.println();
	}
    }
}

class Edge {
    int tail;
    int head;

    protected Edge(int tail, int head) {
	this.tail = tail;
	this.head = head;
    }
}
