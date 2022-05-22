/**
 * SOLVED : False
 * DATE : 2022.05.21
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/1620 (나는야 포켓몬 마스터 이다솜)
 * ALGORITMN : Data structure, Hash, Map
 * NOTE : key=문자열, value=인덱스인 맵과 반대인 맵을 이용
 */

package 백준1620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public String[] solution(Map<Integer, String> pokedex, Map<String, Integer> pokedexReverse, String[] question) {
	String[] answer = new String[question.length];
	for (int i = 0; i < answer.length; i++) {
	    char f = question[i].charAt(0);
	    switch (f) {
	    case '0': case '1': case '2': case '3': case '4':
	    case '5': case '6': case '7': case '8':
	    case '9':
		answer[i] = pokedex.get(Integer.parseInt(question[i]));
		break;

	    default: 
		answer[i] = String.valueOf(pokedexReverse.get(question[i]));
	    }
	}

	return answer;
    }
}

public class Main {
    private static Map<Integer, String> pokedex;
    private static Map<String, Integer> pokedexReverse;
    private static String[] question;

    public static void main(String[] args) {
	try {
	    input();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	String[] answer = (new Solution()).solution(pokedex, pokedexReverse, question);
	for (String ans : answer)
	    System.out.println(ans);
    }

    private static void input() throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] fline = br.readLine().split(" ");
	int N = Integer.parseInt(fline[0]);
	int M = Integer.parseInt(fline[1]);
	pokedex = new HashMap<Integer, String>(N);
	pokedexReverse = new HashMap<String, Integer>(N);
	question = new String[M];
	for (int n = 0; n < N; n++) {
	    String poke = br.readLine();
	    pokedex.put(n + 1, poke);
	    pokedexReverse.put(poke, n + 1);
	}
	for (int m = 0; m < M; m++) {
	    question[m] = br.readLine();
	}

    }
}
