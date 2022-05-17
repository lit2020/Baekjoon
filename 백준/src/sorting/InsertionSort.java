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
	for (int i = left+1; i <= right; i++) {
	    E elementForInsert = arr[i];
	    int j;
	    for (j = i - 1; j >= left; j--) {
		if (this.compare(elementForInsert, arr[j]) < 0)
		    arr[j+1] = arr[j];
		else
		    break;
	    }
	    arr[j+1] = elementForInsert;
	}
    }

}
