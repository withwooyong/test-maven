package codingdojang;

public class Recursion {

	public static void main(String[] args) {
		int n = 10;
		String str = "ABCDEFG";
		int intArr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		//func(n);
		//printBinary(n);
		//printChar(str);
		//printCharReverse(str);
		//System.out.println(sum(intArr.length, intArr));
		//System.out.println(forSum(intArr.length, intArr));
		//findMazePath(0,  0);
		System.out.println(countCells(5, 3)); 
	}
	
	private static int test() {		
		return 0;
	}
	
	private static int [] cols = new int[5];
	// backtracking 상태공간트리란 찾는 해를 포함하는 트리. 깊이우선탐색
	// non-promising
	private static boolean nQueens(int level) {
		if (!promising(level)) {
			return false;
		} else if (level == N) {
			for (int i = 1; i <= N; i++) {
				System.out.println("i=" + cols[i]);
			}
			return true;
		} else {
			for (int i = 1; i <= N; i++) {
				cols[level+1] = i;
				if (nQueens(level+1)) {
					return true;
				}
			}
			return false;
		}
	}
	
	private static boolean promising(int level) {
		for (int i = 1; i < level; i++) {
			if (cols[i] == cols[level]) { // 같은 열에 놓였는지 검사
				return false;
			} else if (level-i == Math.abs(cols[level]-cols[i])) { // 같은 대각선에 놓였는지 검사
				return false;
			}
		}
		return true;
	}
	
	private static int IMAGE_COLOR = 0;
	private static int BACKGROUND_COLOR = 1;
	private static int ALREADY_COUNTED = 2;
	private static int [][] grid = {
			{0, 1, 1, 1, 1, 1, 1, 0}, 	
			{1, 0, 0, 1, 1, 0, 1, 1},
			{0, 0, 1, 1, 0, 1, 0, 1},
			{1, 1, 1, 1, 1, 0, 1, 1},
			{1, 0, 1, 0, 1, 0, 1, 1},
			{1, 0, 1, 0, 1, 0, 1, 1},
			{0, 1, 1, 1, 0, 1, 1, 0},
			{1, 0, 0, 1, 1, 0, 0, 0}
	};
	
	public static int countCells(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N) { // 좌표범위검사
			return 0;
		} else if (grid[x][y] != IMAGE_COLOR) { 
			return 0;
		} else {
			// IMAGE_COLOR 이면 카운트 동서남북+대각선포함 8가지 경우 
			grid[x][y] = ALREADY_COUNTED;
			return 1 + countCells(x,  y+1) + countCells(x+1,  y+1)
			+ countCells(x+1,  y) + countCells(x+1,  y-1)
			+ countCells(x,  y-1) + countCells(x-1,  y-1)
			+ countCells(x-1,  y) + countCells(x-1,  y+1);
		}
	}
	
	private static int N = 8;
	private static int [][] maze = {
			{0, 0, 0, 0, 0, 0, 0, 1}, 	
			{0, 1, 1, 0, 1, 1, 0, 1},
			{0, 0, 0, 1, 0, 0, 0, 1},
			{0, 1, 0, 0, 1, 1, 0, 0},
			{0, 1, 1, 1, 0, 0, 1, 1},
			{0, 1, 0, 0, 0, 1, 0, 1},
			{0, 0, 0, 1, 0, 0, 0, 1},
			{0, 1, 1, 1, 0, 1, 0, 0}
	};
	
	private static final int PATHWAY_COLOR = 0; // white
	private static final int WALL_COLOR = 1;	// blue
	private static final int BLOCKED_COLOR = 2;	// red
	private static final int PATH_COLOR = 3;	// green
	
	// maze 미로
	public static boolean findMazePath(int x, int y) {
		//System.out.println("start x=" + x + " y=" + y);
		if ((x < 0) || (y < 0) || (x >= N) || (y >= N)) {
			return false;
		} else if (maze[x][y] != PATHWAY_COLOR) { // 지나갈수 있는길이 아니면
			return false;
		} else if (x == (N - 1) && y == (N - 1)) { // 최종출구
			System.out.println("end x=" + x + " y=" + y + " maze[x][y]=" + maze[x][y]);
			maze[x][y] = PATH_COLOR; // 지나간 길
			return true;
		} else {
			// maze[x][y] = PATHWAY_COLOR 이면 지나갈 수 있다.
			System.out.println("x=" + x + " y=" + y + " maze[x][y]=" + maze[x][y]);
			maze[x][y] = PATH_COLOR; // 지나간 길
			// 동서남북
			if (findMazePath(x-1,  y) || findMazePath(x+1, y) ||
					findMazePath(x, y-1) || findMazePath(x, y+1)) {				
				return true;
			} else {
				maze[x][y] = BLOCKED_COLOR;
			}
			return false;
		}
	}
	
	public static int binarySearch(String[] items, String target, int begin, int end) {
		if (begin > end) {
			return -1;
		} else {
			int middle = (begin+ end) / 2;
			int compResult = target.compareTo(items[middle]);
			
			if (compResult == 0) {
				return middle;
			} else if (compResult < 0) {
				return binarySearch(items, target, begin, middle-1); 
			} else {
				return binarySearch(items, target, middle, end);
			}
		}
	}
	
	public static int findMax(int[] data, int begin, int end) {
		if (begin == end) {
			return data[begin];
		} else {
			return Math.max(data[begin], findMax(data, begin+1, end)); // 최대값 찾기
		}
	}
	
	public static int findMax2(int[] data, int begin, int end) {
		if (begin == end) {
			return data[begin];			
		} else {
			int middle = (begin + end) / 2;
			int max1 = findMax(data, begin, middle);
			int max2 = findMax(data, middle+1, end); // begin middle middle+1 end
			return Math.max(max1, max2);
		}
	}
	
	// 암시적 explicit
	public static int search(int[] data, int end, int target) {
		for (int i = 0; i < end; i++) {
			if (data[i] == target) {
				return i;
			} 
		}
		return -1;
	}
	
	// 명시적 implicit
	public static int searchRecursion(int[] data, int begin, int end, int target) {
		if (begin > end) {
			return -1;
		} else if (target == data[begin]) {
			return begin;
		} else {
			return searchRecursion(data, begin+1, end, target);
		}
	}
	
	public static int sum(int n, int[] data) {
		if (n <= 0) {
			return 0;
		} 
		System.out.println(n-1);
		return data[n-1] + sum(n-1, data);
	}
	
	public static int forSum(int n, int[] data) {
		int sum = 0;
		if (n < 0) {
			return 0;
		}
		
		for (int i : data) {
			System.out.println(i);
			sum += data[i-1];
		}
		return sum;
	}
	
	public static void func(int k) {
		if (k <= 0) {
			return;
		} else {			
			System.out.println("Hello");
			func(k-1);
		}		
	}
	
	public static void printBinary(int n) {
		if (n <= 2) {
			System.out.print(n);
		} else {			
			printBinary(n/2);
			System.out.print(n%2);
		}
	}
	
	public static void printChar(String str) {
		if (str.length() == 0) {
			return;
		} else {
			System.out.print(str.charAt(0));
			printChar(str.substring(1));
		}
	}
	
	public static void printCharReverse(String str) {
		if (str.length() == 0) {
			return;
		} else {
			printCharReverse(str.substring(1));
			System.out.print(str.charAt(0));
		}
	}

}
