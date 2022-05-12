package 백준10610;

public class Test {

  public static void main(String[] args) {
    String s = "1234567898765432";
    String[] sp = s.split("");
    for(int i=0; i<s.length(); i++) {
      System.out.print(sp[i] + " ");
    }
  }
}
