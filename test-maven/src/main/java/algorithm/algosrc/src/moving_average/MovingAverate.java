package algorithm.algosrc.src.moving_average;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 4.3 이동평균구하기 movingAverage1
// 4.4 선형 시간에 이동 평균구하기 movingAverage2
public class MovingAverate {
	
	private static Logger log = LoggerFactory.getLogger(MovingAverate.class);

	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 2, 1, 0, 1, 2, 3 };
		double[] B = { 1.5, 2.5, 2.5, 1.5, 0.5, 0.5, 1.5, 2.5 };
		
		log.debug("{}", movingAverage1(A, 2));
		log.debug("{}", movingAverage2(A, 2));		

	}
	
	// 길이가 N 인 실수 벡터 A 가 주어질 때, 각 위치에서의 M-이동평균 값을 구한다
	public static ArrayList<Double> movingAverage1(int[] A, int M) {

		ArrayList<Double> ret = new ArrayList<>();
		int N = A.length;
		for (int i = M - 1; i < N; ++i) {
			// A[i] 까지의 이동평균 값을 구하자.
			double partialSum = 0;
			for (int j = 0; j < M; ++j) {
				partialSum += A[i - j];
			}
			ret.add(partialSum / M);
		}
		return ret;
	}

	// 길이가 N 인 실수 벡터 A 가 주어질 때, 각 위치에서의 M-이동평균 값을 구한다
	public static ArrayList<Double> movingAverage2(int[] A, int M) {
		ArrayList<Double> ret = new ArrayList<>();
		int N = A.length;
		double partialSum = 0;
		for (int i = 0; i < M - 1; ++i) {
			partialSum += A[i];
		}
		for (int i = M - 1; i < N; ++i) {
			partialSum += A[i];
			ret.add(partialSum / M);
			partialSum -= A[i - M + 1]; // partialSum -= A[i - 1]; 
			
		}
		return ret;
	}
}
