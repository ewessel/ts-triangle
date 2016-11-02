package poc.ts.triangle.service.rest;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static poc.ts.triangle.core.entity.Triangle.Type.EQUILATERAL;
import static poc.ts.triangle.core.entity.Triangle.Type.ISOSCELES;
import static poc.ts.triangle.core.entity.Triangle.Type.SCALENE;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import poc.ts.triangle.core.entity.Triangle;
import poc.ts.triangle.service.server.AppResourceConfig;

/**
 * Class {@link TriangleOperationServiceTest} tests the REST resource serving
 * triangle operation
 *
 * @author Erik Wessel
 */
public class TriangleOperationServiceTest extends JerseyTest {

	private final static Logger logger = LogManager.getLogger(TriangleOperationServiceTest.class);

	@Override
	protected Application configure() {
		return new AppResourceConfig();
	}

	@Test
	public void triangleOfTypeScalene() {
		requestTypeBySideLengths("12/159/19", OK, SCALENE);

		requestTypeBySideLengths("946916/94916/8998", OK, SCALENE);

		requestTypeBySideLengths("8998/64894/898", OK, SCALENE);
	}

	@Test
	public void triangleOfTypeIsosceles() {
		requestTypeBySideLengths("12/159/159", OK, ISOSCELES);

		requestTypeBySideLengths("946916/946916/8998", OK, ISOSCELES);

		requestTypeBySideLengths("8998/64894/8998", OK, ISOSCELES);
	}

	@Test
	public void triangleOfTypeEquilateral() {
		requestTypeBySideLengths("12/12/12", OK, EQUILATERAL);

		requestTypeBySideLengths("64661961/64661961/64661961", OK, EQUILATERAL);
	}

	@Test
	public void triangleOutsideOfBoundaries() {
		requestTypeBySideLengths("-12/12/12", BAD_REQUEST, null);

		requestTypeBySideLengths("12/-1651694/2", BAD_REQUEST, null);

		requestTypeBySideLengths("169191912/16694/-942", BAD_REQUEST, null);

		requestTypeBySideLengths("0/165194/-942", BAD_REQUEST, null);

		requestTypeBySideLengths("1694/0/94", BAD_REQUEST, null);
	}

	@Test
	public void toFewArguments() {
		requestTypeBySideLengths("12/64", NOT_FOUND, null);

		requestTypeBySideLengths("1651624", NOT_FOUND, null);
	}

	@Test
	public void toMuchArguments() {
		requestTypeBySideLengths("12/64/12/64", NOT_FOUND, null);

		requestTypeBySideLengths("16516694/12/64/12/64", NOT_FOUND, null);
	}

	@Test
	public void stringArguments() {
		requestTypeBySideLengths("12//12", NOT_FOUND, null);

		requestTypeBySideLengths("/-165162.694/2", NOT_FOUND, null);

		requestTypeBySideLengths("169191912/165162.694/vvrd", NOT_FOUND, null);
	}

	// Utils

	/**
	 * Utility method allowing to call the REST resource with the given
	 * argument, and checking the response's HTTP status and value against the
	 * given expected arguments
	 *
	 * @param arg
	 *            the argument to be passed to the request URI
	 * @param expectedResponseStatus
	 *            the expected HTTP response status
	 * @param expectedType
	 *            the expected triangle type
	 */
	private void requestTypeBySideLengths(String arg, Status expectedResponseStatus, Triangle.Type expectedType) {
		WebTarget target = target("/triangle/operation/typeBySideLengths/" + arg);
		Response response = target.request().get();

		int actualResponseStatus = response.getStatus();
		String actualResponseValue = response.readEntity(String.class);
		assertEquals("Actual response status is wrong", expectedResponseStatus.getStatusCode(), actualResponseStatus);
		if (expectedType != null) {
			assertTrue("Actual response doesn't match expected value " + expectedType,
					actualResponseValue != null && actualResponseValue.equals(expectedType.name()));
		}
		if (logger.isDebugEnabled()) {
			logger.debug("For request: " + target.getUri() + ", response is: status=" + actualResponseStatus
					+ (actualResponseValue != null ? ", value=" + actualResponseValue : ""));
		}
	}
}
