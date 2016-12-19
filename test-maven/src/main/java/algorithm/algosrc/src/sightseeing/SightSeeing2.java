package algorithm.algosrc.src.sightseeing;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 6.7 여행하는 외판원 문제를 해결하는 재귀호출 알고리즘
public class SightSeeing2 {
	
	private static Logger log = LoggerFactory.getLogger(SightSeeing2.class);
	
	private static int n = 4; // 방문할 수 있는 곳의 개수
	private static int[][] distance = new int[10][10];
	
	public static void main(String[] args) {
		// 모든 관광지 간의 소요시간
		
		int[][] weights = { { 0, 4, 7, 2 }, { 4, 0, 3, 6 }, { 7, 3, 0, 8 }, { 2, 6, 8, 0 } };
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				distance[i][j] = weights[i][j];
			}
		}
		
		boolean[] seen = new boolean[4];
		log.debug("{}", shortestPath(0, seen, 0, 2)); // 7
		log.debug("{}", shortestPath(1, seen, 0, 2)); // 6
		log.debug("{}", shortestPath(2, seen, 0, 2)); // 7
		log.debug("{}", shortestPath(3, seen, 0, 2)); // 6
		log.debug("==========================");
		log.debug("{}", visitM(3)); // 6
		log.debug("{}", visitM(2)); // 2
		log.debug("{}", visitM(1)); // 0
		log.debug("==========================");
		for(int i = 3; i >= 1; --i) {
			log.debug("{} {}", visitM(i), shortestPath2(-1, seen, 0, i));
		}
	}
	
	// n 개의 관광지 중 m 개를 방문할 수 있는 최단 시간을 반환한다.
	private static int visitM(int m) {
		int ret = Integer.MAX_VALUE;
		// 모든 시작점에 대해 shortestPath() 를 호출해 보고 최소값을 반환한다
		for (int start = 0; start < n; start++) {
			boolean[] visited = new boolean[n];
			ret = Math.min(ret, shortestPath(start, visited, 0, m - 1));
		}
		return ret;
	}
	
	// 지금 만들 수 있는 경로 중 가장 짧은 것의 길이를 반환한다.
	// here: 현재 위치
	// visited: 각 관광지별 방문 여부
	// curLength: 지금까지 따라온 경로의 길이
	// toPick: 더 방문해야 할 관광지의 수
	private static int shortestPath(int here, boolean[] visited, int curLength, int toPick) {
		// 기저 사례: 이미 경로가 완성되었을 때 현재 길이를 그냥 반환
		if (toPick == 0) {
			return curLength;
		}
		visited[here] = true;
		// 그 외: 다음에 방문할 정점을 결정하고 그 중 가장 작은 것을 반환
		int ret = Integer.MAX_VALUE;
		for (int next = 0; next < n; next++) {
			if (!visited[next]) {
				int cand = shortestPath(next, visited, curLength + distance[here][next], toPick - 1);
				ret = Math.min(ret, cand);
			}
		}
		visited[here] = false;
		return ret;
	}

	// 지금 만들 수 있는 경로 중 가장 짧은 것의 길이를 반환한다.
	// here: 현재 위치
	// visited: 각 관광지별 방문 여부
	// curLength: 지금까지 따라온 경로의 길이
	// toPick: 더 방문해야 할 관광지의 수
	private static int shortestPath2(int here, boolean[] visited, int curLength, int toPick) {
		// 기저 사례: 이미 경로가 완성되었을 때 현재 길이를 그냥 반환
		if (toPick == 0) {
			return curLength;
		}
		if (here != -1) {
			visited[here] = true;
		}
		// 그 외: 다음에 방문할 정점을 결정하고 그 중 가장 작은 것을 반환
		int ret = Integer.MAX_VALUE;
		for (int next = 0; next < n; next++) {
			if (visited[next] == false) {
				int nextLength = curLength;
				if (here != -1) {
					nextLength += distance[here][next];
				}
				int cand = shortestPath(next, visited, nextLength, toPick - 1);
				ret = Math.min(ret, cand);
			}
		}
		if (here != -1) {
			visited[here] = false;
		}
		return ret;
	}	
}
