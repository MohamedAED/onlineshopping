package edu.miu.waa.onlineShopping.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Buyer extends User {

	private int points;

	@OneToOne(cascade = { CascadeType.ALL })
	private CardPayment cardPayment;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "order_id")
	List<Order> orders;

	@ManyToMany
	@JoinTable(name = "seller_id")
	List<Seller> followingSellers;

	public Buyer() {
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public CardPayment getCardPayment() {
		return cardPayment;
	}

	public void setCardPayment(CardPayment cardPayment) {
		this.cardPayment = cardPayment;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Seller> getFollowingSellers() {
		return followingSellers;
	}

	public void setFollowingSellers(List<Seller> followingSellers) {
		this.followingSellers = followingSellers;
	}
}
