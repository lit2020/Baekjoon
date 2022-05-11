/**
 *  05/05/2022
 */

package น้มุ1715;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class Solution {
  
  private int N;
  
  private Queue<Integer> _deck_sizes;
  
  private int _answer;
  
  
  private void input() {
    Scanner scanner = new Scanner(System.in);
    this.N = Integer.parseInt(scanner.nextLine());
    this._deck_sizes = new PriorityQueue<Integer>(N);
    for(int i = 0; i < N; i++) {
      this._deck_sizes.add(Integer.parseInt(scanner.nextLine()));
    }
    scanner.close();
  }
  
  private void solution() {
    
    int count = 0;
    while(this._deck_sizes.size() > 1) {
      int size1 = this._deck_sizes.remove();
      int size2 = this._deck_sizes.remove();
      count += (size1 + size2);
      this._deck_sizes.add(size1 + size2);
    }
    this._answer = count;
  }
  
  private void printAnswer() {
    System.out.print(this._answer);
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
