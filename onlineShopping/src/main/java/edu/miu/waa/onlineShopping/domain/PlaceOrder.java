package edu.miu.waa.onlineShopping.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import edu.miu.waa.onlineShopping.domain.enums.OrderStatus;

@Entity
public class PlaceOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String orderNumber;

	private BigDecimal totalPrice;

	private OrderStatus status;
	
	private LocalDate orderDate;

	private LocalDate orderDeliveryDate;
	
	private LocalDate orderShippingDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<CartItem> cartItems;

	public PlaceOrder(BigDecimal totalPrice, Set<CartItem> cartItems) {
		this.orderNumber = "1111";
		this.totalPrice = totalPrice;
		this.status = OrderStatus.PLACED;
		this.orderDate = LocalDate.now();
		this.cartItems = new HashSet<CartItem>();
		this.cartItems.addAll(cartItems);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getOrderDeliveryDate() {
		return orderDeliveryDate;
	}

	public void setOrderDeliveryDate(LocalDate orderDeliveryDate) {
		this.orderDeliveryDate = orderDeliveryDate;
	}

	public LocalDate getOrderShippingDate() {
		return orderShippingDate;
	}

	public void setOrderShippingDate(LocalDate orderShippingDate) {
		this.orderShippingDate = orderShippingDate;
	}

	public Set<CartItem> getItems() {
		return cartItems;
	}

	public void setItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
}
