package sorting;

public class TreeSort<E extends Comparable<E>> extends Sort<E> {

    public TreeSort() {
	super();
    }

    public TreeSort(boolean reverseOrder) {
	super(reverseOrder);
    }

    @Override
    public void sort(E[] arr) {
	this.treeSort(arr, 0, arr.length - 1);
    }

    protected void treeSort(E[] arr, int begin, int end) {
	Tree<E> root = new Tree<E>(arr[begin], null, null);
	Tree<E> parent = root;
	for(int i = 1; i <= end; i=i+2) {
	    Tree<E> leftChild = new Tree(arr[i]);
	    Tree<E> right
	}
	
    }
    
    private void addElement() {
	
    }
    class Tree<T extends Comparable<T>> {
	private E node;
	private Tree left;
	private Tree right;
	
	private Tree(E value, Tree leftChild, Tree rightChild) {
	    
	}
    }
}
