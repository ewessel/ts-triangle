/**
 *
 */
package poc.ts.triangle.core.geom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static poc.ts.triangle.core.entity.Triangle.Type.EQUILATERAL;
import static poc.ts.triangle.core.entity.Triangle.Type.ISOSCELES;
import static poc.ts.triangle.core.entity.Triangle.Type.SCALENE;

import org.junit.Before;
import org.junit.Test;

import poc.ts.triangle.core.TriangleOperation;
import poc.ts.triangle.core.entity.Triangle.Type;
import poc.ts.triangle.core.impl.TriangleOperationImpl;

/**
 * Class {@link TriangleOperationTest} unit-tests {@link TriangleOperation} and
 * {@link TriangleFactory}
 *
 * @author Erik Wessel
 */
public class TriangleOperationTest {

	TriangleOperation triangleOperation;

	@Before
	public void retrieveFactories() {
		triangleOperation = new TriangleOperationImpl();
	}

	@Test
	public void triangleOfTypeScalene() {
		Type type = SCALENE;
		int sl1 = 6466;
		int sl2 = 165;
		int sl3 = 6198;

		assertEquals(String.format("Wrong type for triangle with side lengths %s, %s, %s", sl1, sl2, sl3), type,
				triangleOperation.getTriangleTypeBySideLengths(sl1, sl2, sl3));
	}

	@Test
	public void triangleOfTypeScalene2() {
		Type type = SCALENE;
		int sl1 = 3;
		int sl2 = 4;
		int sl3 = 5;

		assertEquals(String.format("Wrong type for triangle with side lengths %s, %s, %s", sl1, sl2, sl3), type,
				triangleOperation.getTriangleTypeBySideLengths(sl1, sl2, sl3));
	}

	@Test
	public void triangleOfTypeIsosceles() {
		Type type = ISOSCELES;
		int sl1 = 1345;
		int sl2 = 64661;
		int sl3 = 64661;

		assertEquals(String.format("Wrong type for triangle with side lengths %s, %s, %s", sl1, sl2, sl3), type,
				triangleOperation.getTriangleTypeBySideLengths(sl1, sl2, sl3));
	}

	@Test
	public void triangleOfTypeIsosceles2() {
		Type type = ISOSCELES;
		int sl1 = 64661961;
		int sl2 = 1654;
		int sl3 = 64661961;

		assertEquals(String.format("Wrong type for triangle with side lengths %s, %s, %s", sl1, sl2, sl3), type,
				triangleOperation.getTriangleTypeBySideLengths(sl1, sl2, sl3));
	}

	@Test
	public void triangleOfTypeIsosceles3() {
		Type type = ISOSCELES;
		int sl1 = 543;
		int sl2 = 543;
		int sl3 = 9;

		assertEquals(String.format("Wrong type for triangle with side lengths %s, %s, %s", sl1, sl2, sl3), type,
				triangleOperation.getTriangleTypeBySideLengths(sl1, sl2, sl3));
	}

	@Test
	public void triangleOfTypeEquilateral() {
		Type type = EQUILATERAL;
		int sl1 = 16546416;
		int sl2 = 16546416;
		int sl3 = 16546416;

		assertEquals(String.format("Wrong type for triangle with side lengths %s, %s, %s", sl1, sl2, sl3), type,
				triangleOperation.getTriangleTypeBySideLengths(sl1, sl2, sl3));
	}

	@Test
	public void triangleOfTypeEquilateral2() {
		Type type = EQUILATERAL;
		int sl1 = 3;
		int sl2 = 3;
		int sl3 = 3;

		assertEquals(String.format("Wrong type for triangle with side lengths %s, %s, %s", sl1, sl2, sl3), type,
				triangleOperation.getTriangleTypeBySideLengths(sl1, sl2, sl3));
	}

	@Test
	public void triangleOutsideOfBoundaries() {
		try {
			triangleOperation.getTriangleTypeBySideLengths(-12, 12, 12);
			fail("Triangle with negative length don't exist");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void triangleOutsideOfBoundaries2() {
		try {
			triangleOperation.getTriangleTypeBySideLengths(12, -165, 234);
			fail("Triangle with negative length don't exist");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void triangleOutsideOfBoundaries3() {
		try {
			triangleOperation.getTriangleTypeBySideLengths(1612, 594, -942);
			fail("Triangle with negative length don't exist");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void triangleOutsideOfBoundaries4() {
		try {
			triangleOperation.getTriangleTypeBySideLengths(16919191, 162, 0);
			fail("Triangle with length zero don't exist");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void triangleOutsideOfBoundaries5() {
		try {
			triangleOperation.getTriangleTypeBySideLengths(0, 162, 0);
			fail("Triangle with length zero don't exist");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void triangleOutsideOfBoundaries6() {
		try {
			triangleOperation.getTriangleTypeBySideLengths(16919191, 0, 162);
			fail("Triangle with length zero don't exist");
		} catch (IllegalArgumentException e) {
		}
	}
}
