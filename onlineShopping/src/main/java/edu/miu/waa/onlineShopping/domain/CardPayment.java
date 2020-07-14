package edu.miu.waa.onlineShopping.domain;

import edu.miu.waa.onlineShopping.validator.CardExpirationDate;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class CardPayment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	@Size(min = 16, max = 16)
	private String cardNumber;

	@CardExpirationDate
	private String expiryDate;

	@NotEmpty
	@Size(min = 3, max = 3)
	private String cvv;

	@NotEmpty
	@Size(min = 5, max = 14)
	private String nameOnCard;

	public CardPayment() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

}
