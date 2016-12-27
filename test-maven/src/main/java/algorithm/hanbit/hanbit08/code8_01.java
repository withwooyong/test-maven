package algorithm.hanbit.hanbit08;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code8_01 {

	private static Logger log = LoggerFactory.getLogger(code8_01.class);

	private static int MAX = 100;
	private static int[] Buf = new int[MAX];
	private static int CompareCount;

	// 실행안됨.
	public static void main(String[] args) {
		int ret;
		CompareCount = 0;
		MakeRandomNumber();
		QuickSort(Buf, 0, MAX - 1);
		log.debug("{}", Arrays.toString(Buf));

		ret = BinarySearch(23);

		if (ret == -1) {
		} else {
			log.debug("{}", ret);
		}
		log.debug("{}", CompareCount);
	}

	private static void QuickSort(int data[], int left, int right) {
		int num, i, j, temp;
		if (right > left) {
			num = data[right];
			i = left - 1;
			j = right;

			for (;;) {
				while (data[++i] < num)
					;
				while (data[--j] > num)
					;
				if (i >= j)
					break;

				temp = data[i];
				data[i] = data[j];
				data[j] = temp;
			}

			temp = data[i];
			data[i] = data[right];
			data[right] = temp;

			QuickSort(data, left, i - 1);
			QuickSort(data, i + 1, right);
		}
	}

	private static int BinarySearch(int num) {
		int left, right;
		int min;
		left = 0;
		right = MAX;

		while (right >= left) {
			CompareCount++;
			min = (left + right) / 2;

			if (num < Buf[min]) {
				right = min - 1;
			} else {
				left = min + 1;
			}

			if (num == Buf[min]) {
				return min;
			}
		}
		return -1;
	}

	private static void MakeRandomNumber() {
		int i, Num;
		i = 1;
		Buf[0] = 100;

		while (i < MAX) {
			Num = (int) (Math.random() * MAX);
			if (!IsNumberExit(Num, i)) {
				Buf[i] = Num;
				i++;
			}
		}
	}

	private static boolean IsNumberExit(int number, int index) {
		for (int i = 0; i < index; i++) {
			if (Buf[i] == number || number == 0) {
				return true;
			}
		}
		return false;
	}

}
