package edu.miu.waa.onlineShopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CartHomeController {

	@RequestMapping
	public String goHome() {
		return "CartHome";
	}
	
}
