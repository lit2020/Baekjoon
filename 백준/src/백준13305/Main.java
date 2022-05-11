/*
 * 05.04.2022
 */

package น้มุ13305;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class City implements Comparable<City>{
  
  protected int oil_price;
  protected long end_distance;
 
  protected City(int p, long d) {
    this.oil_price = p;
    this.end_distance = d;
  }
  
  public String toString() {
    return "[" + oil_price + ", " + end_distance + "]";
  }

  @Override
  public int compareTo(City o) {
    if(this.oil_price < o.oil_price)
      return -1;
    
    else if(this.oil_price > o.oil_price)
      return 1;
    
    else 
      return 0;
  }
}

class Solution {
  /* <= 10^5 */
  private int number_of_city;
  
  /* <= 10^9  */
  private int[] distance_between_city;
  
  /* distance from each city to end point */
  private long[] distance_to_end;
  
  /* oil price of each city 
   * <= 10^9
   */
  private int[] oil_price;
  
  /* each element of this list is combined @distance_to_end with @oil_price*/
  private List<City> distance_and_price;
  
  /* because Maximum value of answer is 2^77, it can't be represented by long type
   * so we use Number type, actually BigInteger type
   */
  private Number answer;
  
  protected void input() {
    Scanner scanner = new Scanner(System.in);

    number_of_city = Integer.parseInt(scanner.nextLine());
    distance_between_city = new int[number_of_city - 1];
    distance_to_end = new long[number_of_city - 1];
    oil_price = new int[number_of_city];
    
    String temp = scanner.nextLine();
    distance_between_city = Arrays.asList(temp.split(" ")).stream()
                      .mapToInt(Integer::parseInt).toArray();
 
    temp = scanner.nextLine();
    oil_price = Arrays.asList(temp.split(" ")).stream()
                      .mapToInt(Integer::parseInt).toArray();
    
   scanner.close();
  }
  
  private void solution() {
    BigInteger total_expense = new BigInteger("0");
    long mileage = 0;
    
    distance_to_end[number_of_city - 2] = distance_between_city[number_of_city - 2];
    for(int i = number_of_city - 3; i >= 0; i--) {
      distance_to_end[i] += distance_to_end[i+1] + distance_between_city[i];
    }
    
    distance_and_price = new ArrayList<City>();
    int i;
    for(i = 0; i < this.number_of_city - 1; i++) {
      this.distance_and_price.add(new City(this.oil_price[i], this.distance_to_end[i]));
    }
    this.distance_and_price.add(new City(this.oil_price[i], 0));
    
    /**
     * Sort @City by price by ascending order
     */
    Collections.sort(distance_and_price);
    
    for(i = 0; i < this.number_of_city; i++) {
      City cur_city = distance_and_price.get(i);
      if(cur_city.end_distance - mileage > 0) {
        total_expense = total_expense.add(BigInteger.valueOf(cur_city.oil_price * (cur_city.end_distance - mileage)));
        mileage += (cur_city.end_distance - mileage);
      }
    }
    
    this.answer = total_expense;
    return;
  }
  
  private void printAnswer() {
    System.out.print(this.answer);
  }
  
  protected void solve() {
    this.input();
    this.solution();
    this.printAnswer();
    /*
    System.out.println(Arrays.toString(distance_between_city));
    System.out.println(Arrays.toString(distance_to_end));
    System.out.println(Arrays.toString(oil_price));
     */
  }
}


public class Main {
  public static void main(String[] args) {
    (new Solution()).solve();
  }
}
