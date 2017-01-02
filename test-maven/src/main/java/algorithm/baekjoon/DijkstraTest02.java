package algorithm.baekjoon;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://manducku.tistory.com/29
 */
public class DijkstraTest02 {

	private static Logger log = LoggerFactory.getLogger(DijkstraTest02.class);

	static int[][] ad;
	static int[] dist;
	static boolean[] visit;
	static int nE, nV;
	static final int inf = 1000000;

	/*
	 * 다익스트라 알고리즘은 최단 경로를 찾는 알고리즘입니다. 가중치가 없는 그래프에 대한 최단 경로는 너비 우선 탐색으로 찾을 수 있기
	 * 때문에, 다이작스트라는 가중치가 있는 그래프 위에서의 최단 경로를 찾을 때 사용 됩니다. 다익스트라 알고리즘을 통해, 최단 거리를
	 * 찾기 위해선 몇 가지 필요한 변수들이 있습니다. visit 배열 : 해당 엣지를 방문했는지 여부 체크 인접 행렬 혹은 리스트 : 각
	 * 엣지별 weight를 저장하는 영역 distance 배열 : 해당 노드까지의 최단 거리를 저장하는 배열
	 * 
	 * 다익스트라 알고리즘의 매커니즘을 간단하게 정리하면, 아래와 같습니다. 체크되어 있지 않은 정점 중에서 D의 값이 가장 작은 정점
	 * x를 선택 x를 체크한다 x와 연결된 모든 정점을 검사한다. 간선을 (x,y,z)라고 했을 때 d[y] > d[x] + z이면 갱신
	 * 1,2,3 단계를 모든 정점을 체크할 때까지 계속한다.
	 */
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		nV = scan.nextInt();
		nE = scan.nextInt();

		ad = new int[nV + 1][nV + 1];
		dist = new int[nV + 1];
		visit = new boolean[nV + 1];

		for (int i = 1; i <= nV; i++) {
			dist[i] = inf; // 임의의 무한한 값으로 거리값 초기화
		}

		for (int i = 0; i < nE; i++) {
			int t1, t2, t3;
			t1 = scan.nextInt();
			t2 = scan.nextInt();
			t3 = scan.nextInt();

			ad[t1][t2] = t3;
		}
		ssp(1, 5);
	}

	public static void ssp(int start, int end) {
		dist[start] = 0; 				// 최초 시작 값 distance 초기화
		String s = "";
		for (int j = 0; j < nV; j++) { 	// dist 값을 한번 초기화 했으므로 n-1번만 진행
			int min = inf + 1; 			// dist 최소값 찾기 위한 임시 값 초기화
			int index = -1;
			for (int k = 1; k <= nV; k++) {
				if (visit[k] == false && min > dist[k]) {
					min = dist[k];
					index = k;
				}
			}
			visit[index] = true;
			s += index + " "; // 경로를 체크하는 방법

			for (int l = 1; l <= nV; l++) {
				// 인접 행렬을 검사하여 최적의 값을 찾아
				if (ad[index][l] != 0 && dist[l] > dist[index] + ad[index][l]) {
					dist[l] = dist[index] + ad[index][l];
				}
			}
		}

		for (int i = 1; i <= nV; i++) {
			System.out.print(dist[i]);
		}
		System.out.println();
		System.out.println(s);
	}

}
