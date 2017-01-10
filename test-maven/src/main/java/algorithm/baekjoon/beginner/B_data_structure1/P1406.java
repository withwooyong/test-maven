package algorithm.baekjoon.beginner.B_data_structure1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1406 {

	private static Logger log = LoggerFactory.getLogger(P1406.class);

	/*
	 * 에디터
	 * 한 줄로 된 간단한 에디터를 구현하려고 한다. 이 편집기는 영어 소문자만을 기록할 수 있는 편집기로, 최대 600,000글자까지 입력할 수 있다.

이 편집기에는 '커서'라는 것이 있는데, 커서는 문장의 맨 앞(첫 번째 문자의 왼쪽), 문장의 맨 뒤(마지막 문자의 오른쪽), 또는 문장 중간 임의의 곳(모든 연속된 두 문자 사이)에 위치할 수 있다. 즉 길이가 L인 문자열이 현재 편집기에 입력되어 있으면, 커서가 위치할 수 있는 곳은 L+1가지 경우가 있다.

이 편집기가 지원하는 명령어는 다음과 같다.

L	커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)
D	커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
B	커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨)
삭제로 인해 커서는 한 칸 왼쪽으로 이동한 것처럼 나타나지만, 실제로 커서의 오른쪽에 있던 문자는 그대로임
P $	$라는 문자를 커서 오른쪽에 추가함 커서는 $의 오른쪽에 위치함
초기에 편집기에 입력되어 있는 문자열이 주어지고, 그 이후 입력한 명령어가 차례로 주어졌을 때, 모든 명령어를 수행하고 난 후 편집기에 입력되어 있는 문자열을 구하는 프로그램을 작성하시오. 단, 명령어가 수행되기 전에 커서는 문장의 맨 뒤에 위치하고 있다고 한다.

입력
첫째 줄에는 초기에 편집기에 입력되어 있는 문자열이 주어진다. 이 문자열은 영어 소문자로만 이루어져 있으며, 길이는 100,000을 넘지 않는다. 둘째 줄에는 입력할 명령어의 개수를 나타내는 정수 N(1≤N≤500,000)이 주어진다. 셋째 줄부터 N개의 줄에 걸쳐 입력할 명령어가 순서대로 주어진다. 명령어는 위의 네 가지 중 하나의 형태로만 주어진다.

출력
첫째 줄에 모든 명령어를 수행하고 난 후 편집기에 입력되어 있는 문자열을 출력한다.

abcd
3
P x
L
P y

abcdyx
	 */
	public static void main(String[] args) throws Exception {	
		solution();
	}
	
	/*
	 * https://gist.github.com/Baekjoon/3f90237fcd175f9b1aea
	 */
	private static void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Stack<Character> left = new Stack<Character>();
        Stack<Character> right = new Stack<Character>();
        for (int i=0; i<s.length(); i++) {
            left.push(s.charAt(i));
        }
        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            String[] line = br.readLine().split(" ");
            char what = line[0].charAt(0);
            if (what == 'L') {
                if (!left.empty()) {
                    right.push(left.pop());
                }
            } else if (what == 'D') {
                if (!right.empty()) {
                    left.push(right.pop());
                }
            } else if (what == 'P') {
                char c = line[1].charAt(0);
                left.push(c);
            } else if (what == 'B') {
                if (!left.empty()) {
                    left.pop();
                }
            }
        }
        while (!left.empty()) {
            right.push(left.pop());
        }
        StringBuilder sb = new StringBuilder();
        while (!right.empty()) {
            sb.append(right.pop());
        }
        System.out.println(sb);
	}

	private static void solution1() {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine(); // abcd
		int N = sc.nextInt(); // 3
		
		LinkedList<String> text = new LinkedList<>();	    
	    for (int i = 0; i < str.length(); i++) {
	    	text.add(""+ str.charAt(i));
		}
	    
	    char[] A = { 'P', 'L', 'P' };
	    char[] B = { 'x', ' ', 'y' };
	 
	    int position = text.size();
	    // 명령에 맞게 함수를 실행함
		for (int i = 0; i < N; i++) {
			String command = sc.nextLine();
			StringTokenizer st = new StringTokenizer(command, " ");
			
			switch ((Character)st.nextElement()) {
			case 'L':
				if (position > 0) {
					position--;
				}
				break;
			case 'D':
				if (position < text.size()) {
					position++;
				}
				break;
			case 'B':
				text.remove(position--);
				break;
			case 'P':
				text.add(position++, "" + B[i]);
			}
		}
	    // 결과 출력
		log.debug("{}", text.toString());
		
		    
	}
}
