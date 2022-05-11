/**
 * 05/06/2022
 */

package น้มุ10828;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

class Solution {
  private Stack _stack;
  
  private int _numberOfCommand;
  private String[] _command; 
  private ArrayList<Integer> _answer;
  
  private void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Scanner in = new Scanner(System.in);    
    
    // does not work
    // String s = "";
    // this._numberOfCommand = Integer.parseInt(s = in.nextLine());
    this._numberOfCommand = Integer.parseInt(br.readLine());
    this._command = new String[this._numberOfCommand];
    for(int i = 0; i < this._numberOfCommand; i++)
      this._command[i] = br.readLine();
  }
  
  private void solution() {
    this._stack = new Stack(10000);
    this._answer = new ArrayList<Integer>(10000);
    for(String com : this._command) {
      String[] line = com.split(" ");
      switch(line[0]) {
        case "push":
          this._stack.push(Integer.parseInt(line[1]));
          break;
          
        case "pop":
          int popped = _stack.pop();
          if(popped != -1)
            this._answer.add(popped);
          else
            this._answer.add(-1);
          break;
          
        case "size":
          this._answer.add(_stack.size());
          break;
          
        case "empty":
          if(_stack.isEmpty())
            this._answer.add(1);
          else
            this._answer.add(0);
          break;
          
        case "top":
          int top = _stack.top();
          if(top != -1)
            this._answer.add(top);
          else
            this._answer.add(-1);
         
      }
    }
  }
  
  private void printAnswer() throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    for(int ans : this._answer)
      bw.write(ans + "\n");
    bw.flush();
  }
  
  protected void solve() throws IOException {
    this.input();
    this.solution();
    this.printAnswer();
    
  }
  private class Stack {
    private int[] _elementData;
    private int _elementCount;
    
    private Stack(int initialCapacity) {
      this._elementData = new int[initialCapacity];
      this._elementCount = 0;
    }

    private int size() {
      return this._elementCount;
    }
    
    private boolean isEmpty() {
      return (this.size() == 0);
    }
    
    private void push(int newElement) {
      this._elementData[this._elementCount++] = newElement;
    }
    
    private int pop() {
      if(this._elementCount == 0)
        return -1;
      
      int removed = top();
      this._elementCount--;
      return removed;
    }
    
    private int top() {
      if(this._elementCount == 0)
        return -1;
      
      return this._elementData[this._elementCount - 1];
    }
    
  }
  
 
}

public class Main {

  public static void main(String[] args) throws IOException {
    (new Solution()).solve();

  }

}
