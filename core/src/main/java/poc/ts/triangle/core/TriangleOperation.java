package poc.ts.triangle.core;

import poc.ts.triangle.core.entity.Triangle;

/**
 * Interface {@link TriangleOperation} defines triangle operations
 *
 * @author Erik Wessel
 */
public interface TriangleOperation {

	/**
	 * Computing the triangle type from its side lengths
	 *
	 * @param sideLength1
	 *            side length 1
	 * @param sideLength2
	 *            side length 2
	 * @param sideLength3
	 *            side length 3
	 * @return type of triangle
	 *
	 * @see Type.SCALENE
	 * @see Type.ISOSCELES
	 * @see Type.EQUILATERAL
	 */
	Triangle.Type getTriangleTypeBySideLengths(int sideLength1, int sideLength2, int sideLength3);
}
