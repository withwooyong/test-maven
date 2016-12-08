package codility.lesson10.primeandcompositenumbers;

/*
 * http://terms.naver.com/entry.nhn?docId=1179083&ref=y&cid=40942&categoryId=32204
 * 그리스의 수학자이자 지리학자인 에라토스테네스가 고안한 소수(素數)를 찾는 방법으로, 
 * 이 방법으로 소수를 찾으려면, 2부터 시작해 자연수를 차례로 쓴 다음, 2 이외의 2의 배수, 3 이외의 3의 배수, 5 이외의 5의 배수의 순서로 수를 지워나가 끝에 남는 수가 소수이다.
 * 
 * 2부터 n까지의 숫자중에서 에라토스테네스의 체로 소수를 찾으려면, 2부터 시작해 n까지의 자연수를 차례로 쓴다. (2, 3, 4, ..., n)
 * 그리고 2 이외의 2의 배수를 지운다(p=2). 이때 2가 최초의 소수가 된다.
 * 그 다음 소수인 3을 제외한 3의 배수를 지운다(p=3).
 * 이 방법을 다음에 지울 소수, 즉 p의 제곱이 n 보다 커질 때까지, 이 방법을 계속한다(p2≥n).
 * 
 */
public class Eratosthenes {

	public static void main(String[] args) {
		
//1과 그 자신 이외의 수를 약수로 가지는 자연수로, 모두 소수의 곱으로 분해할 수 있다.
	}
	
	public static void getPrime(int max) {
		boolean[] a = new boolean[max];
		for (int i = 2; i < max; i++)
			a[i] = true;

		int to = (int) Math.sqrt(max);

		for (int i = 2; i < to; i++)
			if (a[i] != false)

				for (int j = i; j * i < max; j++)
					a[i * j] = false;

		for (int i = 2; i < max; i++)
			if (a[i])
				System.out.print(" " + i);

		System.out.println();
	}
}
