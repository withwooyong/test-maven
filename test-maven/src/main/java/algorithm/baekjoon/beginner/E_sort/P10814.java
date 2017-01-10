package algorithm.baekjoon.beginner.E_sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P10814 {	
	
	private static Logger log = LoggerFactory.getLogger(P10814.class); 

	/*
	 * 나이순 정렬
	 * 문제
온라인 저지에 가입한 사람들의 나이와 이름이 가입한 순서대로 주어진다. 이 때, 회원들을 나이가 증가하는 순으로, 나이가 같으면 먼저 가입한 사람이 앞에 오는 순서로 정렬하는 프로그램을 작성하시오.

입력
첫째 줄에 온라인 저지 회원의 수 N이 주어진다. (1 ≤ N ≤ 100,000)

둘째 줄부터 N개의 줄에는 각 회원의 나이와 이름이 공백으로 구분되어 주어진다. 나이는 1보다 크거나 같으며, 200보다 작거나 같은 정수이고, 이름은 알파벳 대소문자로 이루어져 있고, 길이가 100보다 작거나 같은 문자열이다. 입력은 가입한 순서로 주어진다.

출력
첫째 줄부터 총 N개의 줄에 걸쳐 온라인 저지 회원을 나이 순, 나이가 같으면 가입한 순으로 한 줄에 한 명씩 나이와 이름을 공백으로 구분해 출력한다.

3
21 Junkyu
21 Dohyun
20 Sunyoung

20 Sunyoung
21 Junkyu
21 Dohyun
	 */
	public static void main(String[] args) throws Exception {
		
	}

	/*
	 * https://gist.github.com/Baekjoon/633c9eaf24cd4606ef01
	 */
	private static void solution() {
		
	}
}

class Person {
	int age;
	String name;
	int join;

	Person(int age, String name, int join) {
		this.age = age;
		this.name = name;
		this.join = join;
	}
}
