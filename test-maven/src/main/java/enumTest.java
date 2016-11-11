
public class enumTest {
	
	public static enum RESULT_MSG {
		SUCCESS, FAIL, DUPLICATE
	}

	public static void main(String[] args) {
		System.out.println(RESULT_MSG.SUCCESS.toString());
		System.out.println(RESULT_MSG.FAIL);
		System.out.println(RESULT_MSG.DUPLICATE);

	}

}
