package algorithm.algosrc.src.boggle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 6.3 보글 게임판에서 단어를 찾는 재귀 호출 알고리즘
public class Boggle2 {
	
	private static Logger log = LoggerFactory.getLogger(Boggle2.class);

	public static void main(String[] args) {
		// 게임판의 정보를 저장한다
		
//		ArrayList<String> board = new ArrayList<>();
//		board.add("URLPM");
//		board.add("XPRET");
//		board.add("GIAET");
//		board.add("XTNZY");
//		board.add("HOQRS");
		
		log.debug("{}", hasWord(1, 1, "PRETTY"));
//		log.debug("{}", hasWord(2, 0, "GIRL"));
//		log.debug("{}", hasWord(4, 0, "HONEY"));
//		log.debug("{}", !hasWord(1, 1, "PRETTMY"));
//		log.debug("{}", !hasWord(2, 0, "GIRAL"));
	}
	
	private static char[][] board = {
			{ 'U','R','L','P','M' },	
			{ 'X','P','R','E','T' },
			{ 'G','I','A','E','T' },
			{ 'X','T','N','Z','Y' },
			{ 'H','O','Q','R','S' }
	};
	
	// 5x5 의 보글 게임 판의 해당 위치에서 주어진 단어가 시작하는지를 반환
	private static boolean hasWord(int y, int x, String word) {
		if (y < 0 || x < 0 || y >= 5 || x >= 5) {
			return false;
		}
		if (board[y][x] != word.charAt(0)) {
			return false;
		}
		if (word.length() == 1) {
			return true;
		}
		for (int dx = -1; dx <= 1; ++dx) {
			for (int dy = -1; dy <= 1; ++dy) {
				//if ((dx || dy) && hasWord(y + dy, x + dx, word.substring(1))) {
				log.debug("{} {} {}", y+dy, x+dx, word.substring(1));
				if ((dx > 0 || dy > 0) && hasWord(y + dy, x + dx, word.substring(1))) {
					return true;
				}
			}
		}
		return false;
	}

}
