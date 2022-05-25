/**
 * SOLVED : Solved
 * DATE : 2022.05.26
 * USER : KI-WOONG KIM
 * FROM : https://www.acmicpc.net/problem/11000 (강의실 배정)
 * ALGORITHM :
 * NOTE :
 */

package 백준11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public int solution(Lecture[] lectures) {
        Arrays.sort(lectures); // 강의가 먼저 끝나는 순으로 정렬
        LinkedList<Lecture> remain = new LinkedList<>(Arrays.asList(lectures));
        int room = 0;
        while (remain.size() > 0) {
            ++room;
            Iterator<Lecture> itr = remain.iterator();
            int lastEnd = itr.next().end;
            itr.remove();
            while (itr.hasNext()) {
                Lecture lec = itr.next();
                if (lec.start >= lastEnd) {
                    lastEnd = lec.end;
                    itr.remove();
                }
            }
        }
        return room;
    }
}

public class Main {
    static int nLecture;
    static Lecture[] lectures;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println((new Solution()).solution(lectures));
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nLecture = Integer.parseInt(br.readLine());
        lectures = new Lecture[nLecture];
        for (int i = 0; i < nLecture; i++) {
            String[] line = br.readLine().split(" ");
            lectures[i] = new Lecture(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }
    }
}

class Lecture implements Comparable<Lecture> {
    int start;
    int end;

    public Lecture(int startTime, int endTime) {
        this.start = startTime;
        this.end = endTime;
    }

    @Override
    public int compareTo(Lecture o) {
        return this.end - o.end;
    }
}