package edu.miu.waa.onlineShopping.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Buyer extends User {

	private int points;

	@OneToOne(cascade =	CascadeType.ALL)
	@JoinColumn(name = "cardPayment_id")
	private CardPayment cardPayment;

	@OneToMany(cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<Seller> followingSellers;

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

	public Set<Seller> getFollowingSellers() {
		return followingSellers;
	}

	public void setFollowingSellers(Set<Seller> followingSellers) {
		this.followingSellers = followingSellers;
	}
}
