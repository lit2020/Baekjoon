/**
 * DATE : 2022.05.10 
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1764 (�躸��)
 * ALGORITMN : Set, Sorting
 * NOTE : �Է¿� �ߺ��� ���ٴ� ������ �����Ƿ� HashSet �ڷᱸ���� �̿��ϸ� add�� search
 * 	    ���� ��� ����ð� O(1)�� ������ �����ϴ�. 
 */


package 백준1764;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class Solution {
    protected List<String> solution(HashSet<String> neverHeard, HashSet<String> neverSeen) {
	LinkedList<String> answer = new LinkedList<String>();
	Iterator<String> itrNeverHeard = neverHeard.iterator();
	String temp;
	while (itrNeverHeard.hasNext()) {
	    temp = itrNeverHeard.next();
	    
	    if (neverSeen.contains(temp)) {
		answer.add(temp);
	    }
	}
	
	return answer;
    }
}


public class Main {

    private static HashSet<String> neverHeard;
    private static HashSet<String> neverSeen;
    
    private static void input() {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n = 0;
	int m = 0;
	String nm = "";
	try {
	    nm = br.readLine();
	} catch (IOException e) {
	}
	n = Integer.parseInt(nm.split(" ")[0]);
	m = Integer.parseInt(nm.split(" ")[1]);

	neverHeard = new HashSet<String>(n);
	neverSeen = new HashSet<String>(m);

	for(int i = 0; i < n; i++) {
	    try {
		neverHeard.add(br.readLine());
	    } catch (IOException e) {
	    }
	}
	
	for(int i = 0; i < m; i++) {
	    try {
		neverSeen.add(br.readLine());
	    } catch (IOException e) {
	    }
	}
	
    }

    private static void printAnswer(List<String> answer) {
	System.out.println(answer.size());
	for(String s : answer)
	    System.out.println(s);
    }
    
    public static void main(String[] args) {
	input();
	List<String> answer = (new Solution()).solution(neverHeard, neverSeen);
	printAnswer(answer);
    }

}
