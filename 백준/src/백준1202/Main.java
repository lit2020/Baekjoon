/**
 * SOLVED : Time Over
 * DATE : 2022.05.13
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1202
 * ALGORITMN : Greedy, Sorting
 * NOTE :
 */

package 백준1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

class Solution {
    protected ArrayList<String> dbg_answer;

    /**
     * 
     * @param sorted Bag sorted by ascending order
     * @param target Weight of jewelry that would be pushed in a bag.
     * @return minimal bag's index on @sorted that can contain the jewelry -1 if the
     *         jewelry can't be pushed in any bag
     */
    protected static int binarySearch(int[] sorted, int target) {
	if (target > sorted[sorted.length - 1]) // 보석의 무게가 모든 가방의 용량보다 큼
	    return -1;

	int low = 0;
	int high = sorted.length - 1;
	int mid = 0;
	while (low < high) {
	    mid = (low + high) / 2;
	    // target보다 작지 않은 가장 작은값을 찾아야하므로 high=mid-1이 아님
	    if (sorted[mid] >= target)
		high = mid;
	    else if (sorted[mid] < target)
		low = mid + 1;
	}

	// 보석을 담을 수 있는 가방의 가장 작은(앞에있는) 인덱스
	return low;
    }

    protected static int searchUsableBag(boolean[] isUsed, int begin, int end) {
	if (begin == end) {
	    if (isUsed[begin])
		return -1;
	    return begin;
	}

	int mid = (begin + end) / 2;
	int leftSection, rightSection;
	leftSection = searchUsableBag(isUsed, begin, mid);
	if (leftSection != -1) // 왼쪽 구간에 빈가방있음
	    return leftSection;
	else {
	    rightSection = searchUsableBag(isUsed, mid + 1, end);
	    if (rightSection != -1) // 오른쪽 구간에 빈가방 있음
		return rightSection;
	    
	    return -1; // 양쪽 모두 빈가방 없음
	}
    }

    // 정답이 int의 범위를 넘어 오버플로우가 발생
    protected long solution(int[][] jew, int[] bag) {
	// 디버깅
	dbg_answer = new ArrayList<String>(jew.length);

	long answer = 0;

	// 보석정보(무게,가치)와 가방정보(용량)을 각각 정렬 : O(nlgn)
	// 보석은 가치의 내림차순, 가방은 오름차순으로 정렬 : O(klgk)
	Arrays.sort(jew, new Comparator<int[]>() {
	    @Override
	    public int compare(int[] o1, int[] o2) {
		if (o1[1] == o2[1])
		    return o1[0] - o2[0];
		return o2[1] - o1[1];
	    }
	});
	Arrays.sort(bag);

	boolean[] isUsed = new boolean[bag.length];
	int nUsableBag = bag.length;
	// 가장 가치가 큰 보석부터 선택한다 : O(n)
	for (int i = 0; i < jew.length; i++) {
	    if (nUsableBag == 0)
		break;

	    int[] jewelry = jew[i];
	    int mass = jewelry[0];
	    int value = jewelry[1];

	    // 선택한 보석을 넣을 가방을 찾는다. : O(k)
	    int minBagIdx = binarySearch(bag, mass);
	    if (minBagIdx == -1)
		continue;

	    // 보석을 넣을 수 있는 가방들 중 정렬상 가장 앞에있는 가방을 선택
	    int bagIdx = searchUsableBag(isUsed, minBagIdx, bag.length - 1);
	    if (bagIdx == -1) // 사용할 수 있는 가방이 없음
		continue; // 다음 보석으로 이동
	    dbg_answer.add("jew[" + mass + ", " + value + "] -> " + bag[bagIdx] + " bag No." + bagIdx + "  ");
	    if (minBagIdx != bagIdx) {
		String temp = dbg_answer.get(dbg_answer.size() - 1) + "<= No." + minBagIdx;
		dbg_answer.remove(dbg_answer.size() - 1);
		dbg_answer.add(temp);
	    }
	    answer += value;
	    isUsed[bagIdx] = true;
	    --nUsableBag;
	}

	return answer;
    }

}

