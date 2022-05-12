package 백준11718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {

  private String[] _input = new String[110];

  private int _size;

  private void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line = "";
    int i = 0;
    while (!(line = br.readLine()).equals(""))
      this._input[i++] = line;

    this._size = i;
  }

  
  private void printAnswer() {
    if (this._size > 0) {
      for (int i = 0; i < this._size - 1; i++) {
        System.out.println(this._input[i]);
      }
      System.out.print(this._input[this._size - 1]);
    }
  }

  protected void solve() throws IOException {
    input();
    printAnswer();
  }
}


public class Main {

  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub
    (new Solution()).solve();
  }

}
