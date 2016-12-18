package algorithm.algosrc.src.housewarming;

import java.util.ArrayList;

// 4.5 음식 메뉴 정하기
public class HouseWarming2 {

	public static void main(String[] args) {
		int cases;
	}
	
	private static int M;
	
	// food 번 요리를 만들까 말까를 결정한다
	private static int selectMenu(ArrayList<Integer> menu, int food) {
	    if(food == M) {
	        if(everybodyCanEat(menu))
	            return menu.size();
	        return 987654321;
	    }
	    int ret = selectMenu(menu, food+1);
	    menu.add(food);
	    ret = Math.min(ret, selectMenu(menu, food+1) + 1);
	    menu.remove(menu.size());
	    return ret;
	}

	private static boolean everybodyCanEat(ArrayList<Integer> menu) {
	    return false;
	}
}
