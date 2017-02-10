package algorithm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringArray {

	private static Logger log = LoggerFactory.getLogger(StringArray.class);
	
	public static void main(String[] args) {
		log.debug("{}", StringArray.class.getSimpleName());
		log.debug("{}", StringArray.class.getName());
		Method[] method = Employee.class.getDeclaredMethods();
		//Method[] method = Employee.class.getMethods();
		
		for (Method method2 : method) {
			log.debug("{}", method2.getName());
		}		
		Class cla = Employee.class;
		log.debug("{}", cla.getName());
		
		Field[] field = Employee.class.getDeclaredFields();
		for (Field field2 : field) {
			log.debug("{}", field2.getName());
		}
		
		try {
			
		} catch (Exception e) {
			StackTraceElement[] ste = e.getStackTrace();
			String className = ste[0].getClassName(); // 예외 발생 클래스
			String fileName = ste[0].getFileName();
			String methodName = ste[0].getMethodName();
			int lineNumber = ste[0].getLineNumber();			
			String message = e.getMessage();
		}
		
		// 1. JSP 호출 ->  -> 컴파일
		// 2. 페이지번역
		// 3. JSP 페이지 컴파일
		// 4. 클래스 로드
		// 5. 인스턴스 생성
		// 6. jspinit 메소드 호출
		// 7. _jspService 메소드 호출
		// 8. _jspDestroy 메소드 호출
		
		
		/*
		toCharArray() //get char array of a String
		charAt(int x) //get a char at the specific index
		length() //string length
		length //array size 
		substring(int beginIndex) 
		substring(int beginIndex, int endIndex)
		Integer.valueOf()//string to integer
		String.valueOf()/integer to string
		Arrays.sort()  //sort an array
		Arrays.toString(char[] a) //convert to string
		Arrays.copyOf(T[] original, int newLength)
		System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
		*/
//		String test = "withwooyong";
//		char[] charArr = test.toCharArray();
		
//		stringTest();
//		stringBufferTest();
//		stringBuilderTest();
		//sortedSetTest();
	}
	
	public static void sortedSetTest() {
		//SortedSet<Integer> set = new TreeSet<>();
		TreeSet<Integer> set = new TreeSet<>();
		set.add(1);
		set.add(3);
		set.add(2);
		log.debug("{}", set.toString());
	}
	
	public static void stringTest() {
		long time = System.currentTimeMillis();
		final String aValue = "abcde";
		String a = new String();
		for (int loop = 0; loop < 10000; loop++) {
			a += aValue;
		}
		System.out.println(System.currentTimeMillis() - time);
	}
	
	public static void stringBufferTest() {
		System.out.println(System.currentTimeMillis());
		final String aValue = "abcde";
		StringBuffer b = new StringBuffer();
		for (int loop = 0; loop < 10000; loop++) {
			b.append(aValue);
		}
		System.out.println(System.currentTimeMillis());
	}
	
	public static void stringBuilderTest() {
		System.out.println(System.currentTimeMillis());
		final String aValue = "abcde";
		StringBuilder c = new StringBuilder();
		for (int loop = 0; loop < 10000; loop++) {
			c.append(aValue);
		}
		System.out.println(System.currentTimeMillis());
	}
}
