/**
 * SOLVED : True
 * DATE : 2022.05.11
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1931 (회의실 배정)
 * ALGORITMN : Greedy, Sorting
 * NOTE : compatible한 job들의 최대개수를 세는 문제 
 * 	  greed template로서 endtime를 사용하여 endtime이 빠른 순으로 선택한다
 * 	   문제에서 회의의 시작시간과 종료시간이 같을 수 있으므로 endtime이 같은 경우에는
 * 	   시작시간이 빠른 순으로 정렬해야한다
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
     * conference A that start 2 and end 2 conference B that start 1 and end 2 if A
     * is in front of B on sorted array, my solution select conference A first, and
     * reject B because B's start time precedes A's end time This makes the answer
     * to be 1. However, the correct answer is 2 in case B is in front of A on
     * sorted array In that case, solution function select B, and A is also
     * selected.
     */
    @Override
    public int compareTo(Conference o) {
	if (this.end != o.end)
	    return this.end - o.end;
	else
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

	// 사용자 입력
	input();
	int answer = (new Solution()).solution(confs);
	// Debug 정렬 상태 확인
	for (int i = 0; i < confs.length; i++) {
	    System.out.print(confs[i].start + " ");
	    System.out.println(confs[i].end);
	}

	// 정답 출력
	printAnswer(answer);
    }
}
