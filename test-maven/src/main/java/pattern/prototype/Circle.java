package pattern.prototype;

public class Circle extends Shape {

	private int x, y, r;

	public Circle(int x, int y, int r) {
		super();
		this.x = x;
		this.y = y;
		this.r = r;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the r
	 */
	public int getR() {
		return r;
	}

	/**
	 * @param r the r to set
	 */
	public void setR(int r) {
		this.r = r;
	}
	
	public Circle copy() throws CloneNotSupportedException {
		Circle circle = (Circle) clone();
		circle.x += 1;
		circle.y += 1;		
		return circle;
	}
	
}
