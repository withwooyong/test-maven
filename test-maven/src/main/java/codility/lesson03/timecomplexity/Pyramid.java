package codility.lesson03.timecomplexity;

public class Pyramid {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n = 9;
		pattern1(n);
		pattern2(n);
		pattern3(n);
		pattern4(n);
		pattern5(n);
		pattern6(n);
	}

	private static void pattern1(int n) {
		int noOfRows = n;
		// Initializing rowCount with 1
		int rowCount = 1;
		System.out.println("Here Is Your Pyramid");
		// Implementing the logic
		for (int i = noOfRows; i > 0; i--) {
			// Printing i spaces at the beginning of each row
			for (int j = 1; j <= i; j++) {
				System.out.print(" ");
			}
			// Printing 'rowCount' value 'rowCount' times at the end of each row
			for (int j = 1; j <= rowCount; j++) {
				System.out.print(rowCount + " ");
			}
			System.out.println();
			// Incrementing the rowCount
			rowCount++;
		}
	}
	
	private static void pattern2(int n) {
		int noOfRows = n;
		// Initializing rowCount with 1
		int rowCount = 1;
		System.out.println("Here Is Your Pyramid");
		// Implementing the logic
		for (int i = noOfRows; i > 0; i--) {
			// Printing i spaces at the beginning of each row
			for (int j = 1; j <= i; j++) {
				System.out.print(" ");
			}
			// Printing 'j' value at the end of each row
			for (int j = 1; j <= rowCount; j++) {
				System.out.print(j + " ");
			}
			System.out.println();
			// Incrementing the rowCount
			rowCount++;
		}
	}

	private static void pattern3(int n) {
		System.out.println("How Many Rows You Want In Your Pyramid?");
		int noOfRows = n;
		// Initializing rowCount with 1
		int rowCount = 1;
		System.out.println("Here Is Your Pyramid");
		// Implementing the logic
		for (int i = noOfRows; i > 0; i--) {
			// Printing i spaces at the beginning of each row
			for (int j = 1; j <= i; j++) {
				System.out.print(" ");
			}
			// Printing * at the end of each row
			for (int j = 1; j <= rowCount; j++) {
				System.out.print("* ");
			}
			System.out.println();
			// Incrementing the rowCount
			rowCount++;
		}
	}

	private static void pattern4(int n) {
		// Taking noOfRows value from the user
		System.out.println("How Many Rows You Want In Your Pyramid?");
		int noOfRows = n;
		// Initializing rowCount with 1
		int rowCount = 1;
		System.out.println("Here Is Your Pyramid");
		// Implementing the logic
		for (int i = noOfRows; i > 0; i--) {
			// Printing i*2 spaces at the beginning of each row
			for (int j = 1; j <= i * 2; j++) {
				System.out.print(" ");
			}
			// Printing j value where j value will be from 1 to rowCount
			for (int j = 1; j <= rowCount; j++) {
				System.out.print(j + " ");
			}
			// Printing j value where j value will be from rowCount-1 to 1
			for (int j = rowCount - 1; j >= 1; j--) {
				System.out.print(j + " ");
			}
			System.out.println();
			// Incrementing the rowCount
			rowCount++;
		}
	}
	
	private static void pattern5(int n) {
		// Taking noOfRows value from the user
		System.out.println("How Many Rows You Want In Your Pyramid?");
		int noOfRows = n;
		// Initializing rowCount with noOfRows
		int rowCount = noOfRows;
		System.out.println("Here Is Your Pyramid");
		// Implementing the logic
		for (int i = 0; i < noOfRows; i++) {
			// Printing i*2 spaces at the beginning of each row
			for (int j = 1; j <= i * 2; j++) {
				System.out.print(" ");
			}
			// Printing j where j value will be from 1 to rowCount
			for (int j = 1; j <= rowCount; j++) {
				System.out.print(j + " ");
			}
			// Printing j where j value will be from rowCount-1 to 1
			for (int j = rowCount - 1; j >= 1; j--) {
				System.out.print(j + " ");
			}
			System.out.println();
			// Decrementing the rowCount
			rowCount--;
		}
	}
	
	private static void pattern6(int n) {
		// Taking noOfRows value from the user
		System.out.println("How Many Rows You Want In Your Pyramid?");
		int noOfRows = n;
		// Initializing rowCount with 1
		int rowCount = 1;
		System.out.println("Here Is Your Pyramid");
		// Implementing the logic
		for (int i = noOfRows; i >= 1; i--) {
			// Printing i*2 spaces at the beginning of each row
			for (int j = 1; j <= i * 2; j++) {
				System.out.print(" ");
			}
			// Printing j where j value will be from i to noOfRows
			for (int j = i; j <= noOfRows; j++) {
				System.out.print(j + " ");
			}
			// Printing j where j value will be from noOfRows-1 to i
			for (int j = noOfRows - 1; j >= i; j--) {
				System.out.print(j + " ");
			}
			System.out.println();
			// Incrementing the rowCount
			rowCount++;
		}
	}
}
