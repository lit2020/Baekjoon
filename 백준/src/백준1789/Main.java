/**
 * 05/05/2022 ki-woong Kim
 */

package 백준1789;

import java.util.Scanner;

class Solution {
  
  protected final static double MAX = 4294967295L;
  private double S;
  
  private int answer;
  
  private void input() {
    Scanner scanner = new Scanner(System.in);
    S = Double.valueOf(scanner.nextLine());
    scanner.close();
  }
  
  private int solution() {
    /*
     * we have to find the Maximum integer value n that
     * satisfy the inequality condition n(n+1) / 2 < S (n1, the answer)
     * this value is AT MOST less than a value that satisfy
     * condition n*n = 2S (n2)
     * Therefore, we can search the answer from the value that
     * make condition above True
     * 
     * Because Maimum value of @S user entered is 4294967295,
     * n2 can't be grater than 92681. So @int type is enough to
     * represent n2
     * 
     * but when we test if the number n2 could be selected as a answer,
     * n*(n+1) can be greater than Maximum Integer Value, so type casting
     * to #double is essential.
     * 
     */
    int n = (int)Math.sqrt(2*S);
    while(true) {
      double tmp = ( (double)n * (double)(n+1) ) / 2;
      if(tmp > S)
        n -= 1;
      else
        break;
    }
      
    answer = n;
    return answer;
  }
  
  public int solution(double S) {
    this.S = S;
    return solution();
  }

  private void printAnswer() {
    System.out.print(answer);
  }
  
  protected void solve() {
    input();
    solution();
    printAnswer();
  }
}


public class Main {

  public static void main(String[] args) {
    Solution s = new Solution();
    s.solve();
  }
}
