package algorithm.baekjoon;

public class CaseElement {

	int[] caseElement;

	public CaseElement(int[] caseElement) {
		this.caseElement = caseElement;
	}

	public int[] getCaseElement() {
		return caseElement;
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < caseElement.length; i++) {
			str += caseElement[i];
		}
		return str;
	}
}
