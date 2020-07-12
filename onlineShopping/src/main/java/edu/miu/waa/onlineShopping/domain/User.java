package edu.miu.waa.onlineShopping.domain;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.validation.constraints.*;
import edu.miu.waa.onlineShopping.domain.enums.UserStatus;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import org.springframework.format.annotation.DateTimeFormat;
import edu.miu.waa.onlineShopping.domain.enums.Role;


@MappedSuperclass
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	private UserStatus approved;

	@Column(name = "first_name")
	@NotBlank
	private String firstName;

	@Column(name = "last_name")
	@NotBlank
	private String lastName;

	@Column(name = "date_of_birth")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dateOfBirth;

	@Column(name = "email")
	@Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,3})$", message = "* please provide a valid email")
	private String email;

	@Column(name = "phone_number")
	@Pattern(regexp = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$", message = "* please provide valid phone number")
	private String phoneNumber;

	@Column(name = "user_name", unique = true)
	@Size(min = 5, max = 14, message = "{Size.name}")
	private String username;

	@Column(name = "password")
	@NotBlank
	@Size(min = 5)
	private String password;

	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<PlaceOrder> orders;
	
	@Transient
	private String passwordCheck;

	@Column(name = "role")
	private Role role;

	public User() {
	}

	public Set<PlaceOrder> getOrders() {
		return orders;
	}

	public void setOrders(Set<PlaceOrder> orders) {
		this.orders = orders;
	}

	public UserStatus getApproved() {
		return approved;
	}


	public void setApproved(UserStatus approved) {
		this.approved = approved;
	}

	public String getName()
	{
		return lastName+", " + firstName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

}
