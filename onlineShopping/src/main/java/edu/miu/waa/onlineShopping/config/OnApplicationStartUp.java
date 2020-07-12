package edu.miu.waa.onlineShopping.config;

import edu.miu.waa.onlineShopping.domain.*;
import edu.miu.waa.onlineShopping.domain.enums.Role;
import edu.miu.waa.onlineShopping.domain.enums.UserStatus;
import edu.miu.waa.onlineShopping.repositry.AdminRepository;
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
import java.util.Date;
import java.util.List;

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
//		List<User> users = userRepo.findAll();
//		Long count = users.stream().filter(userElm -> userElm.getUsername().equalsIgnoreCase("admin")).count();
//		if (count > 0)
//			return;
		createAdminUser();
		createSeller();
//		createBuyer();

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
		user.setRole(Role.ADMIN);
		sellerService.saveUser(user);

//		Product product4 = new Product();
//		product4.setName("Pepsi");
//		product4.setSeller(user);
//		product4.setDescription("Best Pepsi");
//		product4.setQuantity(new Long(2));
//		product4.setPrice(new Double(90));
//		product4.setPhoto(productPhoto());
//		productRepository.save(product4);
//
//		Product product5 = new Product();
//		product5.setName("Instant Pot");
//		product5.setSeller(user);
//		product5.setDescription("great healthy one-pot meals");
//		product5.setQuantity(new Long(2));
//		product5.setPrice(new Double(120));
//		product5.setPhoto(productPhoto());
//		productRepository.save(product5);
	}

	private void createBuyer() throws ParseException, IOException {
		Buyer user = new Buyer();
		user.setFirstName("buyer");
		user.setLastName("buyer");
		user.setEmail("buyer@miu.edu");
		user.setPhoneNumber("6418192921");
		user.setDateOfBirth(new SimpleDateFormat("MM/dd/yyyy").parse("03/22/1990"));
		user.setUsername("buyer");
		user.setPassword("buyer");
		user.setApproved(UserStatus.APPROVED);
		user.setRole(Role.ADMIN);

		Date date = new Date();
		date.setYear(2022);
		date.setMonth(9);
		CardPayment cardPayment = new CardPayment();
		cardPayment.setCardNumber("123456781234");
		cardPayment.setExpiryDate(date);
		cardPayment.setCvv("123");
		cardPayment.setNameOnCard("omar albaarah");
		user.setCardPayment(cardPayment);

		Address address = new Address();
		address.setCountry("usa");
		address.setState("iowa");
		address.setCity("fairfield");
		address.setStreet("1000 north");
		address.setZipCode("52557");
		user.setAddress(address);
		buyerService.saveUser(user);


//		Review review = new Review();
//		review.setReview("nice one");
//		review.setBuyer(user);
//		reviewService.save(review);
	}
}