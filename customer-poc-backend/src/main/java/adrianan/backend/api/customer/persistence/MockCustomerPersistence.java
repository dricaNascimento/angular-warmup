package adrianan.backend.api.customer.persistence;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Alternative;
import javax.inject.Named;

import adrianan.backend.api.customer.Customer;

@Alternative
@Named("mockCustomerPersistence")
public class MockCustomerPersistence implements CustomerPersistence{

	private List<Customer> customers;

	@PostConstruct
	public void init() {
		this.customers = new LinkedList<>();
		this.add(new Customer(null, "Luiz", "Recife", "+55 11 9 4388 4994"));
		this.add(new Customer(null, "Adriana", "Uruara", "+55 11 9 4388 4994"));
		this.add(new Customer(null, "Glaucio", "Escada", "+55 11 9 4388 4994"));
	}

	private Long lastId = 0L;

	private synchronized void add(Customer customer) {
		this.lastId = this.lastId + 1;
		customer.setCid(Long.toString(this.lastId));
		this.customers.add(customer);
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> result = new ArrayList<>(this.customers);
		return result;
	}

	@Override
	public Customer findById(String id) {
		Customer result = null;
		final int size = this.customers.size();
		int i = 0;
		while (result == null && i < size) {
			Customer customer = this.customers.get(i);
			if (customer.getCid().equals(id)) {
				result = customer;
			}
			i = i + 1;
		}
		return result;
	}

	@Override
	public Customer update(Customer customer) {
		Customer result = null;
		if (customer != null && customer.getCid() != null) {
			Customer old = this.findById(customer.getCid());
			if (old != null) {
				int index = this.customers.indexOf(old);
				this.customers.set(index, customer);
				result = customer;
			}
		}
		return result;
	}

	@Override
	public Customer create(Customer customer) {
		this.add(customer);
		return customer;
	}

	@Override
	public void remove(String id) {
		Customer customer = this.findById(id);
		if (customer != null) {
			this.customers.remove(customer);
		}
	}

}
