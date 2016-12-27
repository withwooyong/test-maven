package algorithm.hanbit.hanbit07;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code7_04 {

	private static Logger log = LoggerFactory.getLogger(code7_04.class);
	private static int MAX_INT = 65536;
	private static int MAX = 100;

	private static int[] Buf = new int[MAX];
	private static int[] Data = new int[MAX];
	private static int NUM;

	// 실행안됨.
	public static void main(String[] args) {
		NUM = MAX;
		MakeRandomNumber();
		log.debug("{}", Arrays.toString(Buf));
		HeapSort(NUM);
		log.debug("{}", Arrays.toString(Buf));
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
			if (Buf[i] == number || number == 0)
				return true;
		}
		return false;
	}

	private static void HeapSort(int N) {
		for (int i = 0; i < MAX; i++) {
			InsertData(Data[i]);
		}
		Data[0] = 1;
		for (int i = MAX - 1; i >= 0; i--) {
			Data[i] = RemoveData();
		}
	}
	
	private static void InsertData(int v) {
		log.debug("{} {} {}", NUM, v, Buf.length);
		Buf[NUM++] = v;
		upheap(NUM);
	}

	private static void upheap(int k) {
		int v;
		v = Buf[k];
		Buf[0] = MAX_INT;

		while (Buf[k / 2] <= v) {
			Buf[k] = Buf[k / 2];
			k = k / 2;
		}
		Buf[k] = v;
	}

	private static void downheap(int k) {
		int i, v;
		v = Buf[k];

		while (k <= NUM / 2) {
			i = 2 * k;
			if (i < NUM && Buf[i] < Buf[i + 1]) {
				i++;
			}
			if (v >= Buf[i]) {
				break;
			}
			Buf[k] = Buf[i];
			k = i;
		}
		Buf[k] = v;
	}	

	private static int RemoveData() {
		int v = Buf[1];
		Buf[1] = Buf[NUM--];
		downheap(1);
		return v;
	}
}
