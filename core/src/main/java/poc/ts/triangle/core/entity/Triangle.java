package poc.ts.triangle.core.entity;

/**
 * Class {@link Triangle} is a representation of a triangle defined by its three
 * side lengths
 *
 * @author Erik Wessel
 */
public class Triangle {

	/*
	 * Triangle type
	 */

	public static enum Type {
		SCALENE, ISOSCELES, EQUILATERAL
	};

	/* Members */

	// Side lengths
	private int sideLength1;
	private int sideLength2;
	private int sideLength3;

	/**
	 * Constructor through side lengths
	 *
	 * @param sideLength1
	 *            the length of side 1
	 * @param sideLength2
	 *            the length of side 2
	 * @param sideLength3
	 *            the length of side 3
	 */
	public Triangle(int sideLength1, int sideLength2, int sideLength3) {
		if (sideLength1 <= 0) {
			throw new IllegalArgumentException("Side length 1 is less than or equal to 0: " + sideLength1);
		}
		if (sideLength2 <= 0) {
			throw new IllegalArgumentException("Side length 2 is less than or equal to 0: " + sideLength2);
		}
		if (sideLength3 <= 0) {
			throw new IllegalArgumentException("Side length 3 is less than or equal to 0: " + sideLength3);
		}
		this.sideLength1 = sideLength1;
		this.sideLength2 = sideLength2;
		this.sideLength3 = sideLength3;
	}

	/**
	 * @return the length of the first side
	 */
	public int getSideLength1() {
		return sideLength1;
	}

	/**
	 * @return the length of the second side
	 */
	public int getSideLength2() {
		return sideLength2;
	}

	/**
	 * @return the length of the third side
	 */
	public int getSideLength3() {
		return sideLength3;
	}

	/**
	 * Computing the triangle type from an instance of triangle
	 *
	 * @param triangle
	 *            the triangle to compute
	 * @return the type
	 *
	 * @see Type.SCALENE
	 * @see Type.ISOSCELES
	 * @see Type.EQUILATERAL
	 */
	public Type getType() {
		if ((sideLength1 == sideLength2) && (sideLength2) == sideLength3) {
			// The three side lengths are equal
			return Type.EQUILATERAL;
		} else if ((sideLength1 == sideLength2) || (sideLength2 == sideLength3) || (sideLength3 == sideLength1)) {
			// Two of the side lengths are equal
			return Type.ISOSCELES;
		} else {
			// Default: none of the side lengths are equal
			return Type.SCALENE;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Triangle [sideLength1=" + sideLength1 + ", sideLength2=" + sideLength2 + ", sideLength3=" + sideLength3
				+ "]";
	}
}
