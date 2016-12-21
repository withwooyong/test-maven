package codility.lesson03.timecomplexity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * https://github.com/CuGBabyBeaR/Interview-questions/tree/94eaf7d8e366b3c0539116c69389325e4a61b9bc/twitter-puddle
 * 얼마나 많은 물이 벽 사이의 웅덩이에 쌓일 것인가? "
 * "In this picture we have walls of different heights. 
 * This picture is represented by an array of integers, where the value at each index is the height of the wall. 
 * The picture above is represented with an array as [2,5,1,2,3,4,7,7,6]."
 * "Now imagine it rains. 
 * How much water is going to be accumulated in puddles between walls?"
 * "We count volume in square blocks of 1X1. 
 * So in the picture above, everything to the left of index 1 spills out. 
 * Water to the right of index 7 also spills out. 
 * We are left with a puddle between 1 and 6 and the volume is 10."
 */
public class Puddles {
	
	private static Logger log = LoggerFactory.getLogger(Puddles.class);

	public static void main(String[] args) {
		int[] A = { 2, 5, 1, 2, 3, 4, 7, 7, 6 };
		int[] B = { 2, 5, 1, 2, 3, 4, 7, 7, 6, 7 };
		int[] C = { 2, 5, 1, 2, 3, 4, 7, 7, 6, 8 };

		log.debug("Case A, total volume : {}", puddles(A)); // return 10
		log.debug("Case B, total volume : {}", puddles(B));
		log.debug("Case C, total volume : {}", puddles(C));
	}
	
	private static int puddles(int[] A) {		
		int left = 0;
		int right = A.length - 1;
		int leftWall = A[left];
		int rightWall = A[right];

		int volume = 0;
		while (left < right) {
			if (leftWall < rightWall) {
				left++; // 왼쪽벽에서 오른쪽벽으로 
				if (leftWall <= A[left]) {
					leftWall = A[left]; // 채워진 물 없음.
				} else {
					volume += leftWall - A[left]; // 채워진 물있음.
				}
			} else {
				right--; // 오른쪽 벽에서 왼쪽벽으로
				if (rightWall <= A[right]) {
					rightWall = A[right];
				} else {
					volume += rightWall - A[right];
				}
			}
			//log.debug("leftWall={} rightWall={} volume={}", leftWall, rightWall, volume);
		}
		return volume;
	}
}
