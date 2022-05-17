package sorting;

/**
 * HeapRoot를 입력받아 부분정렬 가능
 * pros : Stable performance of O(nlgn) even in the worst case
 * 	  No additional memory is needed
 * cons : Unstable sort, poor performance compared to other O(nlgn) algorithm
 */
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
    }

    protected void heapSort(E[] arr, int root, int heapSize) {

	HeapSort.HEAP_ROOT = root;
	int IdxFirstNonLeaf = (root + heapSize) / 2;
	for (int i = IdxFirstNonLeaf; i >= root; i--) {
	    this.adjust(arr, i, heapSize);
	}
	for (int i = 0; i < heapSize - root; i++) {
	    E maxElement = this.removeMax(arr, heapSize - i);
	    arr[heapSize - i] = maxElement;
	}
    }

    private void adjust(E[] arr, int start, int heapSize) {
	int IdxParent = start;
	E parent = arr[IdxParent];
	// if left child index is out of heap, parent is leaf node
	while (this.leftChildIndexOf(IdxParent) <= heapSize) {
	    int IdxLeftChild = leftChildIndexOf(IdxParent);
	    int IdxRightChild = rightChildIndexOf(IdxParent);
	    int IdxBiggerChild = IdxLeftChild;
	    if (IdxRightChild <= heapSize) {
		if (this.compare(arr[IdxRightChild], arr[IdxLeftChild]) >= 0) {
		    IdxBiggerChild = IdxRightChild;
		}
	    }

	    if (this.compare(arr[IdxBiggerChild], parent) >= 0) {
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
