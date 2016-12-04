package codingdojang;

public class NQueen {
	
	public static void main(String[] args) {
		for (short n = 1; n < 14; n++) {
			enumerate(n);
			System.out.println("n = " + (n < 10 ? "0" : "") + n + ", solution count is " + count + ".");
			count = 0;
		}
	}

	static int count = 0;
	
	public static void enumerate(int N) {
		int[] intArr = new int[N];
		enumerate(intArr, 0);
	}

	public static void enumerate(int[] intArr, int n) {
		int N = intArr.length;
		// n이 끝까지 돌았다면 카운트를 증가한다.
		if (n == N) {
			count++;
		} else {
			for (int i = 0; i < N; i++) {
				intArr[n] = i;
				if (isPromising(intArr, n))
					enumerate(intArr, n + 1); // 유망하다면 계속 탐색(재귀호출)
			}
		}
	}

	public static boolean isPromising(int[] q, int n) {
		for (int i = 0; i < n; i++) {
			if (q[i] == q[n])
				return false; // 같은 열인지
			if ((q[i] - q[n]) == (n - i))
				return false; // '\' 방향
			if ((q[n] - q[i]) == (n - i))
				return false; // '/' 방향
		}
		return true;
	}

	
}