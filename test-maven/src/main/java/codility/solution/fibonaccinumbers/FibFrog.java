/*
	The Fibonacci sequence is defined using the following recursive formula:
	    F(0) = 0
	    F(1) = 1
	    F(M) = F(M - 1) + F(M - 2) if M >= 2
	A small frog wants to get to the other side of a river. The frog is initially located at one bank of the river (position −1) and wants to get to the other bank (position N). The frog can jump over any distance F(K), where F(K) is the K-th Fibonacci number. Luckily, there are many leaves on the river, and the frog can jump between the leaves, but only in the direction of the bank at position N.
	The leaves on the river are represented in a zero-indexed array A consisting of N integers. Consecutive elements of array A represent consecutive positions from 0 to N − 1 on the river. Array A contains only 0s and/or 1s:
	0 represents a position without a leaf;
	1 represents a position containing a leaf.
	The goal is to count the minimum number of jumps in which the frog can get to the other side of the river (from position −1 to position N). The frog can jump between positions −1 and N (the banks of the river) and every position containing a leaf.
	For example, consider array A such that:
	    A[0] = 0
	    A[1] = 0
	    A[2] = 0
	    A[3] = 1
	    A[4] = 1
	    A[5] = 0
	    A[6] = 1
	    A[7] = 0
	    A[8] = 0
	    A[9] = 0
	    A[10] = 0
	The frog can make three jumps of length F(5) = 5, F(3) = 2 and F(5) = 5.
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a zero-indexed array A consisting of N integers, returns the minimum number of jumps by which the frog can get to the other side of the river. If the frog cannot reach the other side of the river, the function should return −1.
	For example, given:
	    A[0] = 0
	    A[1] = 0
	    A[2] = 0
	    A[3] = 1
	    A[4] = 1
	    A[5] = 0
	    A[6] = 1
	    A[7] = 0
	    A[8] = 0
	    A[9] = 0
	    A[10] = 0
	the function should return 3, as explained above.
	Assume that:
	N is an integer within the range [0..100,000];
	each element of array A is an integer that can have one of the following values: 0, 1.
	Complexity:
	expected worst-case time complexity is O(N*log(N));
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	피보나치 시퀀스는 다음 재귀 공식을 사용하여 정의됩니다.
F (0) = 0
F (1) = 1
M> = 2 인 경우, F (M) = F (M-1) + F (M-2)
작은 개구리가 강 저편에 도착하기를 원합니다. 개구리는 처음에 강 하나 은행 (위치 -1)에 위치하고 다른 은행 (위치 N)에 도착하려고합니다. 개구리는 어떤 거리 F (K)로 점프 할 수 있습니다. 여기서 F (K)는 K 번째 피보나치 수입니다. 운 좋게도 강가에 많은 잎이 있고, 개구리는 잎 사이를 뛰어 넘을 수 있지만 위치 N의 은행 방향으로 만 갈 수 있습니다.
강에있는 잎은 N 개의 정수로 구성된 0 인덱스 배열 A로 표현됩니다. 배열 A의 연속 요소는 강에서 0에서 N-1까지의 연속적인 위치를 나타냅니다. 배열 A는 0 및 / 또는 1 만 포함합니다.
0은 잎이없는 위치를 나타내고;
1은 잎을 포함하는 위치를 나타낸다.
목표는 개구리가 강 저편 (위치 -1에서 위치 N까지)으로 갈 수있는 점프의 최소 횟수를 세는 것입니다. 개구리는 -1과 N 사이 (강 유역)와 잎이있는 모든 위치 사이를 이동할 수 있습니다.
예를 들어 다음과 같은 배열 A를 고려하십시오.
A [0] = 0
A [1] = 0
A [2] = 0
A [3] = 1
A [4] = 1
A [5] = 0
A [6] = 1
A [7] = 0
A [8] = 0
A [9] = 0
A [10] = 0
개구리는 길이 F (5) = 5, F (3) = 2 및 F (5) = 5의 세 점프를 만들 수 있습니다.
함수 작성 :
클래스 솔루션 {public int solution (int [] A); }
즉, N 개의 정수로 구성된 인덱스가없는 배열 A가 주어지면, 개구리가 강 저편에 도달 할 수있는 최소 점프 수를 반환합니다. 개구리가 강 저편에 도달 할 수 없다면 함수는 -1을 반환해야합니다.
예를 들어, 주어진 :
A [0] = 0
A [1] = 0
[2] = 0
A [3] = 1
A [4] = 1
A [5] = 0
A [6] = 1
A [7] = 0
A [8] = 0
A [9] = 0
A [10] = 0
함수는 위에서 설명한대로 3을 반환해야합니다.
가정하자면 :
N은 [0..100,000] 범위 내의 정수입니다.
배열 A의 각 요소는 0, 1 중 하나의 값을 가질 수있는 정수입니다.
복잡성:
기대 최악의 경우의 시간 복잡도는 O (N * log (N))이다.
예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.fibonaccinumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FibFrog {
	int number = 0;
	public static void main(String[] args) {
		int[] A = new int[]{0,0,0,1,1,0,1,0,0,0,0};
		System.out.println(solution(A));
	}
	
	public static int solution(int[] A) {
		List<Integer> fibs = getFibonaciUpTo(A.length+1);
		boolean[] visited = new boolean[A.length];
		Stack<Jump> stack= new Stack<Jump>();
		stack.push(new Jump(-1,0));
		while(!stack.isEmpty()) {
			Jump currJump = stack.firstElement();
			stack.remove(0);
			int i = 0;
			while(currJump.pos + fibs.get(i)<= A.length) {
				if (currJump.pos + fibs.get(i) == A.length)
					return currJump.jumps + 1;
				if(A[fibs.get(i)+currJump.pos] == 1 && !visited[currJump.pos + fibs.get(i)]) {
					stack.push(new Jump(fibs.get(i)+currJump.pos, currJump.jumps+1));
					visited[fibs.get(i)+currJump.pos] = true;
				}
				i++;
			}
		}
		return -1;
	}
	
    public static List<Integer> getFibonaciUpTo(int n) {
        List<Integer> fibs = new ArrayList<Integer>();
        fibs.add(0);
        fibs.add(1);
        int i =2;
        while(fibs.get(fibs.size()-1) <= n){
        	fibs.add(fibs.get(i-1)+fibs.get(i-2));
        	i++;
        }
        fibs.remove(0);
        fibs.remove(1);
        return fibs;
    }
    
    public static class Jump {
    	int pos;
    	int jumps;
    	Jump(int p, int j) {
    		pos = p;
    		jumps = j;
    	}
    }
		
}
