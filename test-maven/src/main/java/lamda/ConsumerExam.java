package lamda;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Consumer 함수적 인터페이스
 * Consumer 함수적 인터페이스의 특징은 리턴값이 없는 accept() 메소드를 가지고 있습니다. 
 * accept() 메소드는 단지 매개값을 소비하는 역할만 합니다. 
 * 여기서 소비한다는 말은 사용만 할 뿐 리턴값이 없다는 뜻입니다.
 * 매개 변수의 타입과 수에 따라서 아래와 같은 Consumer들이 있습니다.
 * Consumer<T> 인터페이스를 타켓 타입으로 하는 람다식은 다음과 같이 작성할 수 있습니다. 
 * accept() 메소드는 매개값으로 T 객체 하나를 가지므로 람다식도 한 개의 매개 변수를 사용합니다. 
 * 타입 파라미터 T에 String이 대입되었기 때문에 람다식의 t 매개변수 타입은 String이 됩니다.
 * @author user
 *
 */
public class ConsumerExam {
	
	private static Logger log = LoggerFactory.getLogger(ConsumerExam.class);

	// Consumer<String> consumer = t -> { t를 소비하는 실행문; };
	public static void main(String[] args) {
        Consumer<String> consumer = t -> log.debug("{}", t + "8");
        consumer.accept("자바");
 
        BiConsumer<String, String> biConsumer = (t, u) -> log.debug("{}", t + u);
        biConsumer.accept("자바", "8");
 
        DoubleConsumer doubleConsumer = d -> log.debug("{}", "자바" + d);
        doubleConsumer.accept(8.0);
        
        IntConsumer intConsumer = i -> log.debug("{}", "자바" + i);
        intConsumer.accept(8);        
        
        LongConsumer longConsumer = (l) -> log.debug("{}", "자바" + l);
        longConsumer.accept(8L);
        
        ObjDoubleConsumer<String> objDoubleConsumer = (t, d) -> log.debug("{}", t + d);
        objDoubleConsumer.accept("자바", 8.0);
 
        ObjIntConsumer<String> objIntConsumer = (t, i) -> log.debug("{}", t + i);
        objIntConsumer.accept("자바", 8);
        
        ObjLongConsumer<String> objLongConsumer = (t, l) -> log.debug("{}", t + l);
        objLongConsumer.accept("자바", 8L);
    }
}

