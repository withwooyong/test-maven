package lamda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://palpit.tistory.com/673
 * Function 함수적 인터페이스
 * Function 함수적 인터페이스의 특징은 매개값과 리턴값이 있는 applyXXX() 메소드를 가지고 있습니다. 
 * 이 메소드들은 매개값을 리턴값으로 매핑하는 역할을 합니다. 
 * 매가 변수 타입과 리턴 타입에 따라서 아래와 같은 Function 함수적 인터페이스가 있습니다.
 * 
 * Function<T, R> 인터페이스를 타겟 타입으로 하는 람다식은 다음과 같이 작성할 수 있습니다. 
 * apply() 메소드는 매개값으로 T 객체 하나를 가지므로 람다식도 한 개의 매개 변수를 사용합니다. 
 * 그리고 apply( )메소드의 리턴 타입이 R이므로 람다식 중괄호 {}의 리턴값은 R 객체가 됩니다. 
 * T가 Student 타입이고 R이 String 타입이므로 t 매개 변수 타입은 Student가 되고, 람다식의 중괄호 {}는 String을 리턴해야 합니다. 
 * t.getName()은 Student 객체의 getName() 메소드를 호출해서 학생 이름을 얻습니다. 
 * return문만 있을 경우 중괄호 {}와 return 문은 생략할 수 있습니다. 
 * 다음 코드는 Student 객체를 학생 이름(String)으로 매핑하는 것입니다.
 * @author user
 *
 */
public class FunctionExam1 {
	
	private static final Logger log = LoggerFactory.getLogger(FunctionExam1.class);

	private static List<Student> list = Arrays.asList(
            new Student("Jackie", 90, 65, ""), 
            new Student("Jolie", 100, 100, "")
    );
 
    public static void printString(Function<Student, String> function) {
        for (Student std : list) {
            log.debug("{}", function.apply(std) + " ");
        }
    }
    
    public static void printInt(ToIntFunction<Student> function) {
        for (Student std : list) {
        	log.debug("{}", function.applyAsInt(std) + " ");
        }
    }
    
    public static void main(String[] args) {
        printString( t -> t.getName() );
        printInt( t -> t.getEnglishScore() );
        printInt( t -> t.getMathScore() );
        
    }
 
}
