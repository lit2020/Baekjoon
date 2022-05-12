package 백준1946;

import java.util.ArrayList;

public class Test {

  public static void main(String[] args) {
    ArrayList<String> arrayList = new ArrayList<>();

    arrayList.add("Test1");
    arrayList.add("Test2");
    arrayList.add("Test3");

    String[] array = arrayList.toArray(new String[arrayList.size()]);
    
    for(int  i=0; i<array.length; i++) {
      System.out.print(array[i] + " ");
    }
  }

}
