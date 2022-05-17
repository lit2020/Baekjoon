package sorting;

public class HeapSort<E extends Comparable<E>> extends Sort<E> {

    // Index where the sorting starts
    private static int HEAP_ROOT = 0;

    public HeapSort() {
	super();
    }

    public HeapSort(boolean reverseOrder) {
	super(reverseOrder);
    }

    @Override
    public void sort(E[] arr) {
	this.heapSort(arr, 0, arr.length - 1);
	/*
	 * E[] temp = Arrays.copyOf(arr, arr.length + HEAP_ROOT); for(int i = arr.length
	 * - 1; i >= 0; i--) { temp[i + HEAP_ROOT] = temp[i]; } this.heapSort(temp,
	 * HEAP_ROOT, arr.length); for(int i = 0; i < arr.length; i++) { arr[i] =
	 * (E)temp[i + 1]; }
	 */
    }

    protected void heapSort(E[] arr, int root, int heapSize) {
	HeapSort.HEAP_ROOT = root;
	int IdxFirstNonLeaf = heapSize / 2;
	for (int i = IdxFirstNonLeaf; i >= root; i--) {
	    this.adjust(arr, i, heapSize);
	}
	for (int i = 0; i < heapSize; i++) {
	    E maxElement = this.removeMax(arr, heapSize - i);
	    arr[heapSize - i] = maxElement;
	}
    }

    private void adjust(E[] arr, int start, int heapSize) {
	int IdxParent = start;
	E parent = arr[IdxParent];
	while (IdxParent <= heapSize / 2) {
	    int IdxLeftChild = leftChildIndexOf(IdxParent);
	    int IdxRightChild = rightChildIndexOf(IdxParent);
	    int IdxBiggerChild = IdxLeftChild;
	    if (IdxRightChild <= heapSize) {
		if (arr[IdxRightChild].compareTo(arr[IdxLeftChild]) >= 0) {
		    IdxBiggerChild = IdxRightChild;
		}
	    }

	    if (arr[IdxBiggerChild].compareTo(parent) >= 0) {
		arr[IdxParent] = arr[IdxBiggerChild];
		IdxParent = IdxBiggerChild;
		continue;
	    }
	    break;
	}
	arr[IdxParent] = parent;
    }
    
    private int leftChildIndexOf(int parentIndex) {
	return parentIndex * 2 - (HeapSort.HEAP_ROOT - 1);
    }

    private int rightChildIndexOf(int parentIndex) {
	return parentIndex * 2 - (HeapSort.HEAP_ROOT - 2);
    }

    private E removeMax(E[] arr, int heapSize) {
	E maxElement = arr[HEAP_ROOT];
	arr[HEAP_ROOT] = arr[heapSize];
	this.adjust(arr, HEAP_ROOT, heapSize - 1);
	return maxElement;
    }

}
