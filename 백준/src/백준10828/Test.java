package น้มุ10828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

  public static void main(String[] args) throws NumberFormatException, IOException {
    Scanner in = new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] arr = new String[10];
    int n = Integer.parseInt(br.readLine());
    for(int i = 0; i<10; i++) {
      arr[i] = (br.readLine());
    }
    
    System.out.println(Arrays.toString(arr));
  }

}

