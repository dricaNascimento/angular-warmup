package adrianan.backend.api.customer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import adrianan.backend.api.customer.persistence.CustomerPersistence;

@Path("/customers")
@Singleton
@Startup
public class CustomerSessionBean {
	
	private static final Logger logger = Logger.getLogger(CustomerSessionBean.class.getName());
	
    @Inject
    private CustomerPersistence customerPersistence;

	@PostConstruct
	public void init() {
		
	}

	@PreDestroy
	public void destroy() {
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getCustomers() {
		List<Customer> result = this.customerPersistence.findAll();
		return result;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerById(@PathParam("id") String id) {
		Customer result = this.customerPersistence.findById(id);
		return result;
	}

	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Customer newCustomer(Customer customer) {
		Customer result = null;
		if (customer != null) {
			result = this.customerPersistence.create(customer);
			logger.log(Level.INFO, "added customer: " + customer.getName());
		}
		return result;
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteCustomer(@PathParam("id") String id) {
		if (id != null) {
			this.customerPersistence.remove(id);
			logger.log(Level.INFO, "removed customer with id " + id);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Customer updateCustomer(Customer customer) {
		Customer result = null;
		if (customer != null && customer.getCid() != null) {
			result = this.customerPersistence.update(customer);
			logger.log(Level.INFO, "updated " + customer.getName());
		}
		return result;

	}

}
