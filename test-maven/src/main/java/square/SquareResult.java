package square;

/** A squaring result message. */
public class SquareResult {
	private final int input;
	private final int output;

	/**
	 * Make a new result message.
	 * 
	 * @param input
	 *            input number
	 * @param output
	 *            square of input
	 */
	public SquareResult(int input, int output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public String toString() {
		return input + "^2 = " + output;
	}
}