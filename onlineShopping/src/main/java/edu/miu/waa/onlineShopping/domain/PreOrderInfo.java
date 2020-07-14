package edu.miu.waa.onlineShopping.domain;

import java.io.Serializable;

public class PreOrderInfo implements Serializable {

	private static final long serialVersionUID = -908L;
	
	private ShippingAddress shippingAddress;
	private BillingAddress billingAddress;
	private CardPayment cardPayment;
	private ShoppingCart shoppingCart;
	private Buyer buyer;
	private String paymentType;
	private String cardNumberTruncated;
	private int requiredPoints;
	
	public PreOrderInfo() {
		super();
	}

	public PreOrderInfo(ShippingAddress shippingAddress, BillingAddress billingAddress, CardPayment cardPayment,
			ShoppingCart shoppingCart, Buyer buyer, String paymentType, String cardNumberTruncated, int requiredPoints) {
		super();
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.cardPayment = cardPayment;
		this.shoppingCart = shoppingCart;
		this.buyer = buyer;
		this.paymentType = paymentType;
		this.cardNumberTruncated = cardNumberTruncated;
		this.requiredPoints = requiredPoints;
	}
	
	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public BillingAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	public CardPayment getCardPayment() {
		return cardPayment;
	}

	public void setCardPayment(CardPayment cardPayment) {
		this.cardPayment = cardPayment;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCardNumberTruncated() {
		return cardNumberTruncated;
	}

	public void setCardNumberTruncated(String cardNumberTruncated) {
		this.cardNumberTruncated = cardNumberTruncated;
	}

	public int getRequiredPoints() {
		return requiredPoints;
	}

	public void setRequiredPoints(int requiredPoints) {
		this.requiredPoints = requiredPoints;
	}
	
}
