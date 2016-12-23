package codility.lesson08.leader;

import java.util.Arrays;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Leader {
	
	private static Logger log = LoggerFactory.getLogger(Leader.class);

	// Leader는 배열에서 절반이상의 중복된 값을 말한다.
	public static void main(String[] args) {
		//int[] A = { 6, 8, 4, 6, 8, 6, 6 };
		int[] A = { 4, 6, 6, 6, 6, 8, 8 };
		log.debug("O(n2) {}", slowLeader(A));
		log.debug("O(nlogn) {}", fastLeader(A));
		log.debug("O(n) {}", goldenLeader(A));
		log.debug("O(n) {}", stackLeader(A));
	}
	
	// O(n)
	// 첫번째는 스택에삽입하고 두번째 부터 스택에 있는 값이랑 동일하면 스택삭입 틀리면 스택에 있는거 삭제.
	// 최종에 남는 숫자가 Leader 이다.
	// for 문을 한번 더 돌리면서 동일한 값이 갯수 세면 됨.
	private static int stackLeader(int[] A) {
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < A.length; i++) {
			if (stack.isEmpty()) {
				stack.push(A[i]);
			} else {
				if (stack.peek() != A[i]) { // stack 값과 틀리면 stack 삭제
					stack.pop();
				} else {
					stack.push(A[i]);
				}
			}
		}
		log.debug("마지막 남은 stack={}", stack.toString());
		int candidate = stack.pop();
		int count = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == candidate) {
				count++;
			}
		}
		
		if (count > A.length / 2) {
		} else {
			candidate = -1; // reader 없음.
		}
		return candidate; // reader 
	}

	// O(n2)
	// 중복루프를 돌면서 껀껀히 비교한 후 절반이상인 값을 골라낸다.
	private static int slowLeader(int[] A) {
		int n = A.length;
		int leader = -1;
		int candidate = -1;

		for (int i = 0; i < A.length; i++) {
			candidate = A[i];
			int count = 0;
			for (int j = 0; j < A.length; j++) {
				if (A[j] == candidate) {
					count += 1; // 동일한 값이면 카운트
				}
			}
			if (count > n / 2) {
				leader = candidate;
			}
		}
		return leader;
	}
	
	// O(nlogn)
	private static int fastLeader(int[] A) {
		int n = A.length;
		int leader = -1;
		Arrays.sort(A); // 정렬하면 중
		int candidate = A[n / 2]; // 정렬 후 중간지점부터 시작
		int count = 0;

		for (int i = 0; i < A.length; i++) {
			if (A[i] == candidate) {
				count += 1;
			}
		}
		if (count > n / 2) {
			leader = candidate;
		}
		return leader;
	}
	
	// O(n)
	private static int goldenLeader(int[] A) {		
		int size = 0;
		int value = 0;
		for (int i = 0; i < A.length; i++) {
			if (size == 0) {
				size += 1;
				value = A[i];
			} else if (value != A[i]) {
				size -= 1;
			} else {
				size += 1;
			}
		}
		
		int candidate = -1;
		if (size > 0) {
			candidate = value;
		}
		int leader = -1;
		int count = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == candidate) {
				count += 1;
			}			
		}
		if (count > (A.length / 2)) {
			leader = candidate;
		}
		return leader;
	}
}
