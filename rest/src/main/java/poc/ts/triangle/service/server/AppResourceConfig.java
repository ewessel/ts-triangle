package poc.ts.triangle.service.server;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import poc.ts.triangle.core.TriangleOperation;
import poc.ts.triangle.core.impl.TriangleOperationImpl;

/**
 * Configuration of the application
 *
 * @author Erik Wessel
 */
public class AppResourceConfig extends ResourceConfig {

	public AppResourceConfig() {
		// Loading resources from given package
		packages("poc.ts.triangle.service.rest");
		// Bind dependency service
		register(new AbstractBinder() {

			@Override
			protected void configure() {
				bind(TriangleOperationImpl.class).to(TriangleOperation.class);
			}
		});
	}

}
