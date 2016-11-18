package codingdojang;

/*
 * 1. 재귀함수란?
 * 함수 내에서 자기 자신을(함수)를 계속적으로 콜 하면서 풀어가는 방식이다.
 * 스택(Stack)이라고 생각할 수 있다.
 * 함수가 콜 되면서 최근에 자신을 부른 원래 함수가 스택에 차곡차곡 쌓이게 됨.
 * 중요한건 
 * 처음 불려진 함수에서(스택 맨 밑에있는 메소드) return 되는 값이 최종 return 값이 된다
 * 
 * 2. 팩토리얼이란?
 * 3! = 3*2*1 = 6
 * 4! = 4*3*2*1 = 24
 * 5! = 5*4*3*2*1 = 120
 * 
 * 특정 숫자의 팩토리얼 구하기 
 */
public class Factorial {

	public static void main(String[] args) {
		int input = 4; // 4!
		System.out.println(fact(input));
	}

	public static int fact(int n) {
		if (n <= 1) {
			return n;
		} else {
			return fact(n - 1) * n; // <-- 재귀
		}
	}	
}
