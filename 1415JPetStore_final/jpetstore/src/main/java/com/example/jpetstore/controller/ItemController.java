package com.example.jpetstore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.example.jpetstore.service.ItemValidator;
import com.example.jpetstore.service.PetStoreFacade;

@Controller
@SessionAttributes("itemForm")
public class ItemController {
	@Autowired
	private PetStoreFacade petStore;
	
	@Autowired
	private ItemValidator itemValidator;
	
	@ModelAttribute("itemForm") 
	public ItemForm createItemForm() {
		return new ItemForm();
	}
	
	@ModelAttribute("categoryNames")  
	public List<String> referenceData() {
		ArrayList<String> categoryNames = new ArrayList<String>();
		categoryNames.add("FISH");
		categoryNames.add("DOGS");
		categoryNames.add("REPTILES");
		categoryNames.add("CATS");
		categoryNames.add("BIRDS");
		
		return categoryNames;
	}
	
	@ModelAttribute("productNames") 
	public List<String> referenceData2() {
		ArrayList<String> productNames = new ArrayList<String>();
		productNames.add("Angelfish");
		productNames.add("Tiger Shark");
		productNames.add("Koi");
		productNames.add("Goldfish");
		productNames.add("Bulldog");
		productNames.add("Poodle");
		productNames.add("Dalmation");
		productNames.add("Golden Retriever");
		productNames.add("Labrador Retriever");
		productNames.add("Chihuahua");
		productNames.add("Rattlesnake");
		productNames.add("Iguana");
		productNames.add("Manx");
		productNames.add("Persian");
		productNames.add("Amazon Parrot");
		productNames.add("Finch");

		return productNames;
	}	
	/*0622 수정*/
	@RequestMapping("/shop/showPorA.do") 
	public String showPorA(HttpServletRequest request) throws ModelAndViewDefiningException {
		return "PorA";
		
	}	
	
	@RequestMapping("/shop/addItem.do") 
	public String addNewItem(HttpServletRequest request,
			@ModelAttribute("itemForm") ItemForm itemForm
			) throws ModelAndViewDefiningException {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		if(userSession != null) { 
			return "NewItemForm";
		}
		else { 
			ModelAndView modelAndView = new ModelAndView("Error");
			modelAndView.addObject("message", "물품을 등록하시려면 먼저 로그인하세요");
//			System.out.println(modelAndView);
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}
	
	@RequestMapping("/shop/newItemSubmitted.do")
	public String bindAndValidateOrder(HttpServletRequest request,
			@ModelAttribute("itemForm") ItemForm itemForm,
			BindingResult result) {
		UserSession userSession = (UserSession)request.getSession().getAttribute("userSession");
		itemForm.getItem().setSellerUsername(userSession.getAccount().getUsername());
		itemForm.getItem().setKind("P");
		itemValidator.validateItemFields(itemForm.getItem(), result);

		if(result.hasErrors()) {
			return "NewItemForm";
		}
		else {
			return "confirmAddItem";
		}
	}
	
	@RequestMapping("/shop/confirmAddItem.do")
	protected ModelAndView confirmAddItem(
			@ModelAttribute("itemForm") ItemForm itemForm,
			SessionStatus status) {
		//�떎�젣 �궫�엯 
		petStore.insertItem(itemForm.getItem());
		ModelAndView mav =  new ModelAndView("ViewAddedItem");
		mav.addObject("addedItem", itemForm.getItem());
		mav.addObject("message", "Thank you, your item has been added.");
		status.setComplete();
		return mav;
	}
	
	/**
	 * Added June 1st, 2018
	 * Showing list of my selling items
	 */
	@RequestMapping("/shop/listSellingItems.do")
	public ModelAndView handleRequest(HttpServletRequest request) throws Exception {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		if(userSession != null) {
			String username = userSession.getAccount().getUsername();
			//System.out.println(username + " hello!");
			ModelAndView mav = new ModelAndView("ListSellingItems");
			mav.addObject("sellingItemList", petStore.getSellingItemListBySellerUsername(username));
			mav.addObject("sellingAuctionItemList", petStore.getSellingAuctionItemListBySellerUsername(username));
			return mav;
		} else {
			ModelAndView modelAndView = new ModelAndView("Error");
			modelAndView.addObject("message", "세션 오류. 재로그인 하세요");
			//System.out.println(modelAndView);
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}
	
	/**
	 * Added June 22
	 * Delete item
	 */
	@RequestMapping("/shop/removeSellingItem.do")
	public ModelAndView removeSellingItem(
			@RequestParam("workingItemId")String workingItemId,
			HttpServletRequest request
			) throws Exception {
		petStore.deleteItem(workingItemId);
		
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		if(userSession != null) {
			String username = userSession.getAccount().getUsername();
			//System.out.println(username + " hello!");
			ModelAndView mav = new ModelAndView("ListSellingItems");
			mav.addObject("sellingItemList", petStore.getSellingItemListBySellerUsername(username));
			mav.addObject("sellingAuctionItemList", petStore.getSellingAuctionItemListBySellerUsername(username));
			return mav;
		}
		else {
			ModelAndView modelAndView = new ModelAndView("Error");
			modelAndView.addObject("message", "데이터를 불러오는 중에 오류가 생겼습니다.");
			//System.out.println(modelAndView);
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}
	
	/**
	 * Added June 23
	 * Edit item
	 */
	String tempWorkingItemId = "";
	@RequestMapping("/shop/editSellingItem.do")
	public ModelAndView editSellingItem(
			@RequestParam("workingItemId") String workingItemId,
			@ModelAttribute("editItemForm") EditItemForm editItemForm
			) throws Exception {
		tempWorkingItemId = workingItemId;
		ModelAndView mav = new ModelAndView("EditItemForm");
		mav.addObject("editItemForm", editItemForm);
		mav.addObject("editItem", petStore.getItem(workingItemId));
		return mav;
	}
	
	@RequestMapping("/shop/editItemSubmitted.do")
	public ModelAndView bindAndValidateEditItem(
			@ModelAttribute("editItemForm") EditItemForm editItemForm,
			BindingResult result,
			HttpServletRequest request
			) {
		itemValidator.validateItemFields(editItemForm.getItem(), result);
		
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		String username = userSession.getAccount().getUsername();
		if(result.hasErrors()) {
			ModelAndView mav = new ModelAndView("EditItemForm");
			mav.addObject("editItemForm", editItemForm);
			mav.addObject("editItem", petStore.getItem(editItemForm.getItem().getItemId()));
			return mav;
		} else { 
			petStore.updateItem(editItemForm.getItem());
			ModelAndView mav = new ModelAndView("ListSellingItems");
			mav.addObject("sellingItemList", petStore.getSellingItemListBySellerUsername(username));
			mav.addObject("sellingAuctionItemList", petStore.getSellingAuctionItemListBySellerUsername(username));
			return mav;
			//System.out.println("no errors");
//			return mav;
		}
	}
	
}
