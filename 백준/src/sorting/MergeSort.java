package sorting;

public class MergeSort<E extends Comparable<E>> extends Sort<E> {

    public MergeSort() {
	super();
    }

    public MergeSort(boolean reverseOrder) {
	super(reverseOrder);
    }

    @Override
    public void sort(E[] arr) {
	this.mergeSort(arr, 0, arr.length - 1);
    }

    protected void mergeSort(E[] arr, int left, int right) {
	if (left < right) {
	    int mid = (left + right) / 2;
	    this.mergeSort(arr, left, mid);
	    this.mergeSort(arr, mid + 1, right);
	    this.merge(arr, left, mid, right);
	}
    }

    @SuppressWarnings("unchecked")
    private void merge(E[] arr, int left, int mid, int right) {
	Object[] sorted = new Object[arr.length];
	int i = left;
	int j = mid + 1;
	int k = left;
	while (i <= mid && j <= right) {
	    if (arr[i].compareTo(arr[j]) <= 0) {
		sorted[k++] = arr[i++];
	    } else {
		sorted[k++] = arr[j++];
	    }
	}

	if (i <= mid) {
	    for (int l = i; l <= mid; l++) {
		sorted[k++] = arr[l];
	    }
	} else {
	    for (int l = j; l <= right; l++) {
		sorted[k++] = arr[l];
	    }
	}
	for (int l = left; l <= right; l++) {
	    arr[l] = (E)sorted[l];
	}
    }

}
