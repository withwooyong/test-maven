package algorithm.baekjoon.middle1.A_greedy;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1783 {	
	
	private static Logger log = LoggerFactory.getLogger(P1783.class); 

	/*
	 * 병든 나이트
	 * 병든 나이트가 N * M 크기 체스판의 가장 왼쪽아래 칸에 위치해 있다. 
	 * 병든 나이트는 건강한 보통 체스의 나이트와 다르게 4가지로만 움직일 수 있다.
	 * 2칸 위로, 1칸 오른쪽
	 * 1칸 위로, 2칸 오른쪽
	 * 1칸 아래로, 2칸 오른쪽
	 * 2칸 아래로, 1칸 오른쪽
	 * 병든 나이트는 병들었지만, 그래도 명색이 체스 나이트이기 때문에 많은 칸을 방문하고 싶어 한다. 
	 * 병든 나이트의 이동에는 제약이 있다. 
	 * 만약, 이동 횟수가 4번 이상인 경우에는 위의 이동 방법을 각각 한 번씩 이용해야 한다.
	 * 체스판의 크기가 주어졌을 때, 병든 나이트가 방문할 수 있는 칸의 최대 개수를 출력하는 프로그램을 작성하시오. 
	 * 처음에 있는 칸도 센다.
	 * 
	 * 첫째 줄에 체스판의 세로 길이 N과, 둘째 줄에 체스판의 가로 길이 M이 주어진다. 
	 * N과 M은 2,000,000,000보다 작거나 같은 자연수이다.
	 * 
	 * 병든 나이트가 방문할 수 있는 칸의 개수중 최대값을 출력한다.
	 * 100 50
	 * 
	 * 48
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/980cd1791d8ee1784bf0af9b8d51dd11
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int height = sc.nextInt();
        int width = sc.nextInt();
        if (height == 1) {
            System.out.println(1);
        } else if (height == 2) {
            System.out.println(Math.min(4, (width+1)/2));
        } else if (height >= 3) {
            if (width >= 7) {
                System.out.println(width-2);
            } else {
                System.out.println(Math.min(4, width));
            }
        }
        System.out.println();
		
		sc.close();
	}
}
