package edu.miu.waa.onlineShopping.controller;


import java.security.Principal;

import javax.validation.Valid;

import edu.miu.waa.onlineShopping.domain.Admin;
import edu.miu.waa.onlineShopping.domain.ShoppingCart;
import edu.miu.waa.onlineShopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import edu.miu.waa.onlineShopping.domain.*;
import edu.miu.waa.onlineShopping.domain.enums.*;
import edu.miu.waa.onlineShopping.service.*;

@Controller
@SessionAttributes({"UserRole","UserInfo"})
public class LoginController {

	@Autowired
	private SellerService sellerService;

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private AdminService adminService;

	@Autowired
	UserService userService;

	@RequestMapping("/goHome")
	public String goHome(Principal principal, Model model) {

		String pageURL="";
		User user = getUserByName(principal.getName());
		String role = user.getRole().toString().toLowerCase();
		if (user.getRole() == Role.SELLER) {
			pageURL = "forward:/seller/find";
			Seller seller = sellerService.findUserByUsername(principal.getName());
			model.addAttribute("UserInfo",seller);

		} else if (user.getRole() == Role.ADMIN) {
			pageURL = "AdminHomePage";
			Admin admin =  adminService.findUserByUsername(principal.getName());
			model.addAttribute("UserInfo",admin);
		} else if (user.getRole() == Role.BUYER){
			Buyer buyer =  buyerService.findUserByUsername(principal.getName());
			model.addAttribute("UserInfo",buyer);
			pageURL = "forward:/SignedIn?UserId=" + buyer.getUserId() +"&UserRole=" + role;
		}
		model.addAttribute("UserRole",role);

		return pageURL;

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

	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
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
			modelAndView.setViewName("login");
			return modelAndView;
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
			modelAndView.setViewName("login");
			return modelAndView;
		}
		modelAndView.addObject("buyer", buyer);
		modelAndView.addObject("type", "buyer");
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	/*
	* Added By Mohamed Saleh
	* */
	@GetMapping("/logout")
	public String Logout()
	{
		return "home";
	}

}
