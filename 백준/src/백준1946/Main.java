/*
 * 05 / 05 / 2022   ki-woong Kim
 */

package น้มุ1946;

import java.util.ArrayList;
import java.util.Scanner;

class Solution {

    private int T;

    private int num_of_candidate;

    private int[] ranking;

    private ArrayList<Integer> answer;

    private Scanner scanner = new Scanner(System.in);

    private void getTestNumber() {

	this.T = Integer.parseInt(scanner.nextLine());
	this.answer = new ArrayList<Integer>(T);
    }

    private void input() {
	this.num_of_candidate = Integer.parseInt(scanner.nextLine());

	this.ranking = new int[this.num_of_candidate];

	String[] in;
	for (int i = 0; i < this.num_of_candidate; i++) {
	    in = scanner.nextLine().split(" ");
	    this.ranking[Integer.parseInt(in[0]) - 1] = Integer.parseInt(in[1]) - 1;
	}

    }

    private void solution() {
	int highest_rank = this.ranking[0];
	int successful_cand = 1;

	for (int i = 1; i < this.num_of_candidate; i++) {
	    if (this.ranking[i] < highest_rank) {
		highest_rank = this.ranking[i];
		successful_cand++;
	    }
	}

	this.answer.add(successful_cand);

    }

    private void printAnswer() {
	for (int i = 0; i < this.T; i++) {
	    System.out.println(this.answer.get(i));
	}
    }

    protected void solve() {
	getTestNumber();
	for (int t = 0; t < T; t++) {
	    input();
	    solution();
	}
	printAnswer();
    }

}

public class Main {

    public static void main(String[] args) {
	(new Solution()).solve();

    }

}
