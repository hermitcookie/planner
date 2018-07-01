package com.example.jpetstore.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

import com.example.jpetstore.dao.bidDao;
import com.example.jpetstore.service.PetStoreFacade;

@Controller
@SessionAttributes("bidForm")
public class BidController {
	
	
	@Autowired
	private PetStoreFacade petStore;
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}	
	
	@ModelAttribute("userId")
	public String getUserId(HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
			return userSession.getAccount().getUsername();
	}
	
	@ModelAttribute("bidForm")
	public bidForm createBidForm() {
		return new bidForm();
	}

	@RequestMapping("/shop/makeBid.do")
	public ModelAndView makeBid(HttpServletRequest request,
			@RequestParam("workingItemId") String workingItemId, 
			@ModelAttribute("bidForm") bidForm bidForm ){
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		bidForm.getBid().setUserId(userSession.getAccount().getUsername());
		bidForm.getBid().setItemId(workingItemId);
		
		ModelAndView mav = new ModelAndView("makeBid");
		mav.addObject("bidForm", bidForm);
		mav.addObject("newBid", bidForm.getBid());
		return mav;
	}


	@RequestMapping("/shop/submitBid.do")
	protected ModelAndView submitBid(HttpServletRequest request,
			@ModelAttribute("bidForm") bidForm bidForm,
			SessionStatus status) {
//		System.out.println(bidForm.getBid().getBidPrice() + ", " + bidForm.getBid().getUserId() + ", " + bidForm.getBid().getItemId());
		
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		
		petStore.insertBid(bidForm.getBid());
		
		ModelAndView mav =  new ModelAndView("submitBid");
		mav.addObject("addedBid", bidForm.getBid());
		mav.addObject("message", "Thank you, your bid has been added.");
		status.setComplete();
		return mav;
	}
	
	
}
