package lamda;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Supplier 함수적 인터페이스
 * Supplier 함수적 인터페이스의 특징은 매개 변수가 없고 리턴값이 있는 getXXX() 메소드를 가지고 있습니다.
 * 이 메소드들은 실행 후 호출한 곳으로 데이터를 리턴(공급)하는 역할을 합니다.
 * 리턴 타입에 따라서 아래와 같은 Supplier 함수적 인터페이스들이 있습니다.
 * 
 * Supplier<T> 인터페이스를 타겟 타입으로 하는 람다식은 다음과 같이 작성할 수 있습니다. 
 * getAsInt() 메소드가 매개값을 가지지 않으므로 람다식도 ()를 사용합니다. 
 * 람다식의 중괄호 {}는 반드시 int 값을 리턴하도록 해야 합니다.
 * IntSupplier supplier = () -> {...; return int_value; }
 * 
 * @author user
 *
 */
public class SupplierExam {
	
	private final static Logger log = LoggerFactory.getLogger(SupplierExam.class);

	public static void main(String[] args) {
		
		BooleanSupplier booleanSupplier = () -> {
			boolean b = false;
			return b;
		};		
		log.debug("{}", booleanSupplier.getAsBoolean());
		
		DoubleSupplier doubleSupplier = () -> {
			double d = 8.0;
			return d;
		};
		log.debug("{}", doubleSupplier.getAsDouble());
		
		
        IntSupplier intSupplier = () -> {
            int num = (int) (Math.random() * 6) + 1; 
            return num;
        };
        log.debug("{}", intSupplier.getAsInt());
        
        LongSupplier longSupplier = () -> {
        	long l = 8;
        	return l;
        };
        log.debug("{}", longSupplier.getAsLong());       
        
    }
}
