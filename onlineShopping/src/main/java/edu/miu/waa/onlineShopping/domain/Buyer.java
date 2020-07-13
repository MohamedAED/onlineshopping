package edu.miu.waa.onlineShopping.domain;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.validation.Valid;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Buyer extends User {

	private int points;

	@Valid
	@OneToOne(cascade =	CascadeType.ALL)
	@JoinColumn(name = "cardPayment_id")
	private CardPayment cardPayment;

	@OneToMany(cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<Seller> followingSellers;

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "billing_address_id")
	private BillingAddress billingAddress;

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shipping_address_id")
	private ShippingAddress shippingAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shoppingCart_id")
	private ShoppingCart shoppingCart;
	
	public Buyer() {
	}

	public BillingAddress getBillingAddress() {
		return billingAddress;
	}
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}
	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public CardPayment getCardPayment() {
		return cardPayment;
	}
	public void setCardPayment(CardPayment cardPayment) {
		this.cardPayment = cardPayment;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public Set<Seller> getFollowingSellers() {
		return followingSellers;
	}
	public void setFollowingSellers(Set<Seller> followingSellers) {
		this.followingSellers = followingSellers;
	}
}
