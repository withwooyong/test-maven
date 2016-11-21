package codility.solution;

/**
 * http://www.geeksforgeeks.org/equilibrium-index-of-an-array/
 * @author wooyong
 *
 */
public class Task1 {
	
	private static int[][] matrix = new int[5][5];

    public static void set(int x, int y, int value) {
        if (x >= matrix.length) {
            int[][] tmp = matrix;
            matrix = new int[x + 1][];
            System.arraycopy(tmp, 0, matrix, 0, tmp.length);
            for (int i = x; i < x + 1; i++) {
                matrix[i] = new int[y];
            }
        }

        if (y >= matrix[x].length) {
            int[] tmp = matrix[x];
            matrix[x] = new int[y + 1];
            System.arraycopy(tmp, 0, matrix[x], 0, tmp.length);
        }
        matrix[x][y] = value;
    }

    public static int get(int x, int y) {
        return x >= matrix.length || y >= matrix[x].length ? 0 : matrix[x][y];
    }

    public static void main(String[] args) {

    	/*
    	 * A[0][0] = 2 A[0][1] = 7 A[0][2] = 5
    	 * A[1][0] = 3 A[1][1] = 1 A[1][2] = 1
    	 * A[2][0] = 2 A[2][1] = 1 A[2][2] = -7
    	 * A[3][0] = 0 A[3][1] = 2 A[3][2] = 1
    	 * A[4][0] = 1 A[4][1] = 6 A[4][2] = 8
    	 */    	

        set(1, 1, 1);     // set (1, 1) to 1
        set(10, 10, 2);   // set (10, 10) to 2
        set(100, 100, 3); // set (100, 100) to 3

        System.out.println(get(1, 1));     // outputs 1
        System.out.println(get(10, 10));   // outputs 2
        System.out.println(get(100, 100)); // outputs 3 
    }
	
	

}
