package algorithm.hanbit.hanbit09;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code9_03 {
	
	private static Logger log = LoggerFactory.getLogger(code9_03.class);
	
	private static int MAX = 100;
	private static int[] Buf = new int[MAX];
	private static ArrayList<Hit> hits = new ArrayList<>();
	

	public static void main(String[] args) {
		// private static NODE Hit[50];
		Initialize();
		MakeRandomNumber();
		log.debug("{}", hits.toString());		

	}
	
	private static void Initialize() {

		for (int i = 0; i < MAX; i++) {
			Buf[i] = -1;
		}

		for (int i = 0; i < MAX; i++) {
			Hit hit = new Hit();
			hit.setKey(-1);
			hit.setCounter(0);
			hits.add(hit);
		}
	}

	private static void MakeRandomNumber()	{
		int i, Num, index;
		i = 0;
		while (i < 50) {
			Num = (int) (Math.random() * MAX);
			Buf[Num] = Num;

			index = Num % 50;
			hits.get(index).setKey(Num);
			hits.get(index).setCounter(hits.get(index).getCounter() + 1);
			i++;
		}
	}
}
