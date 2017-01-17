package algorithm.baekjoon.beginner.B_data_structure1;

import java.util.ArrayDeque;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P10866 {

	private static Logger log = LoggerFactory.getLogger(P10866.class); 

	/*
	 * 덱
	 * 문제
정수를 저장하는 덱(Deque)를 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.

명령은 총 여덟 가지이다.

push_front X: 정수 X를 덱의 앞에 넣는다.
push_back X: 정수 X를 덱의 뒤에 넣는다.
pop_front: 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
pop_back: 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
size: 덱에 들어있는 정수의 개수를 출력한다.
empty: 덱이 비어있으면 1을, 아니면 0을 출력한다.
front: 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
back: 덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
입력
첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다. 둘쨰 줄부터 N개의 줄에는 명령이 하나씩 주어진다. 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다. 문제에 나와있지 않은 명령이 주어지는 경우는 없다.

출력
출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력한다.

15
push_back 1
push_front 2
front
back
size
empty
pop_front
pop_back
pop_front
size
empty
pop_back
push_front 3
empty
front

2
1
2
0
2
1
-1
0
1
-1
0
3
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * ArrayDeque를 이용하면 앞부분이나 뒤부분으로 객체를 추가할 수 있습니다.
	 * ArrayDeque에 add 메소드를 사용하면 뒤로 객체가 추가 됩니다.
	 * ArrayDeque에 push 메소드를 사용하면 앞으로 객체가 추가 됩니다.
	 * ArrayDeque에 getFirst 메소드를 사용하면 앞쪽에서 객체를 가져 옵니다.
	 * ArrayDeque에 element 메소드를 사용하면 앞쪽에서 객체를 가져 옵니다.
	 * ArrayDeque에 getLast 메소드를 사용하면 뒤쪽에서 객체를 가져 옵니다.
	 * ArrayDeque에 pop 메소드를 사용하면 앞쪽에서 객체를 가져 오고 ArrayDeque에서 제거 합니다.
	 * ArrayDeque에 poll 메소드를 사용하면 앞쪽에서 객체를 가져 오고 ArrayDeque에서 제거 합니다.
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        for (int k=0; k<n; k++) {
            String line = sc.nextLine();
            String[] s = line.split(" ");
            String cmd = s[0];
            if (cmd.equals("push_front")) {
                int num = Integer.parseInt(s[1]);
                queue.offerFirst(num);
            } else if (cmd.equals("push_back")) {
                int num = Integer.parseInt(s[1]);
                queue.offerLast(num);
            }  else if (cmd.equals("front")) {
                if (queue.isEmpty()) {
                    System.out.println("-1");
                } else {
                    System.out.println(queue.peekFirst());
                }
            } else if (cmd.equals("size")) {
                System.out.println(queue.size());
            } else if (cmd.equals("empty")) {
                if (queue.isEmpty()) {
                    System.out.println("1");
                } else {
                    System.out.println("0");
                }
            } else if (cmd.equals("pop_front")) {
                if (queue.isEmpty()) {
                    System.out.println("-1");
                } else {
                    System.out.println(queue.pollFirst());
                }
            } else if (cmd.equals("pop_back")) {
                if (queue.isEmpty()) {
                    System.out.println("-1");
                } else {
                    System.out.println(queue.pollLast());
                }
            } else if (cmd.equals("back")) {
                if (queue.isEmpty()) {
                    System.out.println("-1");
                } else {
                    System.out.println(queue.peekLast());
                }
            }
        }
		
		sc.close();
	}

}
