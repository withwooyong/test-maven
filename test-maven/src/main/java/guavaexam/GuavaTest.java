package guavaexam;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

/**
 * https://blog.outsider.ne.kr/710
 * https://www.tutorialspoint.com/guava/guava_quick_guide.htm
 * @author user
 *
 */
public class GuavaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 1. 컬렉션 초기화와 유틸리티
		// 2. 제한된 함수형 스타일의 프로그래밍
		// 3. 멀티맵(Multimaps)과 바이맵(Bimaps)
		// 4. 쉬운 해쉬코드와 비교자(Comparators)
		// 5. 방어적 코딩
		
		// 컬렉션 생성
		final Map<String, Map<String, Integer>> lookup1 = new HashMap<String, Map<String, Integer>>();
		
		// in Java 7
		final Map<String, Map<String, Integer>> lookup2 = new HashMap<>();
		
		// in Guava
		final Map<String, Map<String, Integer>> lookup3 = Maps.newHashMap();
		
		// Collections2.filter
		SomeCollection test = new SomeCollection();
		//Collection<?> noNullsCollection = Collections2.filter(test, nonotNull());
		
		Multimap<String, Integer> scores = HashMultimap.create();
		scores.put("Bob", 20);
		scores.put("Bob", 10);
		scores.put("Bob", 15);
		System.out.println(Collections.max(scores.get("Bob"))); // prints 2
		
		int count = 1;
		
		
		if (count <= 0) {
			  throw new IllegalArgumentException("must be positive: " + count);
		}		
		
		// in Guava
		Preconditions.checkArgument(count > 0, "must be positive: %s", count);

	}
	
	public int hashCode() {
		int foo = 1;
		String bar = "1";
		return Objects.hashCode(foo, bar);
	}
	
	public int compareTo(final SomeCollection o) {
		int foo = 1;
		String bar = "1";
		return ComparisonChain.start().compare(foo, o.foo).compare(bar, o.bar).result();
	}

}
