package codingdojang;

import java.util.Scanner;

/*
 * 한 친구가 방금 새 컴퓨터를 샀다. 
 * 그 친구가 지금까지 샀던 가장 강력한 컴퓨터는 공학용 전자 계산기였다. 
 * 그런데 그 친구는 새 컴퓨터의 모니터보다 공학용 계산기에 있는 LCD 디스플레이가 더 좋다며 크게 실망하고 말았다. 
 * 그 친구를 만족시킬 수 있도록 숫자를 LCD 디스플레이 방식으로 출력하는 프로그램을 만들어보자.
 * 
 * 입력
 * 입력 파일은 여러 줄로 구성되며 표시될 각각의 숫자마다 한 줄씩 입력된다. 
 * 각 줄에는 s와 n이라는 두개의 정수가 들어있으며 
 * n은 출력될 숫자( 0<= n <= 99,999,999 ), 
 * s는 숫자를 표시하는 크기( 1<= s < 10 )를 의미한다. 
 * 0 이 두 개 입력된 줄이 있으면 입력이 종료되며 그 줄은 처리되지 않는다.
 * 
 * 출력
 * 입력 파일에서 지정한 숫자를 수평 방향은 '-' 기호를, 수직 방향은 '|'를 이용해서 LCD 디스플레이 형태로 출력한다. 
 * 각 숫자는 정확하게 s+2개의 열, 2s+3개의 행으로 구성된다. 
 * 마지막 숫자를 포함한 모든 숫자를 이루는 공백을 스페이스로 채워야 한다. 
 * 두 개의 숫자 사이에는 정확하게 한 열의 공백이 있어야 한다.
 * 
 * 각 숫자 다음에는 빈 줄을 한 줄 출력한다. 밑에 있는 출력 예에 각 숫자를 출력하는 방식이 나와있다.
 * 
 * 예
 * 입력 예
 * 2 12345
 * 3 67890
 * 0 0
 */
public class LCDDisplay {

	public static void main(String[] args) {
		int N = 20;// Input Limit
		String[] hori = { "02356789", "2345689", "2356890" };
		String[][] verti = { { "045689", "01234789" }, { "0268", "013456789" } };

		Scanner in = new Scanner(System.in);
		int[] s = new int[N];
		String[] n = new String[N];
		int L = -1, l, c, i, j, t, v;
		do {
			s[++L] = in.nextInt();
			n[L] = in.next();
		} while (s[L] != 0 && n[L] != "0"); // 입력받음

		for (l = 0; l < L; l++) { // 입력한 줄 한줄씩
			for (t = 0; t < 3; t++) {
				for (c = 0; c < n[l].length(); c++) {
					System.out.print(" "); // 가로선 ~ c:한 숫자씩
					for (i = 0; i < s[l]; i++)
						System.out.print((hori[t].contains(n[l].charAt(c) + "")) ? "-" : " ");
					System.out.print("  ");
				}
				System.out.println();
				if (t < 2)
					for (j = 0; j < s[l]; j++) { // 세로선
						for (c = 0; c < n[l].length(); c++) {
							for (v = 0; v < 2; v++) {
								System.out.print((verti[t][v].contains(n[l].charAt(c) + "")) ? "|" : " ");
								if (v == 0)
									for (i = 0; i < s[l]; i++)
										System.out.print(" ");
								else
									System.out.print(" ");
							}
						}
						System.out.println();
					}
			}
			System.out.println();
		}

	}

}
