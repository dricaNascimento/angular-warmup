package adrianan.backend.api.customer;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Customer implements Serializable {

	private String cid;
	private String name;
	private String address;
	
	public Customer() {
		
	}

	public Customer(String cid, String name, String address) {
		super();
		this.cid = cid;
		this.name = name;
		this.address = address;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
