package com.example.jpetstore.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
public class SignoffController { 
	@RequestMapping("/shop/signoff.do")
	public String handleRequest(HttpSession session) throws Exception {
		System.out.println("testurl 4");
		session.removeAttribute("userSession");
		session.invalidate();
		
		System.out.println("testurl index 2");
		//return "tiles/index";
		return "redirect:/shop/index.do";
	}
}
