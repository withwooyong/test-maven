package algorithm.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Powerset {

	public static void main(String[] args) {
		// powerSet(5);
		
		
		
	}

	private static char data[] = { 'a', 'b', 'c', 'd', 'e', 'f' };
	private static int n = data.length;
	private static boolean[] include = new boolean[n];

	public static void powerSet(int k) {
		if (k == n) {
			for (int i = 0; i < n; i++) {
				if (include[i]) {
					System.out.print(data[i] + " ");
				}
			}
			System.out.println();
			return;
		}
		include[k] = false;
		powerSet(k + 1);
		include[k] = true;
		powerSet(k + 1);
	}

	public static ArrayList<String> getpowerset(int a[], int n, ArrayList<String> ps) {
		if (n < 0) {
			return null;
		}
		if (n == 0) {
			if (ps == null)
				ps = new ArrayList<String>();
			ps.add(" ");
			return ps;
		}
		ps = getpowerset(a, n - 1, ps);
		ArrayList<String> tmp = new ArrayList<String>();
		for (String s : ps) {
			if (s.equals(" "))
				tmp.add("" + a[n - 1]);
			else
				tmp.add(s + a[n - 1]);
		}
		ps.addAll(tmp);
		return ps;
	}

}
