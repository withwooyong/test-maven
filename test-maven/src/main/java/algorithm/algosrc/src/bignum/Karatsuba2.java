package algorithm.algosrc.src.bignum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 카라츠바의 빠른 곱셈 알고리즘
// 7.3 두 큰 수를 곱하는 O(n2) 시간 알고리즘
public class Karatsuba2 {
	
	private static Logger log = LoggerFactory.getLogger(Karatsuba2.class);

	public static void main(String[] args) {
		String a;
		String b;
		int[] aa = fromString(a);
		int[] bb = fromString(b);
		toString(karatsuba(aa, bb));
		
		
		int cc = 0;
		while(cin >> a >> b) {
			if(argc == 2 && strcmp(argv[1], "karatsuba") == 0) {
				vector<int> aa = fromString(a);
				vector<int> bb = fromString(b);
				cout << toString(karatsuba(aa, bb)) << endl;
			}
			else
				cout << toString(multiply(fromString(a), fromString(b))) << endl;
			fprintf(stderr, "%d ..\n", ++cc);
		}

	}

	// num[] 의 자릿수 올림을 처리한다.
	private static void normalize(int[] num) {
		num.push_back(0);
		// 자릿수 올림을 처리한다
		for(int i = 0; i < num.size(); i++) {		
			if(num[i] < 0) {
				int borrow = (abs(num[i]) + 9) / 10;
				num[i+1] -= borrow;
				num[i] += borrow * 10;
			}
			else {
				num[i+1] += num[i] / 10;
				num[i] %= 10;
			}
		}
		if(num.back() == 0) num.pop_back();
	}

	// 두 긴 정수의 곱을 반환한다. 각 배열에는 각 수의 자리수가 1의 자리에서부터 시작해 저장되어 있다.
	// 예: multiply({3,2,1},{6,5,4}) = 123*456 = 56088 = {8,8,0,6,5}
	private static int[] multiply(int[] a, int[] b) {
		vector<int> c(a.size() + b.size() + 1, 0);
		for(int i = 0; i < a.size(); i++)
			for(int j = 0; j < b.size(); j++)
				c[i+j] += a[i] * b[j];
		normalize(c);
		return c;
	}

	// a += b * (10^k); 를 구현한다.
	private static void addTo(int[] a, int[] b, int k) {
		a.resize(Math.max(a.length, b.length + k));
		for(int i = 0; i < b.length; i++) a[i+k] += b[i];
		normalize(a);
	}

	// a -= b; 를 구현한다. a >= b 를 가정한다.
	private static void subFrom(int[] a, int[] b) {
		a.resize(Math.max(a.length, b.length) + 1);
		for(int i = 0; i < b.length; i++) a[i] -= b[i];
		normalize(a);
	}

	// 두 긴 정수의 곱을 반환한다. 
	private static int[] karatsuba(int[] a, int[] b) {
		int an = a.length;
		int bn = b.length;
		// a 가 b 보다 짧을 경우 둘을 바꾼다
		if(an < bn) return karatsuba(b, a);
		// 기저 사례: a 나 b 가 비어 있는 경우
		if(an == 0 || bn == 0) return vector<int>();
		// 기저 사례: a 가 비교적 짧은 경우 O(n^2) 곱셈으로 변경한다.
		if(an <= 50) return multiply(a, b);

		int half = an / 2;
		// a 와 b 를 밑에서 half 자리와 나머지로 분리한다
		vector<int> a0(a.begin(), a.begin() + half);
		vector<int> a1(a.begin() + half, a.end());
		vector<int> b0(b.begin(), b.begin() + min<int>(b.size(), half));
		vector<int> b1(b.begin() + min<int>(b.size(), half), b.end());
		// z2 = a1 * b1
		vector<int> z2 = karatsuba(a1, b1);
		// z0 = a0 * b0
		vector<int> z0 = karatsuba(a0, b0);
		// a0 = a0 + a1; b0 = b0 + b1
		addTo(a0, a1, 0); addTo(b0, b1, 0);
		// z1 = (a0 * b0) - z0 - z2;
		vector<int> z1 = karatsuba(a0, b0);
		subFrom(z1, z0);
		subFrom(z1, z2);
		// ret = z0 + z1 * 10^half + z2 * 10^(half*2)
		vector<int> ret;
		addTo(ret, z0, 0); 
		addTo(ret, z1, half);
		addTo(ret, z2, half + half);
		return ret;
	}

	private static String toString(int[] a) {
		String ret;
		while(a.length > 1 && a.back() == 0) {
			a.pop_back();
		}
		for(int i = 0; i < a.length; i++) { 
			ret += char('0' + a[a.length - 1 - i]);
		}
		return ret;
	}

	private static int[] fromString(String s) {
		int[] ret = new int[s.length()];
		ArrayList<Inte>
		for(int i = 0; i < s.length(); i++) {
			ret[i] = s.charAt(i) - 0;
		}
			
		reverse(ret.begin(), ret.end());
		return ret;
	}

}
