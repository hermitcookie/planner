package com.example.jpetstore.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
@SessionAttributes("itemFormAuction")
public class ItemAuctionController {
	@Autowired
	private PetStoreFacade petStore;
	
	@Autowired
	private ItemValidator itemValidator;
	
	@ModelAttribute("itemFormAuction")
	public AuctionItemForm createItemForm() {
		return new AuctionItemForm();
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

	@RequestMapping("/shop/addAuctionItem.do")
	public String addNewItem(HttpServletRequest request,
			@ModelAttribute("itemFormAuction") AuctionItemForm AuctionItemForm 
			) throws ModelAndViewDefiningException {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		if(userSession != null) { 
			return "NewItemFormAuction";
		}
		else { 
			ModelAndView modelAndView = new ModelAndView("Error");
			modelAndView.addObject("message", "물품을 등록하시려면 먼저 로그인하세요.");
//			System.out.println(modelAndView);
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}
	
	@RequestMapping("/shop/newItemSubmitted2.do")
	public String bindAndValidateOrder(HttpServletRequest request,
			@ModelAttribute("itemFormAuction") AuctionItemForm AuctionItemForm,
			BindingResult result) {
		UserSession userSession = (UserSession)request.getSession().getAttribute("userSession");
		AuctionItemForm.getItem().setSellerUsername(userSession.getAccount().getUsername());
		itemValidator.validateItemFields(AuctionItemForm.getItem(), result);
		if(result.hasErrors()) {
			return "NewItemFormAuction";
		}
		else {
//			System.out.println(AuctionItemForm.getItem().getAttribute1());
			AuctionItemForm.getItem().setKind("A");
			return "confirmAddItemAuction";
		}
	}
	@RequestMapping("/shop/confirmAddAuctionItem.do")
	protected ModelAndView confirmAddAuctionItem(
			@ModelAttribute("itemFormAuction") AuctionItemForm AuctionItemForm,
			SessionStatus status) {
		Date closingTime = AuctionItemForm.getItem().getEndDate();
		
		/** 스케줄러 실행부분**/
		/** 스케줄러 실행부분**/
		/** 스케줄러 실행부분**/
		petStore.auctionInsertItem(AuctionItemForm.getItem());
		petStore.AuctionScheduler(closingTime);
		
		ModelAndView mav =  new ModelAndView("ViewAddedAuctionItem");
		mav.addObject("addedItem", AuctionItemForm.getItem());
		mav.addObject("message", "Thank you, your Auction item has been added.");
		status.setComplete();
		return mav;
	}
	
	/*0624 추가 */
	@RequestMapping("/shop/listSellingAuctionItems.do")
	public ModelAndView handleRequest(HttpServletRequest request) throws Exception {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		if(userSession != null) {
			String username = userSession.getAccount().getUsername();
//			System.out.println(username + " hello!");
			return new ModelAndView("ListAuctionItems", "sellingItemList", 
					petStore.getSellingItemList());
		} else {
			ModelAndView modelAndView = new ModelAndView("Error");
			modelAndView.addObject("message", "경매장을 이용하시려면 먼저 로그인하세요 ");
//			System.out.println(modelAndView);
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}
	
	@RequestMapping("/shop/removeSellingAuctionItem.do")
	public ModelAndView removeSellingItem(
			@RequestParam("itemId")String itemId,
			HttpServletRequest request
			) throws Exception {
		petStore.deleteAuctionItem(itemId);
		
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		if(userSession != null) {
			String username = userSession.getAccount().getUsername();
//			System.out.println(username + " hello!");
			return new ModelAndView("ListAuctionItems", "sellingItemList", 
					petStore.getSellingItemList());
		}
		//ModelAndView mav = new ModelAndView("RemoveSellingItem");
		//mav.addObject("message", "Thank you, your item has been deleted.");
		else {
			ModelAndView modelAndView = new ModelAndView("Error");
			modelAndView.addObject("message", "데이터를 불러오는 중에 오류가 생겼습니다.");
//			System.out.println(modelAndView);
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}
	
	@RequestMapping("/shop/editAuctionItem.do")
	public ModelAndView editAuctionItem(
			@RequestParam("itemId")String itemId,
			@ModelAttribute("auctionItemForm") AuctionItemForm auctionItemForm
			) throws Exception {
		ModelAndView mav = new ModelAndView("EditAuctionItemForm");
		mav.addObject("auctionItemForm", auctionItemForm);
		mav.addObject("editAuctionItem", petStore.getAuctionItem(itemId));
		return mav;
	}
	
	@RequestMapping("/shop/editAuctionItemSubmitted.do")
	public ModelAndView bindAndValidateEditItem(
			@ModelAttribute("auctionItemForm") AuctionItemForm auctionItemForm,
			BindingResult result,
			HttpServletRequest request
			) {
		itemValidator.validateItemFields(auctionItemForm.getItem(), result);
		
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		String username = userSession.getAccount().getUsername();
		if(result.hasErrors()) {
			ModelAndView mav = new ModelAndView("EditItemForm");
			mav.addObject("auctionItemForm", auctionItemForm);
			mav.addObject("editAuctionItem", petStore.getAuctionItem(auctionItemForm.getItem().getItemId()));
			return mav;
		} else { 
			petStore.updateItem(auctionItemForm.getItem());
			ModelAndView mav = new ModelAndView("ListSellingItems");
			mav.addObject("sellingItemList", petStore.getSellingItemListBySellerUsername(username));
			mav.addObject("sellingAuctionItemList", petStore.getSellingAuctionItemListBySellerUsername(username));
			return mav;
//			System.out.println("no errors");
//			return mav;
		}
	}

	
	@RequestMapping("/shop/listMyBidItems.do")
	public ModelAndView listMyBidItem (HttpServletRequest request) throws Exception {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		if(userSession != null) {
			String username = userSession.getAccount().getUsername();
			return new ModelAndView("ListBidItems", "bidItemList", petStore.getMyBidItems(username));
		} else {
			ModelAndView modelAndView = new ModelAndView("Error");
			modelAndView.addObject("message", "먼저 로그인하세요");
//			System.out.println(modelAndView);
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}
}
