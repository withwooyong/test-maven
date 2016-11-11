package pattern.template.pack;

public abstract class AbstractGameConnectHelper {

	protected abstract String doSecurity(String str);
	protected abstract boolean authentication(String id, String password);
	protected abstract int authorization(String userName);
	protected abstract String connection(String info);
	
	// 템플릿 메소드
	public String requestConnection(String encodedInfo) {
		// 보안작업 -> 암호화 된 문자열을 복호화.
		String decodedInfo = doSecurity(encodedInfo);
		// 
		String id = "aaa";
		String password = "bbb";
		
		if (!authentication(id, password)) {
			throw new Error("아이디 암호 불일치");
		} 
		String userName = "";
		int i = authorization(userName);
		
		switch (i) {
		case 0:
			System.out.println("게임 매니저");
			break;
		case 1:
			System.out.println("유료 매니저");
			break;
		case 3:
			System.out.println("무료 매니저");
			break;
		case 4:
			System.out.println("권한없음");
			break;
		default:
			break;
		}
		
		return connection(encodedInfo);
	}	
}
