package codility.lesson03.timecomplexity;

/*
 * http://javaconceptoftheday.com/trigonometric-values-math-class/
 * 1) sine of an angle  —> Math.sin();
 * 2) cosine of an angle —> Math.cos();
 * 3) tangent of an angle —> Math.tan();
 * 4) sec of an angle —> 1/Math.sin();
 * 5) cosec of an agnle —> 1/Math.cos();
 * 6) cot of an angle —> 1/Math.tan();
 */
public class TrigonometricFunctions {

	// How To Find Trigonometric Values Of An Angle?
	public static void main(String[] args) {
		double angle = 90;

		// converting angle to radians.
		double angleInRadians = Math.toRadians(angle);

		double sineOfNum = Math.sin(angleInRadians);
		double cosineOfNum = Math.cos(angleInRadians);
		double tangentOfNum = Math.tan(angleInRadians);

		double secOfNum = (1 / sineOfNum);
		double cosecOfNum = (1 / cosineOfNum);
		double cotOfNum = (1 / tangentOfNum);

		System.out.println("sin(" + angle + ") = " + sineOfNum);
		System.out.println("cos(" + angle + ") = " + cosineOfNum);
		System.out.println("tan(" + angle + ") = " + tangentOfNum);

		System.out.println("--------------------------------");

		System.out.println("sec(" + angle + ") = " + secOfNum);
		System.out.println("cosec(" + angle + ") = " + cosecOfNum);
		System.out.println("cot(" + angle + ") = " + cotOfNum);
	}

}
