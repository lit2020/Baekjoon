package sorting;

public class QuickSort<E extends Comparable<E>> extends Sort<E> {

    public QuickSort() {
	super();
    }
    
    public QuickSort(boolean reverseOrder) {
	super(reverseOrder);
    }
    
    @Override
    public void sort(E[] arr) {
	this.quickSort(arr, 0, arr.length - 1);
    }

    protected void quickSort(E[] arr, int left, int right) {
	// target array has less than 2 elements;
	if (left >= right)
	    return;

	int mid = this.partition(arr, left, right);
	this.quickSort(arr, left, mid - 1);
	this.quickSort(arr, mid + 1, right);
    }

    private int partition(E[] arr, int left, int right) {
	int pivotIdx = right;
	E pivot = arr[pivotIdx];
	int i = left;
	int j = right + 1;
	swap(arr, left, pivotIdx);
	do {
	    do {
		++i;
	    } while (arr[i].compareTo(pivot) < 0 && i < right);
	    do {
		--j;
	    } while (arr[j].compareTo(pivot) > 0);
	    if (i < j)
		swap(arr, j, i);
	} while (i < j);

	swap(arr, j, left);
	return j;
    }
}
