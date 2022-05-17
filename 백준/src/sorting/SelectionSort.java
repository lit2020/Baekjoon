package sorting;

public class SelectionSort<E extends Comparable<E>> extends Sort<E> {

    public SelectionSort() {
	super();
    }

    public SelectionSort(boolean reverseOrder) {
	super(reverseOrder);
    }

    @Override
    public void sort(E[] arr) {
	this.selectionSort(arr, 0, arr.length - 1);
    }
    
    protected void selectionSort(E[] arr, int left, int right) {
	for(int i = right; i > 0; i--) {
	    int idxLastOrderElement = 0;
	    for(int j = 1; j <= i; j++) {
		if(arr[j].compareTo(arr[idxLastOrderElement]) >= 0) {
		    idxLastOrderElement = j;
		}
	    }
	    this.swap(arr, i, idxLastOrderElement);
	}
    }
}
