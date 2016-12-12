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
		int[] B = { 2, 5, 1, 3, 1, 2, 1, 7, 7, 6 };
		int[] C = { 6, 1, 4, 6, 7, 5, 1, 6, 4 };

		log.debug("Case A, total volume : {}", puddles(A));
		//log.debug("Case B, total volume : {}", puddles(B));
		//log.debug("Case C, total volume : {}", puddles(C));
	}
	
	private static int puddles(int[] A) {
		int leftPosition = 0;
		int rightPosition = A.length - 1;
		int leftWall = A[leftPosition];
		int rightWall = A[rightPosition];

		int volume = 0;
		while (leftPosition < rightPosition) {
			if (leftWall < rightWall) {
				leftPosition++;
				if (A[leftPosition] >= leftWall) {
					leftWall = A[leftPosition];
				} else {
					volume += leftWall - A[leftPosition];
				}
			} else {
				rightPosition--;
				if (A[rightPosition] >= rightWall) {
					rightWall = A[rightPosition];
				} else {
					volume += rightWall - A[rightPosition];
				}
			}
			log.debug("leftWall={} rightWall={} volume={}", leftWall, rightWall, volume);
		}
		return volume;
	}

}
