package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class Test {
    // Instances of Sorting Class
    static final QuickSort<Integer> qs = new QuickSort<Integer>(false);
    static final InsertionSort<Integer> is = new InsertionSort<Integer>(false);
    static final MergeSort<Integer> ms = new MergeSort<Integer>(false);
    static final SelectionSort<Integer> ss = new SelectionSort<Integer>(false);
    static final BubbleSort<Integer> bs = new BubbleSort<Integer>(false);
    static final HeapSort<Integer> hs = new HeapSort<Integer>(false);
    static final Sort<Integer> sortMethod = hs;
    
    static final Random random = new Random();
    static final int T_num = 100;
    static final int T_SIZE = 1000;
    static final int MAX_VALUE = 2000;
    static final int MIN_VALUE = 0;
    static final int RANGE = MAX_VALUE - MIN_VALUE;
    static Integer[] ARRAY = new Integer[T_SIZE];

    static final String MSG_CLEAR = "CLEAR";
}


public class SortTest {
    static final boolean mode_random = true;
    static final boolean[] t_result = new boolean[Test.T_num];
    static final ArrayList<String> counter_example = new ArrayList<String>(Test.T_num);
    
    public static void main(String[] args) throws IOException {
	for (int i = 0; i < Test.T_num; i++) {
	    // Get input
	    if (mode_random)
		random_init();
	    else
		init();
	    
	    // Run Test
	    t_result[i] = test(Test.sortMethod);
	    
	    // Add counter example if the answer is incorrect
	    if (!t_result[i])
		counter_example.add(Arrays.toString(Test.ARRAY));
	}
	
	if (counter_example.size() == 0)
	    System.out.println(Test.MSG_CLEAR);
	else {
	    for (String ce : counter_example)
		System.out.println(ce);
	}
    }

    private static void init() throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String line = br.readLine();
	line = line.substring(1, line.length() - 1).replace(" ", "");
	String[] arrayString = line.split(",");
	int[] temp = Arrays.stream(arrayString).mapToInt(Integer::parseInt).toArray();
	Test.ARRAY = Arrays.stream(temp).boxed().toArray(Integer[]::new);
    }

    private static void random_init() {
	for (int i = 0; i < Test.T_SIZE; i++)
	    Test.ARRAY[i] = Test.random.nextInt(Test.RANGE) + Test.MIN_VALUE;
    }

    /**
     * 
     * @param sortMethod Instance of sorting class that would be tested
     * @return True if and only if test result is correct
     * @throws IOException
     */
    private static boolean test(Sort<Integer> sortMethod) throws IOException {

	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	Integer[] arr1 = Test.ARRAY.clone();
	Integer[] arr2 = arr1.clone();

	// =====원본출력=====
	System.out.println(Arrays.toString(arr1));
	sortMethod.sort(arr1); // 테스트할 알고리즘으로 정렬
	Arrays.sort(arr2);
	String result1, result2;
	boolean correct;
	bw.write(result1 = Arrays.toString(arr1) + "\n"); // 알고리즘에 의한 정렬
	bw.write(result2 = Arrays.toString(arr2) + "\n"); // 올바른 정렬
	correct = result1.equals(result2);
	bw.write(correct + "\n\n"); // 정렬결과 올바르면 true, 틀리면 false 출력
	bw.flush();
	return correct;
    }
}
