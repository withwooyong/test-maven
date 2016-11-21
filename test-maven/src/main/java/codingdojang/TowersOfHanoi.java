package codingdojang;

/*
 * 하노이의 탑에 대한 전설은 아래와 같다.
 * 인도 베나레스에 있는 한 사원에는 세상의 중심을 나타내는 큰 돔이 있고 그 안에 세 개의 다이아몬드 기둥이 동판 위에 세워져 있다. 
 * 기둥 가운데 하나에는 신이 64개의 순금 원판을 끼워 놓았다. 
 * 가장 큰 원판이 바닥에 놓여 있고, 나머지 원판들이 점점 작아지며 꼭대기까지 쌓여 있다. 
 * 이것은 신성한 브라흐마의 탑이다. 
 * 브라흐마의 지시에 따라 승려들은 모든 원판을 다른 기둥으로 옮기기 위해 밤낮 없이 차례로 제단에 올라 규칙에 따라 원판을 하나씩 옮긴다. 
 * 이 일이 끝날 때, 탑은 무너지고 세상은 종말을 맞이하게 된다.
 * 기둥을 1, 2, 3 번으로 하고, N개의 원판이 작은 것부터 1, 2, 3, 4 … N이라고 할 때, 아래의 규칙에 따라 모든 원판을 3번 기둥으로 쌓기 위해 이동한다.
 * 
 * 1. 한 번에 하나의 원판만 옮길 수 있다.
 * 2. 큰 원판이 작은 원판 위에 있어서는 안 된다.
 * 모든 원판이 1번 기둥에 순서대로 쌓여 있을 때, 3번 기둥으로 모두 이동하는 과정을 순서대로 출력하는 프로그램을 작성하시오.
 */
public class TowersOfHanoi {

	public static void main(String[] args) {

		moveHanoiTower("1", "2", "3", 6);
		System.out.println("총 이동 횟수 : " + count);

	}
	
	public static int count = 0;//전체 횟수
	
	public static void moveHanoiTower(String first, String center, String last,  int n) {
        if (n == 1) {
        	++count;
        	System.out.println(n+" : "+first+ " -> " +last);
        } else {
        	moveHanoiTower(first, last, center, n - 1);
        	// 마지막탑을 보조탑으로 이용하여 첫번째탑에 있는 n-1개의 원반들을 중간에 있는 탑으로 이동한다.
        	++count;
        	System.out.println(n+" : "+first+ " -> " +last);
        	moveHanoiTower(center, first, last, n - 1);
        	// 첫번째탑을 보조탑으로 이용하여 중간탑에 있는 n-1개의 원반들을 목적지 탑으로 이동한다.
        }
        
	}
}
