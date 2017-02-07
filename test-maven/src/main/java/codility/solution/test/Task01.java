package codility.solution.test;

/*
 * that, given an integer N, returns the number of times the digit 1 occurs in decimal representations of all positive integers not exceeding N.
 * For example, given N = 13 the function should return 6, because:
 */
public class Task01 {
	
	public static void main(String[] args) {		
		int N = 13; // 6
		System.out.println(solution(N));
	}
	
	public static int solution(int number) {
		boolean[] digitSeen = new boolean[10];
	    // set each seen digit
	    int count = 0;
	    while (number != 0) {
	        // get the rightmost digit with the modulo operator (%)
	        int digit = number % 10;
	        if (digitSeen[digit] == false) {
	            // only count if this is the first time we have seen it
	            ++count;
	            digitSeen[digit] = true;
	        }
	        // pop off the right-most digit by dividing by 10
	        number /= 10;
	    }
	    return count;
	}
		
}
