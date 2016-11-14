package lamda;

import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunctionExam2 {

	private static final Logger log = LoggerFactory.getLogger(FunctionExam2.class);

	private static List<Student> list = Arrays.asList(
			new Student("Jolie", 100, 89, ""), 
			new Student("Martin", 77, 94, ""),
			new Student("Pierre", 49, 100, ""), 
			new Student("Paul", 80, 78, ""));

	public static double avg(ToIntFunction<Student> function) {
		int sum = 0;
		for (Student std : list) {
			sum += function.applyAsInt(std);
		}
		double avg = (double) sum / list.size();
		return avg;
	}

	public static void main(String[] args) {
		double englishAvg = avg(s -> s.getEnglishScore());
		log.debug("영어 평균 점수: {}", englishAvg);

		double mathAvg = avg(s -> s.getMathScore());
		log.debug("영어 평균 점수: {}", mathAvg);
	}
}
