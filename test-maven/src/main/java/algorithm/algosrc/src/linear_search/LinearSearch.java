package algorithm.algosrc.src.linear_search;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// 4.7 선형탐색
public class LinearSearch {

	private static Logger log = LoggerFactory.getLogger(LinearSearch.class);
	
	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 4, 5 };
		int element = 3;
		log.debug("{}", firstIndex(A, element));
	}
	
	// array[index] = element 인 첫 i 를 반환한다.
	public static int firstIndex(int[] array, int element) {
	    for(int i = 0; i < array.length; ++i) {
	        if(array[i] == element) {
	            return i;
	        }
	    }
	    return -1;
	}
}
