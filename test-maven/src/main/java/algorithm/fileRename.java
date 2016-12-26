package algorithm.hanbit;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class fileRename {
	
	private static Logger log = LoggerFactory.getLogger(fileRename.class);

	public static void main(String[] args) {
		String source = "C:\\Users\\wooyong\\git\\test-maven\\test-maven\\src\\main\\java\\algorithm\\hanbit\\";		
		subDirList(source);
	}
	
	public static void rename(String filename) {
		
		String ext = filename.substring(filename.lastIndexOf(".") + 1);
		
		if (ext.equals("c")) {
			
		} else {
			File file1 = new File("Z/111");
		    File file2 = new File("Z/222");

		    if (!file1.renameTo(file2)) {
		      System.err.println("이름 변경 에러 : " + file1);
		    }
		}
	}
	
	public static void subDirList(String source) {
		File dir = new File(source);
		File[] fileList = dir.listFiles();
		try {
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];
				if (file.isFile()) {
					// 파일이 있다면 파일 이름 출력
					log.debug("AbsolutePath={}", dir.getAbsolutePath());
					log.debug("Name={}", file.getName());
					log.debug("Parent={}", dir.getParent());
					
					String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
					
					if (ext.equals("java")) {
						//String filename = dir.getAbsolutePath() + "\\" + file.getName().replace(".c", ".java");
						String filename = dir.getParent() + "\\" + file.getName();
						log.debug("filename={}", filename);
						file.renameTo(new File(filename));
					}
				} else if (file.isDirectory()) {
					System.out.println("디렉토리 이름 = " + file.getName());
					// 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
					subDirList(file.getCanonicalPath().toString());
				}
			}
		} catch (IOException e) {

		}
	}

}
