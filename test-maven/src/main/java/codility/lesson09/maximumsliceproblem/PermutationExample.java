package codility.lesson09.maximumsliceproblem;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://crunchify.com/how-to-find-all-permutations-of-a-string-in-java-example/
 * <순열>
 * 서로 다른 n개 중에서 r개(n≥r)를 취하여 그들을 일렬로 세울 때, 늘어 세운 것 하나하나를 n개 중에서 r개 취한 순열이라 한다. 
 * 이 순열의 총수는 ńPŕ 로서 표시한다. 
 * 이 기호는 permutation(순열)의 머리글자에서 따온 것이다. 
 * ńPŕ＝n(n－1)(n－2)…(n－r＋1) 로서 계산된다. 
 * 이를테면 서로 다른 5개인 것 중에서 3개를 취하는 순열의 수는 5P3＝5·4·3=60이 된다. 
 * 계승(階乘:factorial)을 사용하면 ńPŕ＝n！/(n－r)！과 같이 된다.
 * 
 * <원순열> 
 * n개인 것을 일렬이 아니고 원형으로 배열할 때의 순열로서, 이때의 원순열의 수는 다음과 같다.
 * n-1Pn-1=(n－1)！
 * 
 * <중복순열> 
 * n개인 것 중에서 r개 취하는 순열에서 중복을 허락하는 것이다. 
 * 이 중복순열의 수를 구하는 데는 우선 최초의 1개 취하는 방법의 수는 n가지, 다음 1개 취하는 방법의 수도 n가지,…인데 
 * 이것을 r회 계속하는 것이 되므로 이 순열의 수는 nr이다. 
 * 중복순열의 수를 n∏r로 표기한 n∏r=nr이다.
 */
public class PermutationExample {
	
	private static Logger log = LoggerFactory.getLogger(PermutationExample.class);

	public static void main(String[] args) {
		String s = "ABC";
		String s1 = "EBAY";
		String s2 = "Yahoo";
		log.debug("{} {}", s, permutation(s));
		log.debug("{} {}", s1, permutation(s1));
		log.debug("{} {}", s2, permutation(s2));
		
		String perm = "";
		String word = "123";
		permutation2(perm, word);
		
	}
	
	// 순열에 대한 조합 
	// 3개일 경우 = 3x2x1 = 6
	// 4개일 경우 = 4x3x2x1 = 24
	// 5개일 경우 = 5x4x3x2x1 = 120
	private static void permutation2(String perm, String word) {
		//log.debug("perm={} word={}", perm, word);
		if (word.isEmpty()) {
			log.debug("{} ", perm + word);
		} else {
			for (int i = 0; i < word.length(); i++) {
				String permTemp = perm + word.charAt(i);
				String wordTemp = word.substring(0, i) + word.substring(i + 1, word.length());
				permutation2(permTemp, wordTemp);
			}
		}
	}	

	public static Set<String> permutation(String str) {
		
		Set<String> result = new HashSet<String>();
		if (str == null) {
			return null;
		} else if (str.length() == 0) {
			result.add("");
			return result;
		}

		char firstChar = str.charAt(0);
		String rem = str.substring(1);
		Set<String> words = permutation(rem);
		
		for (String newString : words) {
			for (int i = 0; i <= newString.length(); i++) {
				result.add(charAdd(newString, firstChar, i));
			}
		}
		return result;
	}

	public static String charAdd(String str, char c, int j) {
		String first = str.substring(0, j);
		String last = str.substring(j);
		return first + c + last;
	}
}
