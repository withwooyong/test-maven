import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * java에서 동영상의 스틸컷을 추출하기 위해 ffmpeg을 Runtime.exec()로 실행하는데, 
 * ffmpeg이 실행이 종료되지 않고 멈추는 현상이 발생했다. 
 * 확인해 본 결과 ffmpeg 에러 출력 메시지 때문이었다. 
 * Runtime.exec()로 ffmpeg Processor를 생성한 뒤에 아래 코드와 같이 에러 출력 스트림으로부터 데이터를 읽어오기만 하면 
 * 블록킹 없이 ffmpeg이 실행된다.
 * 참고로, 코드는 동영상으로부터 특정 시점의 썸네일 이미지를 추출하는 코드이다.
 * 
 * @author user
 *
 */
public class extractImage {

	public static void main(String[] args) throws IOException {
		extractImage test = new extractImage();
		//test.extractImage(videoFile, position, creatingImageFile);

	}

	public File extractImage(File videoFile, int position, File creatingImageFile) {
		try {
			int seconds = position % 60;
			int minutes = (position - seconds) / 60;
			int hours = (position - minutes * 60 - seconds) / 60 / 60;

			String videoFilePath = videoFile.getAbsolutePath();
			String imageFilePath = creatingImageFile.getAbsolutePath();

			String[] commands = { "ffmpeg", "-ss", String.format("%02d:%02d:%02d", hours, minutes, seconds), "-i",
					videoFilePath, "-an", "-vframes", "1", "-y", imageFilePath };

			Process processor = Runtime.getRuntime().exec(commands);

			String line1 = null;
			BufferedReader error = new BufferedReader(new InputStreamReader(processor.getErrorStream()));
			while ((line1 = error.readLine()) != null) {
				System.out.println(line1);
			}
			processor.waitFor();
			int exitValue = processor.exitValue();
			if (exitValue != 0) {
				throw new RuntimeException("exit code is not 0 [" + exitValue + "]");
			}
			return creatingImageFile;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
