package edu.miu.waa.onlineShopping.domain;

import java.util.Set;
import javax.persistence.*;
import javax.validation.Valid;

@Entity
public class Seller extends User {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable
	private Set<Product> products;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable
	private Set<PlaceOrder> orders;

	@ManyToMany(mappedBy = "followingSellers")
	private Set<Buyer> followedSeller;


	public Seller() {
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Set<PlaceOrder> getOrders() {
		return orders;
	}

	public void setOrders(Set<PlaceOrder> orders) {
		this.orders = orders;
	}
	
	

}
