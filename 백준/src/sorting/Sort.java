package sorting;


public abstract class Sort<E extends Comparable<E>> {

    // Boolean is true if descending order
    protected boolean reverseOrder;
    
    // Constructor
    public Sort() {
	this.reverseOrder = false;
    }
    
    public Sort(boolean reverseOrder) {
	this.reverseOrder = reverseOrder;
    }
    
    // 이 클래스를 상속한 정렬클래스에서 각각 구현
    public abstract void sort(E[] arr);
    
    protected int compare(E element, E another) {
	if(this.reverseOrder)
	    return another.compareTo(element);
	return element.compareTo(another);
    }
    
    protected void swap(E[] arr, int i, int j) {
	E temp = arr[i];
	arr[i] = arr[j];
	arr[j] = temp;
    }
}
