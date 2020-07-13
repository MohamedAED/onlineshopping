package edu.miu.waa.onlineShopping.config;

import edu.miu.waa.onlineShopping.domain.*;
import edu.miu.waa.onlineShopping.domain.enums.Role;
import edu.miu.waa.onlineShopping.domain.enums.UserStatus;
import edu.miu.waa.onlineShopping.service.AdminService;
import edu.miu.waa.onlineShopping.service.BuyerService;
import edu.miu.waa.onlineShopping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Component
public class OnApplicationStartUp {

	@Autowired
	private AdminService adminService;

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private SellerService sellerService;


	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) throws Exception {
		createAdminUser();
		createSeller();
		createBuyer();
	}

	private void createAdminUser() throws ParseException, IOException {
		Admin user = new Admin();
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setEmail("admin@miu.edu");
		user.setPhoneNumber("6418192921");
		user.setDateOfBirth(new SimpleDateFormat("MM/dd/yyyy").parse("03/22/1990"));
		user.setUsername("admin");
		user.setPassword("admin");
		user.setApproved(UserStatus.APPROVED);
		user.setRole(Role.ADMIN);
		adminService.saveUser(user);
	}

	private void createSeller() throws ParseException, IOException {
		Seller user = new Seller();
		user.setFirstName("seller");
		user.setLastName("seller");
		user.setEmail("seller@miu.edu");
		user.setPhoneNumber("6418192921");
		user.setDateOfBirth(new SimpleDateFormat("MM/dd/yyyy").parse("03/22/1990"));
		user.setUsername("seller");
		user.setPassword("seller");
		user.setApproved(UserStatus.APPROVED);
		user.setRole(Role.SELLER);
		sellerService.saveUser(user);
	}

	private void createBuyer() throws ParseException, IOException {
		Buyer user = new Buyer();
		user.setFirstName("buyer4");
		user.setLastName("buyer4");
		user.setEmail("buyer4@miu.edu");
		user.setPhoneNumber("6418192921");
		user.setDateOfBirth(new SimpleDateFormat("MM/dd/yyyy").parse("03/22/1990"));
		user.setUsername("mega_unknown");
		user.setPassword("123456789");
		user.setApproved(UserStatus.APPROVED);
		user.setRole(Role.BUYER);
		user.setShoppingCart(new ShoppingCart());
		CardPayment cardPayment = new CardPayment();
		cardPayment.setCardNumber("1234567812345678");
		cardPayment.setExpiryDate("09/24");
		cardPayment.setCvv("123");
		cardPayment.setNameOnCard("omar albaarah");
		user.setCardPayment(cardPayment);
		ShippingAddress address = new ShippingAddress();
		address.setCountry("usa");
		address.setState("iowa");
		address.setCity("fairfield");
		address.setStreet("1000 north");
		address.setZipCode("52557");
		user.setShippingAddress(address);
		BillingAddress billingAddress = new BillingAddress();
		billingAddress.setCountry("usa");
		billingAddress.setState("iowa");
		billingAddress.setCity("fairfield");
		billingAddress.setStreet("1000 north");
		billingAddress.setZipCode("52557");
		user.setBillingAddress(billingAddress);
		buyerService.saveUser(user);
	}


}
