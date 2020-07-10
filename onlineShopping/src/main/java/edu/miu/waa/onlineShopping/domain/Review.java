package edu.miu.waa.onlineShopping.domain;

import edu.miu.waa.onlineShopping.domain.enums.ReviewStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String description;

	private ReviewStatus reviewStatus;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "buyer_id")
	private Buyer buyer;

	public Review(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(ReviewStatus reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
}
