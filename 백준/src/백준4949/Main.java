/* 05 /07 /2022 */
package น้มุ4949;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

class Solution {

  ArrayList<String> _input = new ArrayList<String>();
  Stack<Character> _bracket = new Stack<Character>();
  int numOfLine;
  boolean[] _answer;

  private void input() {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    numOfLine = 0;
    String line = "";
    try {
      while (!(line = br.readLine()).equals(".")) {
        this._input.add(line);
        numOfLine++;
      }
    } catch (Exception e) {
    }
    
    this._answer = new boolean[numOfLine];
  }

  private void solution() {

    for (int i = 0; i < this._input.size(); i++) {
      this._bracket.clear();
      this._answer[i] = true;
      String line = this._input.get(i);

      for (int j = 0; j < line.length(); j++) {
        char jch = line.charAt(j);
        if (jch == '(' || jch == '[')
          this._bracket.push(jch);

        else if (jch == ')') {
          if (this._bracket.empty()) {
            this._answer[i] = false;
            break;
          } else if (this._bracket.peek() != '(') {
            this._answer[i] = false;
            break;
          } else {
            this._bracket.pop();
          }
        }

        else if (jch == ']') {
          if (this._bracket.empty()) {
            this._answer[i] = false;
            break;
          } else if (this._bracket.peek() != '[') {
            this._answer[i] = false;
            break;
          } else
            this._bracket.pop();
        }
      }
      
      if(!this._bracket.empty())
        this._answer[i] = false;
    }
  }
  
  private void printAnswer() {
    for(boolean b : this._answer) {
      if(b)
        System.out.println("yes");
      else
        System.out.println("no");
    }
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
