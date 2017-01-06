package algorithm.baekjoon;

import java.util.Scanner;

/*
 * 너비 우선 탐색(BFS, Breadth First Search) 큐사용
 * 깊이 우선 탐색(DFS)과는 달리 스택을 이용하지 않고 큐를 이용하며, 
 * 너비 우선 탐색도 트리나 그래프에서의 탐색에 사용되는 알고리즘입니다. 
 * 이 너비 우선 탐색은 깊이가 1인 모든 정점을 방문하고 나서, 그 다음에는 깊이가 2인 모든 정점을, 
 * 깊이가 3인 모든 정점을 방문하는 식으로 계속 방문하다가 더이상 방문할 곳이 없으면 탐색을 마칩니다. 
 * 너비 우선 탐색은 깊이 우선 탐색과는 다르게 무작정 막힐때까지 탐색을 진행하는게 아니라, 
 * 이곳저곳 살펴보면서 탐색을 진행하는 것이라고 할 수 있습니다.
 *  
 * 깊이우선탐색 (DFS Depth First Search) 스택사용
 * 일반적으로 이 DFS 알고리즘을 구현할때는 스택을 이용하고, 
 * 트리 혹은 그래프 같은 자료구조에서 데이터를 탐색할 때 사용하는 알고리즘 입니다. 
 * 
 * 깊이 우선 탐색 (Depth First Search)
 * 현재 정점에 연결된 정점이 존재하면 계속 이동한다.
 * 방문한 정점을 재귀호출을 이용해서 스택에 저장한다.
 * 재귀호출을 이용하면 할수록 깊은 정점의 위치에 들어간다.
 * 너무 깊게 들어가면 overflow 발생한다.
 * 막히면 나아갈 곳이 있는 곳으로 돌아간다 -> 백트래킹
 * 유용 : 사이클 탐지(Cycle Detection), 위상 정렬(Topological Sorting)
 * 장점 : 무한히 넓은 트리에 효과적이다.
 * 단점 : 목표 노드가 없는 경로에 깊이 빠질 수 있다.
 * 
 * 너비 우선 탐색 (Breadth First Search)
 * 생성된 순서에 따라 노드를 확장한다.
 * 시작 정점을 출발로 큐 구조에 방문한 정점을 넣어가며 탐색을 한다.
 * 한 정점에 연결된 모든 정점의 탐색이 끝나면 그 정점을 큐에서 제가한다.
 * 그리고 큐의 다음 정점을 꺼내서 그 정점에 연결된 모든 정점을 탐색한다.
 * 많은 기억 공간이 필요하다.
 * 유용 : 최소신장트리(Minimum Spanning Trees), 최단경로(Shortest Paths)
 * 장점 : 무한히 깊거나 무한에 가까운 트리인 경우에 효과적이다.
 * 단점 : 목표 노드로 가는 경로들 모두가 같은 거리일 때 비효율적이다.
 */
public class BFS_DFS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		solution(); // dfs

	}
	
	private static int nE; // Edge 정점
	private static int nV; // 
	private static int[][] ad;
	private static boolean[] visit;
	
	public static void solution() {
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
