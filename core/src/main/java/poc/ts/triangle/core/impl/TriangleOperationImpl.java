/**
 *
 */
package poc.ts.triangle.core.impl;

import poc.ts.triangle.core.TriangleOperation;
import poc.ts.triangle.core.entity.Triangle;

/**
 * Class {@link TriangleOperationImpl} implements triangle operations
 *
 * @author Erik Wessel
 */
public class TriangleOperationImpl implements TriangleOperation {

	/**
	 * Default constructor
	 */
	public TriangleOperationImpl() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * poc.ts.triangle.core.TriangleOperation#getTriangleTypeBySideLengths(int,
	 * int, int)
	 */
	@Override
	public Triangle.Type getTriangleTypeBySideLengths(int sideLength1, int sideLength2, int sideLength3) {
		Triangle triangle = new Triangle(sideLength1, sideLength2, sideLength3);
		return triangle.getType();
	}
}
