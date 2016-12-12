package codility.lesson02.arrays;

public class Arrays {
	
	public static void main(String[] args) {
		//solutionA();
		//solutionB();
		reverse();
	}
	
	public static void test() {
		int[] A = new int[] { 2, 1, 5, 1, 2, 2, 2 };
		
	}
	
	public static void solutionB() {
		char[] B = new char[10];
		for (char c : B) {
			System.out.println("test=" + c);
		}
	}

	public static void solutionA() {
		int[] A = new int[10];
		for (int i : A) {
			System.out.println(i);
		}
	}
	
	// 문자열 뒤집기
	public static void reverse() {
		String str = "abcd";
		System.out.println(new StringBuilder(str).reverse().toString());
	}

}
