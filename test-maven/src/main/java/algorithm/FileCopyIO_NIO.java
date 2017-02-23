package algorithm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileCopyIO_NIO {
	
	private static Logger log = LoggerFactory.getLogger(FileCopyIO_NIO.class);
	private static MakeException makeexception = new MakeException();

	public static void main(String[] args) throws IOException {
//		String source = "C:\\Users\\wooyong\\git\\test-maven\\test-maven\\src\\main\\java\\algorithm\\note-spring.txt";		
//		String target = "C:\\Users\\wooyong\\git\\test-maven\\test-maven\\src\\main\\java\\algorithm\\note-spring_test1.txt";
//		copyFileIO(source, target);		
		throwsTest();
	}
    	
	// 예외 처리를 전혀 하지 않고 호출자에게 넘김으로써 책임을 지지 않음.
	// throws 선언을 통해서 예외가 발생하면 메소드는 상관하지 않고 호출자에게 넘긴다.
	public static void throwsTest() {
		int number = 0;
		try {
//			number = makeexception.parseInt1("DEF");
//			number = makeexception.parseInt2("DEF");
			number = makeexception.parseInt3("DEF");
		} catch (NumberFormatException nfe) {
			log.debug("{}", "호출실패");
		}
		log.debug("{}", number);
	}
	
	public static void copyFileIO(String source, String target) throws IOException {
		FileInputStream fis = new FileInputStream(source);
		FileOutputStream fos = new FileOutputStream(target);
		try {
			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		} catch (IOException e) {
			e.printStackTrace();
			//throw e;
		} finally {
			if (fis != null)
				fis.close();
			if (fos != null)
				fos.close();
		}
	}

	public static void copyFileNIO(String source, String target) throws IOException {
		FileChannel inChannel = new FileInputStream(source).getChannel();
		FileChannel outChannel = new FileOutputStream(target).getChannel();
		try {
			// magic number for Windows, 64Mb - 32Kb
			int maxCount = (64 * 1024 * 1024) - (32 * 1024);
			long size = inChannel.size();
			long position = 0;
			while (position < size) {
				position += inChannel.transferTo(position, maxCount, outChannel);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (inChannel != null)
				inChannel.close();
			if (outChannel != null)
				outChannel.close();
		}
	}
}
