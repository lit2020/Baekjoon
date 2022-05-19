/**
 * SOLVED : False
 * DATE : 2022.05.19
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1012 (유기농 배추)
 * ALGORITMN : Greedy, Math
 * NOTE : 최소비용을 만들기위해서 가장 싼 package가격과 가장 싼 낱개 가격만 이용하면 됨
 */

package 백준1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Map {
    private int[][] map;
    int width;
    int height;
    int nTarget;

    protected Map(int width, int height, int nTarget) {
	this.width = width;
	this.height = height;
	this.map = new int[height][width];
	this.nTarget = nTarget;
    }

    protected int get(int x, int y) {
	if (this.isOutOfMap(x, y))
	    return -1;
	return this.map[y][x];
    }

    protected boolean set(int x, int y, int value) {
	if (this.isOutOfMap(x, y))
	    return false;
	this.map[y][x] = value;
	return true;
    }

    private boolean isOutOfMap(int x, int y) {
	return ((x >= 0) && (x < this.width)) && ((y >= 0) && y < (this.height));
    }
}

public class Main {

    private static int nTest;
    private static ArrayList<Map> maps;
    public static void main(String[] args) {
	

    }

    private static void input() throws NumberFormatException, IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	nTest = Integer.parseInt(br.readLine());
	maps = new ArrayList<Map>(nTest);
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
		map.set(x, y, 1);
	    }
	    maps.add(map);
	}
    }
}


