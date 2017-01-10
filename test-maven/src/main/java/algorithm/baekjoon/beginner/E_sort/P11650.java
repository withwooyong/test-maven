package algorithm.baekjoon.beginner.E_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11650 {	
	
	private static Logger log = LoggerFactory.getLogger(P11650.class); 

	/*
	 * 좌표 정렬하기
	 * 문제
	 * 2차원 평면 위의 점 N개가 주어진다. 
	 * 좌표를 x좌표가 증가하는 순으로, x좌표가 같으면 y좌표가 증가하는 순서로 정렬한 다음 출력하는 프로그램을 작성하시오.
	 * 
	 * 입력
	 * 첫째 줄에 점의 개수 N (1 ≤ N ≤ 100,000)이 주어진다. 
	 * 둘째 줄부터 N개의 줄에는 i번점의 위치 xi와 yi가 주어진다. 
	 * (-100,000 ≤ xi, yi ≤ 100,000) 좌표는 항상 정수이고, 위치가 같은 두 점은 없다.
	 * 
	 * 출력
	 * 첫째 줄부터 N개의 줄에 점을 정렬한 결과를 출력한다.
	 * 

5
3 4
1 1
1 -1
2 2
3 3

1 -1
1 1
2 2
3 3
3 4
	 */
	static PriorityQueue<Point> q = new PriorityQueue<Point>(new ComparatorAscending());
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine().trim());
		Point point;
		String s[] = new String[2];
		for (int i = 0; i < t; i++) {
			s = br.readLine().split(" ");
			int x = Integer.parseInt(s[0]);
			int y = Integer.parseInt(s[1]);
			point = new Point(x, y);
			q.add(point);
		}
		for (int i = 0; i < t; i++) {
			Point pp = q.poll();
			sb.append(pp.x + " " + pp.y + "\n");
		}
		System.out.println(sb.toString());
		
	}

	/*
	 * https://gist.github.com/Baekjoon/c6c920ca4fd31eb9960e
	 * https://gist.github.com/Baekjoon/f3d180e438e2087a5a97
	 * https://gist.github.com/Baekjoon/180de19581f0b55c433c
	 * 출처: http://mygumi.tistory.com/58 [마이구미의 HelloWorld]
	 */
}

class ComparatorAscending implements Comparator<Point> {
	@Override
	public int compare(Point p1, Point p2) {
		if (p1.y < p2.y) {
			return -1;
		} else if (p1.y == p2.y) {
			if (p1.x < p2.x) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}
}

class Point {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

