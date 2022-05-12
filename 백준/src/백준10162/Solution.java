package 백준10162;

import java.util.Scanner;

public class Solution {
  private static int T;
  private static int[] buttons = {300, 60, 10};
  
  private static void input() {
    Scanner scanner = new Scanner(System.in);
    T = Integer.parseInt(scanner.nextLine());
    scanner.close();
  }
  
  private static int[] solution(int T) {
    int[] answer = new int[buttons.length];
    if(T % 10 > 0) {
      answer[0] = -1;
      return answer;
    }
    
    for(int i=0; i < buttons.length; i++) {
      answer[i] = Math.floorDiv(T, buttons[i]);
      T = Math.floorMod(T, buttons[i]);
    }
    
    return answer;
  }
  
  
  public static void main(String[] args) {
    input();
    int[] answer = solution(T);
    
    if(answer[0] < 0) {
      System.out.print(-1);
    }
    else {
      for(int i = 0; i < answer.length; i++) {
        System.out.print(answer[i] + " ");
      } 
    }
  }
}
