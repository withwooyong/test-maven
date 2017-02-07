package codility.solution.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
 * 
 */
public class Task4 {
	
	public static void main(String[] args) {
		
//		int M = 5;
//		int X = 2;
//		int Y = 200;
//		int[] A = { 60, 80, 40 };
//		int[] B = { 2, 3, 5 }; // 5
		
		int M = 3;
		int X = 5;
		int Y = 200;
		int[] A = { 40, 40, 100, 80, 20 };
		int[] B = { 3, 3, 2, 2, 3 }; // 6
		System.out.println(solution(A, B, M, X, Y));

	}

	public static int solution(int[] A, int[] B, int M, int X, int Y) {
        int totalStops = 0;
        long totalWeightPerRound = 0;
        int maxPersonsCount = 0;
        List<Integer> lstFloors = new ArrayList<Integer>(); // items
        int currPerson = 0;
        boolean startLift = false;
        while (currPerson < A.length) {
            if ((totalWeightPerRound + A[currPerson]) <= Y && (maxPersonsCount+1) <= X) {
                totalWeightPerRound += A[currPerson];
                maxPersonsCount++;
                lstFloors.add(B[currPerson]);
                if (currPerson == A.length - 1) {
                    startLift = true;
                }
                currPerson++;
            } else {
                startLift = true;
            }

            if (startLift) {
            	// HashSet 데이터 형태로 생성되면서 중복 제거됨
            	// new ArrayList<Integer>(new HashSet<Integer>(items));
            	ArrayList<Integer> newArrList = new ArrayList<Integer>(new HashSet<Integer>(lstFloors));
            	totalStops += newArrList.size() + 1;
                //totalStops += lstFloors.Distinct().Count() + 1;
                //reset variable
                lstFloors.clear();
                maxPersonsCount = 0;
                totalWeightPerRound = 0;
                startLift = false;
            }
        }

        return totalStops;
    }


}
