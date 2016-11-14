package lamda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Predicate 함수적 인터페이스 Predicate 함수적 인터페이스는 매개 변수와 boolean 리턴값이 있는 testXXX()
 * 메소드를 가지고 있습니다. 이 메소드들은 매개값을 조사해서 true 또는 false를 리턴하는 역할을 합니다. 매개 변수 타입과 수에
 * 따라서 아래와 같은 Predicate 함수적 인터페이스들이 있습니다.
 * 
 * @author user
 *
 */
public class PredicateExam {

	private static final Logger log = LoggerFactory.getLogger(PredicateExam.class);

	private static List<Student> list = Arrays.asList(new Student("Martin", 80, 90, "Male"),
			new Student("Jolie", 74, 88, "Female"), new Student("Sophie", 66, 100, "Female"),
			new Student("Pierre", 100, 78, "Male"), new Student("anne", 80, 90, "Female"),
			new Student("Paul", 42, 91, "Male"), new Student("cristianne", 99, 100, "Female"),
			new Student("Mcg", 100, 90, "Male"));

	public static double[] avg(Predicate<Student> predicate) {
		int count = 0, engSum = 0, mathSum = 0;
		for (Student std : list) {
			if (predicate.test(std)) {
				count++;
				engSum += std.getEnglishScore();
				mathSum += std.getMathScore();
			}
		}
		double avg[] = { ((double) engSum / count), ((double) mathSum / count) };
		return avg;
	}

	public static void main(String[] args) {
		double maleAvg[] = avg(t -> t.getSex().equals("Male"));
		log.debug("{}", "남자 평균 점수(영어, 수학)");

		for (double avg : maleAvg) {
			log.debug("{}", avg + " ");
		}

		double femaleAvg[] = avg(t -> t.getSex().equals("Female"));
		log.debug("{}", "여자 평균 점수(영어, 수학)");

		for (double avg : femaleAvg) {
			log.debug("{}", avg + " ");
		}
	}
}
