package adrianan.backend.api.customer;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(CustomerSessionBean.class);
		classes.add(RestApplication.class);
		classes.add(CORSFilter.class);
		return classes;
	}
}