public class Main {
    // 디버깅 변수
    private static boolean rdbg = true; // 랜덤입력에 의한 디버그
    private static int rdbg_nJewelry = 10;
    private static int rdbg_max_weight = 20;
    private static int rdbg_min_value = 10;
    private static int rdbg_max_value = 30;
    private static int rdbg_nBag = 10;
    private static int rdbg_min_capa = 2;
    private static int rdbg_max_capa = 20;

    private static int nJewelry;
    private static int nBag;
    private static int[][] jewelry;
    private static int[] bag_size;

    private static void input() throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] number = br.readLine().split(" ");
	nJewelry = Integer.parseInt(number[0]);
	nBag = Integer.parseInt(number[1]);
	jewelry = new int[nJewelry][2];
	bag_size = new int[nBag];

	String[] line = null;
	for (int i = 0; i < nJewelry; i++) {
	    line = br.readLine().split(" ");
	    jewelry[i][0] = Integer.parseInt(line[0]);
	    jewelry[i][1] = Integer.parseInt(line[1]);
	}

	for (int i = 0; i < nBag; i++) {
	    bag_size[i] = Integer.parseInt(br.readLine());
	}
    }

    private static void printAnswer(String answer) {
	System.out.print(answer);
    }

    private static void rdbg_init() {
	jewelry = new int[rdbg_nJewelry][2];
	bag_size = new int[rdbg_nBag];

	// 랜덤값 입력
	Random rand = new Random();
	for (int i = 0; i < Math.max(rdbg_nJewelry, rdbg_nBag); i++) {
	    if (i < rdbg_nJewelry) {
		jewelry[i][0] = rand.nextInt(rdbg_max_weight) + 1;
		jewelry[i][1] = rand.nextInt(rdbg_max_value - rdbg_min_value) + rdbg_min_value;
	    }
	    if (i < rdbg_nBag)
		bag_size[i] = rand.nextInt(rdbg_max_capa - rdbg_min_capa) + rdbg_min_capa;
	}

	// 보석과 가방 정렬
	Arrays.sort(jewelry, new Comparator<int[]>() {
	    @Override
	    public int compare(int[] o1, int[] o2) {
		if (o1[1] > o2[1])
		    return -1;
		if (o1[1] < o2[1])
		    return 1;
		else {
		    if (o1[0] < o2[0])
			return -1;
		    if (o1[0] > o2[0])
			return 1;
		    else
			return 0;
		}
	    }

	});
	Arrays.sort(bag_size);
    }

    private static void dbg_initialState() {
	if (!rdbg) {
	    rdbg_nJewelry = nJewelry;
	    rdbg_nBag = nBag;
	}

	Arrays.sort(jewelry, new Comparator<int[]>() {
	    @Override
	    public int compare(int[] o1, int[] o2) {
		if (o1[1] == o2[1])
		    return o1[0] - o2[0];
		return o2[1] - o1[1];
	    }
	});
	Arrays.sort(bag_size);
	// 정렬된 초기상태 출력
	System.out.println("보석목록====가방목록");
	int i = 0;
	for (i = 0; i < Math.min(rdbg_nJewelry, rdbg_nBag); i++) {
	    System.out.print(jewelry[i][0] + " " + jewelry[i][1]);
	    System.out.println("\t No." + i + " " + bag_size[i]);
	}
	if (rdbg_nJewelry > rdbg_nBag) {
	    for (int j = i; j < rdbg_nJewelry; j++)
		System.out.println(jewelry[j][0] + " " + jewelry[j][1]);
	}
	if (rdbg_nJewelry < rdbg_nBag) {
	    for (int j = i; j < rdbg_nBag; j++)
		System.out.println("\t No." + j + " " + bag_size[j]);
	}
    }

    private static void debug() {
	rdbg_init();
	dbg_initialState();
	Solution sol = new Solution();
	BigInteger answer = BigInteger.valueOf(sol.solution(jewelry, bag_size));
	System.out.println("=====Solution=====");
	for (String dbg_info : sol.dbg_answer)
	    System.out.println(dbg_info);
	System.out.println("total : " + answer.toString());
    }

    public static void main(String[] args) throws IOException {
	if (rdbg) {
	    debug();
	} else {
	    input();
	    Solution sol = new Solution();
	    BigInteger answer = BigInteger.valueOf(sol.solution(jewelry, bag_size));
	    printAnswer(answer.toString());
	}

    }

}
