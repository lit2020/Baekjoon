package sorting;

public class InsertionSort<E extends Comparable<E>> extends Sort<E> {

    public InsertionSort() {
	super();
    }

    public InsertionSort(boolean reverseOrder) {
	super(reverseOrder);
    }

    @Override
    public void sort(E[] arr) {
	this.insertionSort(arr, 0, arr.length - 1);
    }

    protected void insertionSort(E[] arr, int left, int right) {
	for (int i = left; i < right; i++) {
	    for (int j = i + 1; j > left; j--) {
		if (this.compare(arr[j], arr[j-1]) < 0)
		    swap(arr, j, j - 1);
		else 
		    break;
	    }
	}
    }

}
