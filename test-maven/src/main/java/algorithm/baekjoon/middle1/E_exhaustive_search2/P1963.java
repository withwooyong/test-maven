package algorithm.baekjoon.middle1.E_exhaustive_search2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1963 {

	private static Logger log = LoggerFactory.getLogger(P1963.class);

	/*
	 * 소수 경로
	 * 한국어 원문 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 2 초 128 MB 2045 1035 738 52.979%
	 * 문제
	 * 소수를 유난히도 좋아하는 창영이는 게임 아이디 비밀번호를 4자리 ‘소수’로 정해놓았다. 어느 날 창영이는 친한 친구와 대화를 나누었는데:
	 * 
	 * “이제 슬슬 비번 바꿀 때도 됐잖아”
	 * “응 지금은 1033으로 해놨는데... 다음 소수를 무엇으로 할지 고민중이야"
	 * “그럼 8179로 해”
	 * “흠... 생각 좀 해볼게. 이 게임은 좀 이상해서 비밀번호를 한 번에 한 자리 밖에 못 바꾼단 말이야. 예를 들어 내가 첫 자리만 바꾸면 8033이 되니까 소수가 아니잖아. 여러 단계를 거쳐야 만들 수
	 * 있을 것 같은데... 예를 들면... 1033 1733 3733 3739 3779 8779 8179처럼 말이야.”
	 * “흠...역시 소수에 미쳤군. 그럼 아예 프로그램을 짜지 그래. 네 자리 소수 두 개를 입력받아서 바꾸는데 몇 단계나 필요한지 계산하게 말야.”
	 * “귀찮아”
	 * 그렇다. 그래서 여러분이 이 문제를 풀게 되었다. 입력은 항상 네 자리 소수만(1000 이상) 주어진다고 가정하자. 주어진 두 소수 A에서 B로 바꾸는 과정에서도 항상 네 자리 소수임을 유지해야 하고,
	 * ‘네 자리 수’라 하였기 때문에 0039 와 같은 1000 미만의 비밀번호는 허용되지 않는다.
	 * 
	 * 입력
	 * 첫 줄에 test case의 수 T가 주어진다. 다음 T줄에 걸쳐 각 줄에 1쌍씩 네 자리 소수가 주어진다.
	 * 
	 * 출력
	 * 각 test case에 대해 두 소수 사이의 변환에 필요한 최소 회수를 출력한다. 불가능한 경우 Impossible을 출력한다.
	 * 
	 * 예제 입력 복사
	 * 3
	 * 1033 8179
	 * 1373 8017
	 * 1033 1033
	 * 예제 출력 복사
	 * 6
	 * 7
	 * 0
	 */
	public static void main(String[] args) throws Exception {
		//solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/d815d8eae0b586537a02
	 
	private static void solution() {
		public static int change(int num, int index, int digit) {
	        if (index == 0 && digit == 0) {
	            return -1;
	        }
	        String s = Integer.toString(num);
	        StringBuilder sb = new StringBuilder(s);
	        sb.setCharAt(index, (char)(digit + '0'));
	        return Integer.parseInt(sb.toString());
	    }
	    public static void main(String args[]) {
	        Scanner sc = new Scanner(System.in);
	        boolean[] prime = new boolean[10001];
	        for (int i=2; i<=10000; i++) {
	            if (prime[i] == false) {
	                for (int j=i*i; j<=10000; j+=i) {
	                    prime[j] = true;
	                }
	            }
	        }
	        for (int i=0; i<=10000; i++) {
	            prime[i] = !prime[i];
	        }
	        int t = sc.nextInt();
	        while (t-- > 0) {
	            int n = sc.nextInt();
	            int m = sc.nextInt();
	            boolean[] c = new boolean[10001];
	            int[] d = new int[10001];
	            d[n] = 0;
	            c[n] = true;
	            Queue<Integer> q = new LinkedList<Integer>();
	            q.add(n);
	            while (!q.isEmpty()) {
	                int now = q.remove();
	                for (int i=0; i<4; i++) {
	                    for (int j=0; j<=9; j++) {
	                        int next = change(now, i, j);
	                        if (next != -1) {
	                            if (prime[next] && c[next] == false) {
	                                q.add(next);
	                                d[next] = d[now] + 1;
	                                c[next] = true;
	                            }
	                        }
	                    }
	                }
	            }
	            System.out.println(d[m]);
	        }
	    }
		
		sc.close();
	}
	*/
}
