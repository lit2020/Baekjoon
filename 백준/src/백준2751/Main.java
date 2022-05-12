package 백준2751;

import java.util.Arrays;
import java.util.Scanner;

class Solution {
  
  private int _N;
  private int[] _numbers;
  private int[] _sorted;
  
  private void input() {
    Scanner scanner = new Scanner(System.in);
    this._N = Integer.parseInt(scanner.nextLine());
    this._numbers = new int[this._N];
    for(int i = 0; i < this._N; i++) {
      this._numbers[i] = Integer.parseInt(scanner.nextLine());
    }
    
    scanner.close();
  }
  
  private void merge(int[] arr, int left, int mid, int right) {
    _sorted = new int[this._N];
    int i = left;
    int j = mid + 1;
    int k = left;
    
    while(i <= mid && j <= right) {
      if(arr[i] <= arr[j]) 
        _sorted[k++] = arr[i++];
      else
        _sorted[k++] = arr[j++];
    }
    
    if(i <= mid) {
      for(int l = i; i <= mid; i++) {
        _sorted[k++] = arr[l];
      }
    }
    else {
      for(int l = j; j <= right; j++) {
        _sorted[k++] = arr[l];
      }
    }
    
    for(int l = left; l <= right; l++) {
      arr[l] = _sorted[l];
    }
      
  }
  
  private void mergeSort(int[] arr, int left, int right) {
    if(left < right) {
      int mid = (left + right) / 2;
      this.mergeSort(arr, left, mid);
      this.mergeSort(arr, mid+1, right);
      this.merge(arr, left, mid, right);
    }
  }
  
  private void solution() {
    this.mergeSort(this._numbers, 0, this._N-1);
  }
  
  private void printAnswer() {
    System.out.print(Arrays.toString(this._numbers));
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
