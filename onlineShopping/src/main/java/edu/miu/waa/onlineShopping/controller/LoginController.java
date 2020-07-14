package edu.miu.waa.onlineShopping.controller;


import java.security.Principal;

import javax.validation.Valid;

import edu.miu.waa.onlineShopping.domain.Admin;
import edu.miu.waa.onlineShopping.domain.ShoppingCart;
import edu.miu.waa.onlineShopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

		String role ="";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String pageURL="";

		if(sellerService.findUserByUsername(principal.getName()) != null){
			role = "seller";
			pageURL = "forward:/seller/find";
			Seller seller = sellerService.findUserByUsername(principal.getName());
			model.addAttribute("UserInfo",seller);
		}
		else if (adminService.findUserByUsername(principal.getName()) != null){
			role = "admin";
			pageURL = "AdminHomePage";
			Admin admin =  adminService.findUserByUsername(principal.getName());
			model.addAttribute("UserInfo",admin);
		}
		else if (buyerService.findUserByUsername(principal.getName()) != null){

			Buyer buyer =  buyerService.findUserByUsername(principal.getName());
			model.addAttribute("UserInfo",buyer);
			role = "buyer";
			pageURL = "forward:/SignedIn?UserId=" + buyer.getUserId() +"&UserRole=" + role;
		}
		model.addAttribute("UserRole",role);

		return pageURL;
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
			bindingResult.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (!bindingResult.hasErrors()) {
			seller.setApproved(UserStatus.PENDING);

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
			bindingResult.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		if (!bindingResult.hasErrors()) {
			buyer.setApproved(UserStatus.PENDING);
			buyer.setShoppingCart(new ShoppingCart());
			buyerService.saveUser(buyer);
			modelAndView.addObject("successMessage", "User has been registered successfully");
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
