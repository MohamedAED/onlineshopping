package edu.miu.waa.onlineShopping.domain;

import java.math.BigDecimal;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Map<Long, CartItem> cartItems;

    private BigDecimal totalPrice;

    public ShoppingCart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Long, CartItem> getItems() {
        return cartItems;
    }

    public void setItems(Map<Long, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void updateTotalPrice() {
    	totalPrice = new BigDecimal(0);
		for (CartItem item : cartItems.values()) {
			totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
		}
	}
    
    public void addCartItem(CartItem item) {
		Long productId = item.getProduct().getId();
		if (cartItems.containsKey(productId)) {
			CartItem existingCartItem = cartItems.get(productId);
			existingCartItem.setQuantity(existingCartItem.getQuantity() + item.getQuantity());
			cartItems.put(productId, existingCartItem);
		} else {
			cartItems.put(productId, item);
		}
		updateTotalPrice();
	}

	public void removeCartItem(CartItem item) {
		Long productId = item.getProduct().getId();
		cartItems.remove(productId);
		updateTotalPrice();
	}
}
