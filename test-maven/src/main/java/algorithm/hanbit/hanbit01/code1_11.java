package algorithm.hanbit.hanbit01;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code1_11 {

	private static Logger log = LoggerFactory.getLogger(code1_11.class);

	private static int[] Array = new int[100];

	public static void main(String[] args) {
		int ret;
		Init();
		ret = MaxSum(10);
		log.debug("ret : {}", ret);
	}

	private static void Init() {
		for (int i = 0; i < 100; i++) {
			Array[i] = 100 - i;
		}
		log.debug("{}", Arrays.toString(Array));
	}

	/*
	 * 100개 배열에서 10개고른 최대합
	 */
	private static int MaxSum(int N) {
		int Sum = 0;
		int Max = 0;

		for (int i = 0; i < N; i++) {
			Sum = 0;
			for (int j = i; j < N; j++) {
				Sum += Array[j];
				if (Sum > Max) {
					Max = Sum;
				}
			}
		}
		return Max;
	}

}
