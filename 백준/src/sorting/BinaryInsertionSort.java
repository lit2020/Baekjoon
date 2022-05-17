package sorting;

public class BinaryInsertionSort<E extends Comparable<E>> extends InsertionSort<E> {

    public BinaryInsertionSort() {
	super();
    }

    public BinaryInsertionSort(boolean reverseOrder) {
	super(reverseOrder);
    }

    @Override
    public void sort(E[] arr) {
	this.binaryInsertionSort(arr, 0, arr.length - 1);
    }
    
    protected void binaryInsertionSort(E[] arr, int left, int right) {
	for(int i = 0; i < right; i++) {
	    int low = 0;
	    int high = i;
	    int mid;
	    E elementForInsertion = arr[i + 1];
	    while(low <= high) {
		mid = (low + high) / 2;
		if(this.compare(elementForInsertion, arr[mid]) < 0) {
		    high = mid - 1;
		}
		else if(this.compare(elementForInsertion, arr[mid]) >= 0) {
		    low = mid + 1;
		}
	    }
	    // low 부터 뒤로 한칸씩 시프트
	    for(int j = i; j >= low; j--) {
		arr[j+1] = arr[j];
	    }
	    // arr[low]에 삽입
	    arr[low] = elementForInsertion;
	}
    }

}
