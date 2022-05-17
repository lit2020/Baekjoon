package sorting;

public class BubbleSort<E extends Comparable<E>> extends Sort<E> {

    public BubbleSort() {
	super();
    }

    public BubbleSort(boolean reverseOrder) {
	super(reverseOrder);
    }

    @Override
    public void sort(E[] arr) {
	this.bubbleSort(arr, 0, arr.length - 1);
    }

    protected void bubbleSort(E[] arr, int left, int right) {
	for (int i = right; i > left; i--) {
	    for (int j = left; j < i; j++) {
		if (arr[j].compareTo(arr[j + 1]) > 0) {
		    this.swap(arr, j, j + 1);
		}
	    }
	}
    }

}
