package edu.miu.waa.onlineShopping.controller;


import edu.miu.waa.onlineShopping.domain.*;
import edu.miu.waa.onlineShopping.domain.enums.*;
import edu.miu.waa.onlineShopping.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@SessionAttributes("user")
public class LoginController {

	@Autowired
	private SellerService sellerService;

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private AdminService adminService;

	@RequestMapping("/goHome")
	public String goHome(Principal principal, Model model) {
		User user = getUserByName(principal.getName());
		if (user.getRole() == Role.SELLER) {
		} else if (user.getRole() == Role.ADMIN) {
		} else {
		}
		model.addAttribute("role", user.getRole().toString().toLowerCase());
		return "AdminHomePage";
	}
	public User getUserByName(String username){
		User user;
		if (sellerService.findUserByUsername(username) != null) {
			user = sellerService.findUserByUsername(username);
		} else if (adminService.findUserByUsername(username) != null) {
			user = adminService.findUserByUsername(username);
		} else {
			user = buyerService.findUserByUsername(username);
		}
		return user;
	}

	@RequestMapping(value={"/loginPage"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("loginPage");
		return modelAndView;
	}

	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(@RequestParam("type") String typeOfRegistration){
		ModelAndView modelAndView = new ModelAndView();
		Seller seller = new Seller();
		Buyer buyer = new Buyer();
		modelAndView.addObject("seller", seller);
		modelAndView.addObject("buyer", buyer);
		modelAndView.addObject("type", typeOfRegistration);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	@RequestMapping(value = "/registration_seller", method = RequestMethod.POST)
	public ModelAndView createNewSeller(@Valid Seller seller, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		seller.setRole(Role.SELLER);
		if (sellerService.findUserByUsername(seller.getUsername()) != null) {
			bindingResult.rejectValue("username", "error.user",
							"There is already a user registered with the provided username");
		}
		if (!bindingResult.hasErrors()) {
			seller.setApproved(UserStatus.PENDING);
			seller.setUsername(seller.getUsername().toLowerCase());
			sellerService.saveUser(seller);
			modelAndView.addObject("successMessage", "User has been registered successfully");
		}
		modelAndView.addObject("seller", seller);
		modelAndView.addObject("type", "seller");
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration_buyer", method = RequestMethod.POST)
	public ModelAndView createNewBuyer(@Valid Buyer buyer, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		buyer.setRole(Role.BUYER);
		if (buyerService.findUserByUsername(buyer.getUsername()) != null) {
			bindingResult.rejectValue("username", "error.user",
					"There is already a user registered with the provided username");
		}
		if (!bindingResult.hasErrors()) {
			buyer.setApproved(UserStatus.PENDING);
			buyer.setShoppingCart(new ShoppingCart());
			buyer.setUsername(buyer.getUsername().toLowerCase());
			buyerService.saveUser(buyer);
			modelAndView.addObject("successMessage", "User has been registered successfully");
		}
		modelAndView.addObject("buyer", buyer);
		modelAndView.addObject("type", "buyer");
		modelAndView.setViewName("registration");
		return modelAndView;
	}

}
