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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

class Solution {
    protected ArrayList<String> dbg_answer;
    
    protected static int binarySearch(int[] sorted, int target) {
	if(target > sorted[sorted.length-1]) return -1;
	if(target < sorted[0]) return 0;
	int low = 0;
	int high = sorted.length - 1;
	int mid = 0;
	while(low < high) {
	    mid = (low + high) / 2;
	    // target보다 작지 않은 가장 작은값을 찾아야하므로 high=mid-1이 아님
	    if(sorted[mid] > target)
		high = mid;
	    else if(sorted[mid] < target)
		low = mid + 1;
	    else 
		break;
	}
	if(low == high)
	    return low;
	
	int startOfTarget = mid;
	while(low < startOfTarget) {
	    int mmid = (low + startOfTarget) / 2;
	    if(sorted[mmid] < target)
		low = mmid + 1;
	    else if(sorted[mmid] == target)
		startOfTarget = mmid;
	}
	return startOfTarget;
    }
    
    protected int solution(int[][] jew, int[] bag) {
	// 디버깅
	dbg_answer = new ArrayList<String>(jew.length);
	
	int answer = 0;
	
	// 보석정보(무게,가치)와 가방정보(중량)을 각각 정렬
	// 보석은 가치의 내림차순, 가방은 오름차순으로 정렬
	Arrays.sort(jew, new Comparator<int[]>() {
	    @Override
	    public int compare(int[] o1, int[] o2) {
		if(o1[1] > o2[1]) return -1;
		if(o1[1] < o2[1]) return 1;
		else {
		    if(o1[0] < o2[0]) return -1;
		    if(o1[0] > o2[0]) return 1;
		    else return 0;
		}
	    }
	    
	});
	Arrays.sort(bag);
	
	// 가장 가치가 큰 보석부터 선택하한다
	boolean[] isUsed = new boolean[bag.length];
	int nUsableBag = bag.length;
	for(int i = 0; i < jew.length; i++) {
	    if(nUsableBag == 0) // 모든 가방을 사용함
		break;
	    
	    int[] jewelry = jew[i];
	    int mass = jewelry[0];
	    int value = jewelry[1];
	    
	    // 선택한 보석을 넣을 가방을 찾는다.
	    // 크기가 작은 가방부터 순서대로 탐색하여, 보석을 넣을수 있는 첫번째 가방에 넣는다.
	    int minBagIdx = binarySearch(bag, mass);
	    if (minBagIdx == -1) // 보석의 무게가 모든 가방의 용량보다 큼 -> 불가능
		continue;
	    for(int j = minBagIdx; j < bag.length; j++) {
		// 넣은 보석의 가치를 answer에 더하고
		// 사용한 가방은 사용한 것으로 체크한다 (가방에는 최대 1개의 보석만)
		if(!isUsed[j]) {
		    dbg_answer.add("jew[" + mass + ", " + value + "] -> "
		    			+ bag[j] + " bag No." + j + "  ");
		    if( minBagIdx != j) {
			String temp = dbg_answer.get(dbg_answer.size() - 1) + "<= No." + minBagIdx;
			dbg_answer.remove(dbg_answer.size());
			dbg_answer.add(temp);
		    }
		    answer += value;
		    isUsed[j] = true;
		    --nUsableBag;
		    break;
		}
	    }
	}	
	return answer;
    }
}


public class Main {
    // 디버깅 변수
    private static boolean dbg = true; // 디버그모드
    private static int dbg_nJewelry = 10;
    private static int dbg_max_weight = 20;
    private static int dbg_max_value = 20;
    private static int dbg_nBag = 5;
    private static int dbg_max_capa = 10;

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

    private static void printAnswer(int answer) {
	System.out.print(answer);
    }
    
    private static void dbg_init() {
	jewelry = new int[dbg_nJewelry][2];
	bag_size = new int[dbg_nBag];
	
	// 랜덤값 입력
	Random rand = new Random();
	for(int i = 0; i < Math.max(dbg_nJewelry, dbg_nBag); i++) {
	    if(i < dbg_nJewelry) {
		jewelry[i][0] = rand.nextInt(dbg_max_weight);
	    	jewelry[i][1] = rand.nextInt(dbg_max_value);
	    }
	    if(i < dbg_nBag)
		bag_size[i] = rand.nextInt(dbg_max_capa);
	}
	
	// 보석과 가방 정렬
	Arrays.sort(jewelry, new Comparator<int[]>() {
	    @Override
	    public int compare(int[] o1, int[] o2) {
		if(o1[1] > o2[1]) return -1;
		if(o1[1] < o2[1]) return 1;
		else {
		    if(o1[0] < o2[0]) return -1;
		    if(o1[0] > o2[0]) return 1;
		    else return 0;
		}
	    }
	    
	});
	Arrays.sort(bag_size);
	
	// 정렬된 초기상태 출력
	System.out.println("보석목록====가방목록");
	int i = 0;
	for(i = 0; i < Math.min(dbg_nJewelry, dbg_nBag); i++) {
		System.out.print(jewelry[i][0] + " " + jewelry[i][1]);
		System.out.println("\t No."+ i + " " +bag_size[i]);
	}
	if(dbg_nJewelry > dbg_nBag) {
	    for(int j = i; j < dbg_nJewelry; j++)
		System.out.println(jewelry[j][0] + " " + jewelry[j][1]);
	}
	if(dbg_nJewelry < dbg_nBag) {
	    for(int j = i; j < dbg_nBag; j++)
		System.out.println("\t No."+ j + " " +bag_size[j]);
	}

    }
    
    	private static void debug() {
	dbg_init();
	Solution sol = new Solution();
	int answer = sol.solution(jewelry, bag_size);
	System.out.println("=====Solution=====");
	for(String dbg_info : sol.dbg_answer)
	    System.out.println(dbg_info);
	System.out.println("total : " + answer);
    }
    
    
    public static void main(String[] args) throws IOException {
	if(dbg) {
	    debug();
	}
	else {
	    input();
	    int answer = (new Solution()).solution(jewelry, bag_size);
	    printAnswer(answer);
	}
	/*
	Random random = new Random();
	for(int i = 0; i < T; i++) {
	    jewelry[i][0] = i;
	    jewelry[i][1] = random.nextInt(10000);
	}
	QuickSort qs = new QuickSort(true);
	int[][] copied = jewelry.clone();
	qs.sort(jewelry);
	Arrays.sort(copied, new Comparator<int[]>() {
	    @Override
	    public int compare(int[] o1, int[] o2) {
		if(o1[1] > o2[1]) return -1;
		if(o1[1] < o2[1]) return 1;
		return 0;
	    }
	    
	});
	boolean correct = true;
	for(int i = 0; i < T; i++) {
	    if(jewelry[i][0] != copied[i][0] || jewelry[i][1] != copied[i][1])
		correct = false;
	}
	System.out.println(correct);
	*/

    }

}
