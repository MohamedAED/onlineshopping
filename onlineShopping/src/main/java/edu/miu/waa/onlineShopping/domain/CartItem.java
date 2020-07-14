package edu.miu.waa.onlineShopping.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CartItem {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
    
    private BigDecimal price;
    
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="plcaeOrder_id", nullable=true) PlaceOrder placeOrder;
	 */

    public CartItem() {
    }

	/*
	 * public PlaceOrder getPlaceOrder() { return placeOrder; }
	 * 
	 * 
	 * 
	 * public void setPlaceOrder(PlaceOrder placeOrder) { this.placeOrder =
	 * placeOrder; }
	 */



	public CartItem(Product product) {
		super();
		this.product = product;
		this.quantity = 1;
		this.price = product.getPrice();
	}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
    
	public BigDecimal evaluateTotalPrice() {
		return this.product.getPrice().multiply(new BigDecimal(this.quantity));
	}
}
