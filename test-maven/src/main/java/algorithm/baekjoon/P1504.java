package algorithm.baekjoon;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1504 {

	private static Logger log = LoggerFactory.getLogger(P1504.class);

	/*
	 * 방향성이 없는 그래프가 주어진다. 
	 * 세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다. 
	 * 또한 세준이는 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 
	 * 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다.
	 * 세준이는 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다. 
	 * 하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라. 
	 * 1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.
	 * 
	 * 첫째 줄에 정점의 개수 N과 간선의 개수 E가 주어진다. (2<=N<=800, 0<=E<=200,000) 
	 * 둘째 줄부터 E개의 줄에 걸쳐서 세 개의 정수 a, b, c가 주어지는데, 
	 * a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c라는 뜻이다. (1<=c<=1,000) 
	 * 다음 줄에는 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호가 주어진다.
	 */
	public static void main(String[] args) throws Exception {
		// (2<=N<=800, 0<=E<=200,000) 
		int N = 4; // 정점의 개수
		int E = 6; // 간선의 개수
		int a;
		int b;
		int c; // (1<=c<=1,000) 
		int[][] arr = {
				{ 1, 2, 3 }
			   ,{ 2, 3, 3 }
			   ,{ 3, 4, 1 }
			   ,{ 1, 3, 5 }
			   ,{ 2, 4, 5 }
			   ,{ 1, 4, 4 }
		};
		int point1 = 2;
		int point2 = 3;
		
		solution();
	}

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		    
	}
}
