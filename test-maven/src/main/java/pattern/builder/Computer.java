package pattern.builder;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 가장 많이 사용하는 lombok annotation 정리
 * @Getter / @Setter
 * Getter와 Setter 함수를 생성합니다.
 * 접근제어 : AccessLevel 지정을 통해서 접근레벨을 제한할 수 있습니다. (PUBLIC, PROTECTED, PACKAGE, PRIVATE)
 * @Getter(AccessLevel.PACKAGE), @Setter(AccessLevel.PRIVATE) getter/setter 관례에 따라서 get 필드명, set 필드명 메소드가 생성됨
 * @EqualsAndHashCode
 * 코드에서 객체의 비교 등의 용도로 사용되는 equals(), hashCode() 메소드의 코드를 생성합니다.
 * 특정 필드를 제외할 수가 있습니다. @EqualsAndHashCode(exclude={“field1”, “field2”})
 * @ToString
 * 객체의 내용을 문자열로 변환해주는 toString() 메소드를 대신할 수 있습니다.
 * 역시 특정 변수를 제외할 수 있습니다. @ToString(exclude=”field1”)
 * @Log
 * 자동으로 logging을 위한 필드인 private static final Logger log 가 추가됩니다. 이후 로그를 출력하려는 곳에서 log.error(), log.warn(), log.debug(), log.info() 형태로 사용하면 됩니다.
 * @Data
 * 모든 필드에 대한 getter, setter와 toString, equals, hashcode, final로 지정됐거나 @NonNull로 명시된 필드에 대한 값을 받는 생성자 메소드 코드를 생성합니다.
 * @AllArgsConstructor
 * 모든 필드에 대한 값을 받는 생성자를 생성합니다.
 * 접근제어 : AccessLevel 지정을 통해서 접근레벨을 제한할 수 있습니다. (PUBLIC, PROTECTED, PACKAGE, PRIVATE)
 * @author user
 */
@Data
@AllArgsConstructor
public class Computer {
	
	private String cpu;
	private String ram;
	private String storage; 
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
}
