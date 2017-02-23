package algorithm;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MakeException {
	
	private static Logger log = LoggerFactory.getLogger(MakeException.class);

	public int parseInt1(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			log.debug("{} is {}", e.getMessage(), e.getCause());
			return 0;
		}
	}
	
	/**
	 * throws 
	 * 메소드에 throws를 통해 발생할 수 있는 예외 작성 ,(콤마)를 작성하여 작성가능
	 * 예외상황 발생 시 조치를 취하지 않음
	 * 메소드가 책임을 지는게 아니고 호출자에게 책임을 넘김
	 * @param str
	 * @return
	 * @throws NumberFormatException
	 */
	public int parseInt2(String str) throws NumberFormatException {
		return Integer.parseInt(str);
	}
	
	/**
	 * throw
	 * 예외 발생하면 메소드가 예외 처리를 하고
	 * 예외를 다시 한번 호출자에게 던진다.
	 * @param str
	 * @return
	 * @throws NumberFormatException
	 */
	public int parseInt3(String str) throws NumberFormatException {
		log.debug("{}", str);
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			log.debug("{}", "숫자로 바꿀 수 없습니다.");
			//InvalidNumberException ine = new InvalidNumberException("숫자로 바꿀 수 없습니다.");
			throw nfe;
		}
	}
}

