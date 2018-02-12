package adrianan.backend.api.customer.persistence;

import java.util.List;

import adrianan.backend.api.customer.Customer;

public interface CustomerPersistence {
	
	List<Customer> findAll();
	
	Customer findById(String id);
	
	Customer update(Customer customer);
	
	Customer create(Customer customer);
	
	void remove(String id);

}
