package algorithm.baekjoon.beginner.G_tree1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1991 {	
	
	private static Logger log = LoggerFactory.getLogger(P1991.class); 

	/*
	 * 트리 순회
	 * 문제
이진 트리를 입력받아 전위 순회(preorder traversal), 중위 순회(inorder traversal), 후위 순회(postorder traversal)한 결과를 출력하는 프로그램을 작성하시오.



예를 들어 위와 같은 이진 트리가 입력되면,

전위 순회한 결과 : ABDCEFG // (루트) (왼쪽 자식) (오른쪽 자식)
중위 순회한 결과 : DBAECFG // (왼쪽 자식) (루트) (오른쪽 자식)
후위 순회한 결과 : DBEGFCA // (왼쪽 자식) (오른쪽 자식) (루트)
가 된다.

입력
첫째 줄에는 이진 트리의 노드의 개수 N(1≤N≤26)이 주어진다. 둘째 줄부터 N개의 줄에 걸쳐 각 노드와 그의 왼쪽 자식 노드, 오른쪽 자식 노드가 주어진다. 노드의 이름은 A부터 차례대로 영문자 대문자로 매겨지며, 항상 A가 루트 노드가 된다. 자식 노드가 없는 경우에는 .으로 표현된다.

출력
첫째 줄에 전위 순회, 둘째 줄에 중위 순회, 셋째 줄에 후위 순회한 결과를 출력한다. 각 줄에 N개의 알파벳을 공백 없이 출력하면 된다.

7
A B C
B D .
C E F
E . .
F . G
D . .
G . .

ABDCEFG
DBAECFG
DBEGFCA
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * 
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[][] a = new int[26][2];
        for (int i=0; i<n; i++) {
            String line = sc.nextLine();
            int x = line.charAt(0) - 'A';
            char y = line.charAt(2);
            char z = line.charAt(4);
            if (y == '.') {
                a[x][0] = -1;
            } else {
                a[x][0] = y-'A';
            }
            if (z == '.') {
                a[x][1] = -1;
            } else {
                a[x][1] = z-'A';
            }
        }
        preorder(a,0);
        System.out.println();
        inorder(a,0);
        System.out.println();
        postorder(a,0);
        System.out.println();
		
		sc.close();
	}
	
	public static void preorder(int[][] a, int x) {
        if (x == -1) return;
        System.out.print((char)(x+'A'));
        preorder(a,a[x][0]);
        preorder(a,a[x][1]);
    }
    public static void inorder(int[][] a, int x) {
        if (x == -1) return;
        inorder(a,a[x][0]);
        System.out.print((char)(x+'A'));
        inorder(a,a[x][1]);
    }
    public static void postorder(int[][] a, int x) {
        if (x == -1) return;
        postorder(a,a[x][0]);
        postorder(a,a[x][1]);
        System.out.print((char)(x+'A'));
    }
}
