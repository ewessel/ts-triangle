/**
 *
 */
package poc.ts.triangle.service.rest;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import poc.ts.triangle.core.TriangleOperation;
import poc.ts.triangle.core.entity.Triangle.Type;

/**
 * Class {@link TriangleOperationService} provides a REST service for triangle
 * operation
 *
 * @author Erik Wessel
 */
@Path("triangle/operation")
public class TriangleOperationService {

	private static final Logger logger = LogManager.getLogger(TriangleOperationService.class);

	@Inject
	private TriangleOperation triangleOperation;

	public TriangleOperationService() {
	}

	@GET
	@Path("typeBySideLengths/{sideLength1}/{sideLength2}/{sideLength3}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTriangleTypeBySideLengths(
			@PathParam("sideLength1") @Min(value = 1, message = "Side length 1 must be positive") int sideLength1,
			@PathParam("sideLength2") @Min(value = 1, message = "Side length 2 must be positive") int sideLength2,
			@PathParam("sideLength3") @Min(value = 1, message = "Side length 3 must be positive") int sideLength3) {

		Type type = triangleOperation.getTriangleTypeBySideLengths(sideLength1, sideLength2, sideLength3);
		if (logger.isInfoEnabled()) {
			logger.info(String.format("Computing triangle type with side lengths: %s, %s, %s. Found %s", sideLength1,
					sideLength2, sideLength3, type));
		}
		return type.name();
	}
}
