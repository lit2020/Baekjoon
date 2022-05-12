package 백준1541;


import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
  
  /**
   * get input string from keyboard
   * @return user input string
   */
  public static String input() {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    if(!input.startsWith("-")) {
      input = "+" + input;
    }
    scanner.close();
    
    return input;
  }
  
  /**
   * 
   * @param str String that user enter
   * @return
   */
  public static Stack<String> parse(String str) {
    StringTokenizer st = new StringTokenizer(str, "+-", true);
    Stack<String> stack = new Stack<String>();
    while(st.hasMoreTokens()) {
      stack.push(st.nextToken());
    }
    
    return stack;
  }
  
  /**
   * 
   * @param stack
   * @return
   */
  public static int solution(Stack<String> stack) {
    int answer = 0;
    int sub_total = 0;
    int last_number = 0;
    while(!stack.isEmpty()) {
      String token = stack.pop();
      if(token.equals("+")) {
        sub_total += last_number;
      }
      else if(token.equals("-")) {  
        answer -= (sub_total + last_number);
        sub_total = 0;
      }
      else {
        last_number = Integer.parseInt(token);
      }
    }
    answer += sub_total;
    
    return answer;
  }
  
  public static void main(String[] args) {
    String input = input();
    Stack<String> stack = parse(input);
    int answer = solution(stack);
    System.out.println(answer);
  }
}
