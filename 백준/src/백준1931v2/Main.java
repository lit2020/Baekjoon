/**
 * SOLVED : True
 * DATE : 2022.05.11
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1931 (ȸ�ǽ� ����)
 * ALGORITMN : Greedy, Sorting
 * NOTE : compatible�� job���� �ִ밳���� ���� ���� 
 * 	  greed template�μ� endtime�� ����Ͽ� endtime�� ���� ������ �����Ѵ�
 * 	   �������� ȸ���� ���۽ð��� ����ð��� ���� �� �����Ƿ� endtime�� ���� ��쿡��
 * 	   ���۽ð��� ���� ������ �����ؾ��Ѵ�
 */

package 백준1931v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Conference implements Comparable<Conference> {
    int start;
    int end;

    protected Conference(int startTime, int endTime) {
	start = startTime;
	end = endTime;
    }

    /*
     * conference A that start 2 and end 2 
     * conference B that start 1 and end 2 
     * if A is in front of B on sorted array, 
     * my solution select conference A first, and reject B 
     * because B's start time precedes A's end time 
     * This makes the answer to be 1. However, the correct answer is 2 
     * in case B is in front of A on sorted array In that case, 
     * solution function select B, and A is also selected.
     */
    @Override
    public int compareTo(Conference o) {
	if (this.end != o.end)
	    return this.end - o.end;
	
	return this.start - o.start;
    }
}

class Solution {

    protected int solution(Conference[] confs) {

	int possibleCount;

	// sort conference by end time by ascending order
	Arrays.sort(confs);

	// select a conference that end most early
	int endOflastConf;
	int i;
	for (i = 0; i < confs.length; i++) {
	    if (confs[i].end >= confs[i].start)
		break;
	}

	// start time > end time for all conference
	if (i == confs.length)
	    return 0;
	else
	    endOflastConf = confs[i].end;

	possibleCount = 1;
	for (int j = i + 1; j < confs.length; j++) {
	    Conference conf = confs[j];
	    if (conf.start >= endOflastConf) {
		if (conf.start <= conf.end) {
		    ++possibleCount;
		    endOflastConf = conf.end;
		}
	    }
	}

	return possibleCount;
    }
}

public class Main {

    private static Conference[] confs;

    private static void input() throws NumberFormatException, IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int N = Integer.parseInt(br.readLine());
	confs = new Conference[N];
	for (int i = 0; i < N; i++) {
	    String[] line = br.readLine().split(" ");
	    int start = Integer.parseInt(line[0]);
	    int end = Integer.parseInt(line[1]);
	    confs[i] = new Conference(start, end);
	}
    }

    private static void printAnswer(int answer) {
	System.out.print(answer);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {

	// ����� �Է�
	input();
	int answer = (new Solution()).solution(confs);
	// Debug ���� ���� Ȯ��
	for (int i = 0; i < confs.length; i++) {
	    System.out.print(confs[i].start + " ");
	    System.out.println(confs[i].end);
	}

	// ���� ���
	printAnswer(answer);
    }
}
