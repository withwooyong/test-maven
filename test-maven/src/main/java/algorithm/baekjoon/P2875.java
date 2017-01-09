package algorithm.baekjoon;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2875 {	
	
	private static Logger log = LoggerFactory.getLogger(P2875.class); 

	/*
	 * 대회 or 인턴 
	 * 백준대학교에서는 대회에 나갈 때 2명의 여학생과 1명의 남학생이 팀을 결성해서 나가는 것이 원칙이다.
	 * 백준대학교는 뛰어난 인재들이 많아 올해에도 N명의 여학생과 M명의 남학생이 팀원을 찾고 있다.
	 * 그런데 올해에는 대회에 참여하려는 학생들 중 K명을 반드시 인턴쉽 프로그램에 참여하라는 학교의 방침이 생기게 되었다.
	 * 백준대학교에서는 뛰어난 인재들이 많기 때문에, 많은 팀을 만드는 것이 최선이다.
	 * 여러분은 N명의 여학생과 M명의 남학생, K명의 인턴쉽에 참여해야하는 인원이 주어질 때 만들 수 있는 최대의 팀 수를 구하면 된다.
	 * 
	 * 첫째 줄에 N, M, K가 순서대로 주어진다. (0 ≤ M ≤ 100), (0 ≤ N ≤ 100), (0 ≤ K ≤ M+N),
	 * 만들 수 있는 팀의 최대값을 출력하면 된다.
	 * 6 3 2
	 * 
	 * 2
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * 2명의 여학생과 1명의 남학생이 팀을 결성해서 나가는 것이 원칙
	 * K명을 반드시 인턴쉽 프로그램에 참여하라는 학교의 방침이라 함은,
	 * 6 3 2 였을 경우, 여자 6명 남자 3명이 있지만 인턴쉽으로 2명이 빠져야한다.
	 * 그렇다면 어떤 경우에 인턴쉽 인원을 빼고 최대 인원을 만들 수 있는가? 그때의 최댓값은 얼마인가?
	 * 출처: http://www.crocus.co.kr/472 [Crocus]
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int N = 6; // 여학생
		int M = 3; // 남학생
		int K = 2; // 인턴
	    int cnt = 0;
	 
	    // 여학생 / 2가 0보다 크고, 남학생이 0보다 클 때
	    while ((N / 2) > 0 && M > 0) {
	    	N -= 2; // 여학생은 2명 빼주고
	    	M--; // 남자는 1명 빼주고
	        cnt++; // 카운트는 1 더해준다.
	    }
	 
	    M = M > 0 ? K -= M : 0; // 만약 남은 남학생 수가 있다면 k에서 빼준다.
	    N = N > 0 ? K -= N : 0; // 만약 남은 여학생 수가 있다면 k에서 빼준다.
	 
	    // 남은 k가 있거나 cnt가 0이 아닐때
	    // 애초에 위의 while에서 cnt가 0이었다면 바로 break
	    while (K > 0 && cnt != 0) {
	    	cnt--;
	    	K -= 3; // cnt -1당 k는 -3(여 2 남 1)
	    }
	    System.out.println(cnt);
	    sc.close();
	}
	
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 여학생
		int M = sc.nextInt(); // 남학생
		int K = sc.nextInt(); // 인턴
	    int cnt = 0;
	 
	    // 여학생 / 2가 0보다 크고, 남학생이 0보다 클 때
	    while ((N / 2) > 0 && M > 0) {
	    	N -= 2; // 여학생은 2명 빼주고
	    	M--; // 남자는 1명 빼주고
	        cnt++; // 카운트는 1 더해준다.
	    }
	    
	    if (M > 0) { // 만약 남은 남학생 수가 있다면 k에서 빼준다.
	    	K -= M;
		} 
	    
	    if (N > 0) { // 만약 남은 여학생 수가 있다면 k에서 빼준다.
	    	K -= N;
		}
	    
	    // 남은 k가 있거나 cnt가 0이 아닐때
	    // 애초에 위의 while에서 cnt가 0이었다면 바로 break
	    while (K > 0 && cnt != 0) {
	    	cnt--;
	    	K -= 3; // cnt -1당 k는 -3(여 2 남 1)
	    }
	    System.out.println(cnt);
	    sc.close();
	}
}
