package algorithm.baekjoon.middle2.C_graph2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1956 {	
	
	private static Logger log = LoggerFactory.getLogger(P1956.class); 

	/*
	 * 운동
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	1098	508	390	44.470%
문제
V개의 마을와 E개의 도로로 구성되어 있는 도시가 있다. 도로는 마을과 마을 사이에 놓여 있으며, 일방 통행 도로이다. 마을에는 편의상 1번부터 V번까지 번호가 매겨져 있다고 하자.

당신은 도로를 따라 운동을 하기 위한 경로를 찾으려고 한다. 운동을 한 후에는 다시 시작점으로 돌아오는 것이 좋기 때문에, 우리는 사이클을 찾기를 원한다. 단, 당신은 운동을 매우 귀찮아하므로, 사이클을 이루는 도로의 길이의 합이 최소가 되도록 찾으려고 한다.

도로의 정보가 주어졌을 때, 도로의 길이의 합이 가장 작은 사이클을 찾는 프로그램을 작성하시오. 두 마을을 왕복하는 경우도 사이클에 포함됨에 주의한다.

입력
첫째 줄에 V와 E가 빈칸을 사이에 두고 주어진다. (2<=V<=400, 0<=E<=V*(V-1)) 다음 E개의 줄에는 각각 세 개의 정수 a, b, c가 주어진다. a번 마을에서 b번 마을로 가는 거리가 c인 도로가 있다는 의미이다. (a->b임에 주의) 거리는 10,000 이하의 자연수이다.

출력
첫째 줄에 최소 사이클의 도로 길이의 합을 출력한다. 운동 경로를 찾는 것이 불가능한 경우에는 -1을 출력한다.

예제 입력  복사
3 4
1 2 1
3 2 1
1 3 5
2 3 2
예제 출력  복사
3
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/3288251c4d2d98301be7
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] d = new int[n][n];
        while (m-- > 0) {
            int x = sc.nextInt();
            x -= 1;
            int y = sc.nextInt();
            y -= 1;
            int z = sc.nextInt();
            if (d[x][y] == 0) {
                d[x][y] = z;
            } else if (d[x][y] > z) {
                d[x][y] = z;
            }
        }
        for (int k=0; k<n; k++) {
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    if (d[i][k] != 0 && d[k][j] != 0) {
                        if (d[i][j] == 0 || d[i][j] > d[i][k] + d[k][j]) {
                            d[i][j] = d[i][k]+d[k][j];
                        }
                    }
                }
            }
        }
        int ans = -1;
        for (int i=0; i<n; i++) {
            if (d[i][i] != 0) {
                if (ans == -1 || ans > d[i][i]) {
                    ans = d[i][i];
                }
            }
        }
        System.out.println(ans);
		
		sc.close();
	}
}
