package pattern.template.pack;

public class DefaultGameConnectHelper extends AbstractGameConnectHelper {

	@Override
	protected String doSecurity(String str) {
		System.out.println("doSecurity");
		return null;
	}

	@Override
	protected boolean authentication(String id, String password) {
		System.out.println("아이디 암호확인");
		return false;
	}

	@Override
	protected int authorization(String userName) {
		System.out.println("authorization");
		return 0;
	}

	@Override
	protected String connection(String info) {
		System.out.println("접속단계");
		return null;
	}

}
