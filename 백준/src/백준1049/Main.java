/**
 * SOLVED : Solved
 * DATE : 2022.05.18
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1049 (기타줄)
 * ALGORITMN : Greedy, Math
 * NOTE : 최소비용을 만들기위해서 가장 싼 package가격과 가장 싼 낱개 가격만 이용하면 됨
 */

package 백준1049;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    private static int PACKAGE_SIZE = 6;
    private int answer = 0;
    
    protected int choosePackageOrPiece(int required, int packagePrice, int piecePrice) {
	if (required <= 0) {
	    return answer;
	}
	if (required >= PACKAGE_SIZE) {
	    answer += packagePrice;
	    required -= 6;
	    return choosePackageOrPiece(required, packagePrice, piecePrice);
	}
	else {
	    answer += Math.min(packagePrice, piecePrice * required);
	    return answer;
	}
    }

    protected int solution(int required, int[] packagePrice, int[] piecePrice) {
	int minPackagePrice, minPiecePrice;
	minPackagePrice = packagePrice[0];
	for (int pp : packagePrice) {
	    if (pp < minPackagePrice) {
		minPackagePrice = pp;
	    }
	}

	minPiecePrice = piecePrice[0];
	for (int pp : piecePrice) {
	    if (pp < minPiecePrice) {
		minPiecePrice = pp;
	    }
	}
	if (minPackagePrice >= minPiecePrice * PACKAGE_SIZE) {
	    return minPiecePrice * required;
	}
	return this.choosePackageOrPiece(required, minPackagePrice, minPiecePrice);
    }
}

public class Main {
    private static int nRequiredString;
    private static int nBrand;
    private static int[] packagePrice;
    private static int[] piecePrice;
    private static int answer;
    public static void main(String[] args) {
	try {
	    input();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	answer = (new Solution()).solution(nRequiredString, packagePrice, piecePrice);
	printAnswer();
    }
    
    private static void input() throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] fline = br.readLine().split(" ");
	nRequiredString = Integer.parseInt(fline[0]);
	nBrand = Integer.parseInt(fline[1]);
	packagePrice = new int[nBrand];
	piecePrice = new int[nBrand];
	String[] line;
	for(int i = 0; i < nBrand; i++) {
	    line = br.readLine().split(" ");
	    packagePrice[i] = Integer.parseInt(line[0]);
	    piecePrice[i] = Integer.parseInt(line[1]);
	}
    }
    
    private static void printAnswer() {
	System.out.print(answer);
    }

}
