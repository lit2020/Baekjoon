/* 05 / 07 / 2022 */

package 백준1181;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Solution {

  private int _numOfWord;

  private ArrayList<String> _words;


  private void input() {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
      this._numOfWord = Integer.parseInt(br.readLine());
      this._words = new ArrayList<String>();
    } catch (NumberFormatException | IOException e1) {
      e1.printStackTrace();
    }
    for (int i = 0; i < this._numOfWord; i++) {
      try {
        this._words.add(br.readLine());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  
  private void solution() {

    Collections.sort(this._words, new Comparator<String>() {

      @Override
      public int compare(String o1, String o2) {
        // TODO Auto-generated method stub
        int lenDiff = o1.length() - o2.length();
        if (lenDiff < 0)
          return -1;
        else if (lenDiff > 0)
          return 1;
        else {
          for (int i = 0; i < o1.length(); i++) {
            int order = Character.compare(o1.charAt(i), o2.charAt(i));
            if (order < 0)
              return -1;
            else if (order > 0)
              return 1;
          }
          return 0;
        }
      }

    });
  }

  private void printAnswer() {
    String prevWord = "";
    for(String word : this._words) {
      if(!prevWord.equals(word)) {
        System.out.println(word);
        prevWord = word;
      }
    }
  }

  protected void solve() {
    input();
    solution();
    printAnswer();
  }

}


public class Main {

  public static void main(String[] args) {
    (new Solution()).solve();
  }

}
