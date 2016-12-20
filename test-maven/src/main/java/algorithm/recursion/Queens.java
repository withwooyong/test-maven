package algorithm.recursion;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/******************************************************************************
 * Compilation: javac Queens.java Execution: java Queens n
 * 
 * Solve the 8 queens problem using recursion and backtracing. Prints out all
 * solutions.
 *
 * Limitations: works for n <= 25, but slows down considerably for larger n.
 *
 * Remark: this program implicitly enumerates all n^n possible placements
 * (instead of n!), but the backtracing prunes off most of them, so it's not
 * necessarily worth the extra complication of enumerating only permutations.
 *
 *
 * % java Queens 3
 *
 * % java Queens 4 
 * * Q * * 
 * * * * Q 
 * Q * * * 
 * * * Q *
 *
 * * * Q * 
 * Q * * * 
 * * * * Q 
 * * Q * *
 *
 * % java Queens 8 
 * Q * * * * * * * 
 * * * * * Q * * * 
 * * * * * * * * Q 
 * * * * * * Q * * 
 * * * * Q * * * * 
 * * * * * * * * Q 
 * * * Q * * * * * 
 * * * * * Q * * * 
 * ...
 *
 ******************************************************************************/

public class Queens {
	
	private static Logger log = LoggerFactory.getLogger(Queens.class);
	
	public static void main(String[] args) {
		//int n = Integer.parseInt(args[0]);
		int n = 10;
		int[] a = new int[n];
		enumerate(a, 0);
	}
	
	/***************************************************************************
	 * Try all permutations using backtracking
	 ***************************************************************************/
	public static void enumerate(int[] q, int k) {
		int n = q.length;
		if (k == n) {
			printQueens(q);
		} else {
			for (int i = 0; i < n; i++) {
				q[k] = i;
				if (isConsistent(q, k)) {
					enumerate(q, k + 1);
				}
			}
		}
	}

	/***************************************************************************
	 * Return true if queen placement q[n] does not conflict with other queens
	 * q[0] through q[n-1]
	 ***************************************************************************/
	public static boolean isConsistent(int[] q, int n) {
		for (int i = 0; i < n; i++) {
			if (q[i] == q[n]) {
				return false; // same column
			} else if ((q[i] - q[n]) == (n - i)) {
				return false; // same major diagonal
			} else if ((q[n] - q[i]) == (n - i)) {
				return false; // same minor diagonal
			}
		}
		return true;
	}

	/***************************************************************************
	 * Prints n-by-n placement of queens from permutation q in ASCII.
	 ***************************************************************************/
	public static void printQueens(int[] q) {
		int n = q.length;
		for (int i = 0; i < n; i++) {
			ArrayList<String> list = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				if (q[i] == j) {
					list.add("Q");
				} else {
					list.add("*");
				}	
			}
			log.debug("{}", list.toString());
		}
		log.debug("");
	}	
}
