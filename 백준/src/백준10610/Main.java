/*
 * 05.04.2022
 */

package 백준10610;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Solution {
  
  private String[] N_str;
  
  /* < 10^(10^5) */
  private int[] N;
  
  private int sum_of_digit;
  
  private boolean zero_exist = false;
  
  private String answer;
  
  
  private boolean isMultipleOfThree() {
    
    return (this.sum_of_digit % 3 == 0);
  }
  
  private boolean isMultipleOfTen() {
    
    return zero_exist;
  }
  
  private void input() {
    Scanner scanner = new Scanner(System.in);
    this.N_str = scanner.nextLine().split("");
    this.N = new int[N_str.length];
    scanner.close();
  }
  
  private void solution() {
    for(int i = 0; i < N_str.length; i++) {
      N[i] = Integer.parseInt(N_str[i]);
      if(N[i] != 0)
        this.sum_of_digit += N[i];     
      else
        this.zero_exist = true;
    }
    
    /* it is possible to make the multiple number of 30 by
     * suffling digits
     */
    if(this.isMultipleOfThree() && this.isMultipleOfTen()) {
      /*
       * Sort digits by descending order
       */
      Integer[] N_sorted = Arrays.stream(N).boxed().toArray(Integer[]::new);
      Arrays.sort(N_sorted, Collections.reverseOrder());

      String[] temp = new String[N.length];
      for(int i=0; i<N.length; i++) {
        temp[i] = String.valueOf(N_sorted[i]);
      }
      
      answer = String.join("", temp);
    }
    else {  // impossible case
      this.answer = "-1";
    }
  }
  
  private void printAnswer() {
    System.out.print(this.answer);
    
  }
  
  protected void solve() {
    this.input();
    this.solution();
    this.printAnswer();
  }

}


public class Main {

  public static void main(String[] args) {
    (new Solution()).solve();
  }
  
}
