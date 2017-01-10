package algorithm.baekjoon.middle1.A_greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1744 {	
	
	private static Logger log = LoggerFactory.getLogger(P1744.class); 

	/*
	 * 수 묶기
	 * 문제
길이가 N인 수열이 주어졋을 때, 그 수열의 합을 구하려고 한다. 하지만, 그냥 그 수열의 합을 모두 더해서 구하는 것이 아니라, 수열의 어떤 인접한 원소끼리를 묶으려고 한다. 어떤 수를 묶으려고 할 때, 위치에 상관없이 묶을 수 있다. 하지만, 같은 위치에 있는 수(자기 자신)를 묶는 것은 불가능하다. 그리고 어떤 수를 묶게 되면, 수열의 합을 구할 때, 묶은 수는 서로 곱한 후에 더한다.

예를 들면, 어떤 수열이 {0, 1, 2, 4, 3, 5}일 때, 그냥 이 수열의 합을 구하면 0+1+2+4+3+5 = 15이다. 하지만, 2와 3을 묶고, 4와 5를 묶게 되면, 0+1+(2*3)+(4*5) = 27이 되어 최대가 된다.

수열의 모든 수는 단 한번만 묶거나, 아니면 묶지 않아야한다.

수열이 주어졌을 때, 수열의 각 수를 적절히 묶었을 때, 그 합이 최대가 되게 하는 프로그램을 작성하시오.

입력
첫째 줄에 수열의 크기 N이 주어진다. N은 10,000보다 작다. 둘째 줄부터 N개의 줄에, 수열의 각 수가 주어진다. 수열의 수는 -10,000보다 크거나 같고, 10,000보다 작거나 같은 정수이다.

출력
수를 적절히 묶어 그 합이 최대값을 출력한다. 정답은 항상 231보다 작다.

4
-1
2
1
3

6
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/4a24036b064f62f55d9d44d951e500e0
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> plus = new ArrayList<Integer>();
        ArrayList<Integer> minus = new ArrayList<Integer>();
        int zero = 0;
        int one = 0;
        for (int i=0; i<n; i++) {
            int x = sc.nextInt();
            if (x == 1) {
                one += 1;
            } else if (x > 0) {
                plus.add(x);
            } else if (x < 0) {
                minus.add(x);
            } else {
                zero += 1;
            }
        }
        Collections.sort(plus);
        Collections.sort(minus);
        Collections.reverse(plus);
        if (plus.size() % 2 == 1) {
            plus.add(1);
        }
        if (minus.size() % 2 == 1) {
            minus.add(zero > 0 ? 0 : 1);
        }
        int ans = one;
        for (int i=0; i<plus.size(); i+=2) {
            ans += plus.get(i) * plus.get(i+1);
        }
        for (int i=0; i<minus.size(); i+=2) {
            ans += minus.get(i) * minus.get(i+1);
        }
        System.out.println(ans);
		
		sc.close();
	}
}
