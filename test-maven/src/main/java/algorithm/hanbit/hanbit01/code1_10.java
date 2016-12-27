package algorithm.hanbit.hanbit01;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code1_10 {

	private static Logger log = LoggerFactory.getLogger(code1_10.class);

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

	private static int MaxSum(int N) {
		int Sum = 0;
		int Max = 0;

		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				Sum = 0;

				for (int k = i; k < j + 1; k++) {
					Sum += Array[k];
				}

				if (Sum > Max) {
					Max = Sum;
				}
			}
		}
		return Max;
	}

}
