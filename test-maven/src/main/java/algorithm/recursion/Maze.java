package algorithm.recursion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 현재 위치에서 출구까지 가는 경로가 있으려면
 * 1) 현재 위치가 출구이거나 혹은
 * 2) 이웃한 셀들 중 하나에서 출구까지 가는 경로가 있거나
 * 
 * 현재 위치에서 이미 가본 곳을 다시 지나가지 않고 출구까지 가는 경로가 있으려면
 * 1) 현재 위치가 출구이거나 혹은
 * 2) 이웃한 셀들 중 하나에서 이미가본 곳을 다시 지나가지 않고 출구까지 가는 경로가 있거나
 */
public class Maze {
	
	private static Logger log = LoggerFactory.getLogger(Maze.class);

	private static int[][] maze = { { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 1, 1, 0, 1, 1, 0, 1 }, { 0, 0, 0, 1, 0, 0, 0, 1 },
			{ 0, 1, 0, 0, 1, 1, 0, 0 }, { 0, 1, 1, 1, 0, 0, 1, 1 }, { 0, 1, 0, 0, 0, 1, 0, 1 },
			{ 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0, 1, 0, 0 } };

	private static int PATHWAY_COLOUR = 0; // white
	private static int WALL_COLOUR = 1; // blue
	private static int BLOCKED_COLOUR = 2; // red
	private static int PATH_COLOUR = 3; // green
	private static int N = 8;
	// 미로찾기(Decision Problem)
	// PATH_COLOR: visited이며 아직 출구로 가는 경로가 될 가능성이 있는 cell 
	// BLOCKED_COLOR: visited이며 출구까지의 경로상에 있지 않음이 밝혀진 cell
	public static void main(String[] args) {
		
		log.debug("{}", findMazePath(0, 0));
	}
	
	private static boolean findMazePath(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N || maze[x][y] != PATHWAY_COLOUR) {
			return false;
		} else if (x == N - 1 && y == N - 1) {
			maze[x][y] = PATH_COLOUR;
			return true;
		} else {
			maze[x][y] = PATH_COLOUR;
			if (findMazePath(x - 1, y) || findMazePath(x + 1, y) || findMazePath(x, y - 1) || findMazePath(x, y + 1)) {
				return true;
			}
			maze[x][y] = BLOCKED_COLOUR; // dead end
			return false;
		}
	}	
}
