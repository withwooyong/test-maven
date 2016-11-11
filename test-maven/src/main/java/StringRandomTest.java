import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;

public class StringRandomTest {
	
	public static void givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect() {
	    byte[] array = new byte[7]; // length is bounded by 7
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
	 
	    System.out.println(generatedString);
	}
	
	public static void givenUsingPlainJava_whenGeneratingRandomStringBounded_thenCorrect() {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (new Random().nextFloat() * (rightLimit - leftLimit));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	 
	    System.out.println(generatedString);
	}
	
	public static void givenUsingApache_whenGeneratingRandomStringBounded_thenCorrect() {
	    int length = 10;
	    boolean useLetters = true;
	    boolean useNumbers = false;
	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	 
	    System.out.println(generatedString);
	}
	
	public static void givenUsingApache_whenGeneratingRandomAlphabeticString_thenCorrect() {
	    String generatedString = RandomStringUtils.randomAlphabetic(10);
	 
	    System.out.println(generatedString);
	}
	
	public static void givenUsingApache_whenGeneratingRandomAlphanumericString_thenCorrect() {
	    String generatedString = RandomStringUtils.randomAlphanumeric(10);
	 
	    System.out.println(generatedString);
	}
	
	public static void main(String[] args) {
		givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect();
		givenUsingPlainJava_whenGeneratingRandomStringBounded_thenCorrect();
		givenUsingApache_whenGeneratingRandomStringBounded_thenCorrect();
		givenUsingApache_whenGeneratingRandomAlphabeticString_thenCorrect();
		givenUsingApache_whenGeneratingRandomAlphanumericString_thenCorrect();
		
		UUID uid = UUID.randomUUID();
		System.out.println(uid.toString());
	}

}
