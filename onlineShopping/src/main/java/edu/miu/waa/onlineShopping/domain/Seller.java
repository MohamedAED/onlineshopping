package edu.miu.waa.onlineShopping.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Seller extends User {

	private Boolean approved = false;

	@OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
	List<Product> products;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	List<Order> orders;

	public Seller() {
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
