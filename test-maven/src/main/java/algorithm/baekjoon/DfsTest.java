package algorithm.baekjoon;

import java.util.Scanner;

/*
 * 깊이우선탐색 (DFS Depth First Search)
 * 간단하게 이야기하면 갈수있을 때까지 간다입니다.
 * 즉, 현재 정점과 인접한 간선들을 하나씩 검사하다가, 아직 방문하지 않은 정점으로 향하는 간선이 있다면 그 간선을 무조건 따라 갑니다.
 * 이 과정에서 더이상 갈 곳이 없는 막힌 정점에 도달하면 포기하고, 마지막에 따라왔던 간선을 따라 뒤로 돌아가면서 탐색이 이루어 집니다.
 * 출처: http://manducku.tistory.com/23 [Manducku`s Code]
 * 
 * 양방향 그래프를 인접행렬과 인접리스트로 구현한 소스
 */
public class DfsTest {

	private static int nE; // Edge 정점
	private static int nV; // 
	private static int[][] ad;
	private static boolean[] visit;	

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		nV = scan.nextInt();
		nE = scan.nextInt();
		ad = new int[nV + 1][nV + 1]; // 변수 초기화
		visit = new boolean[nV + 1]; // 변수 초기화

		for (int i = 0; i < nE; i++) {
			int t1 = scan.nextInt();
			int t2 = scan.nextInt();

			ad[t1][t2] = ad[t2][t1] = 1;
		}
		dfs(1); // 1번부터 탐색 시작
	}
	
	// 재귀함수는 스택으로 구현되어 있다.
	public static void dfs(int i) {
		visit[i] = true; // 함수 호출 시, visit 했음을 표시
		System.out.print(i + " ");

		for (int j = 1; j < nV + 1; j++) {
			if (ad[i][j] == 1 && visit[j] == false) { // j를 방문하지 않았으면 j를 방문한다.
				dfs(j);
			}
		}
	}
}
