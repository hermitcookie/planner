package com.example.jpetstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.example.jpetstore.domain.Item;
import com.example.jpetstore.service.PetStoreFacade;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
public class ViewItemController { 

	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@RequestMapping("/shop/viewItem.do")
	public String handleRequest(HttpServletRequest request, 
			@RequestParam("itemId") String itemId,
			ModelMap model) throws Exception {
		Item item = this.petStore.getItem(itemId);
		model.put("item", item);
		model.put("product", item.getProduct());
		
		System.out.println("viewItem.do");
		UserSession userSession = (UserSession)WebUtils.getSessionAttribute(request, "userSession");
		System.out.println("��������:"+userSession);
		if(userSession != null){
			String state = ""+userSession.getAccount().getState();
//			System.out.println("gdgdstate:"+state);
			if(userSession.getAccount().getUsername() != null){
				String username = userSession.getAccount().getUsername();
//				System.out.println("username:"+username);
				this.petStore.updateCountView(itemId, username);//��ȸ�� ����
				model.addAttribute("likyn",this.petStore.selectIfLikeQ(itemId, username));
			}
		}
		else if(userSession == null)
			this.petStore.updateCountView(itemId);//��ȸ�� ����
		
		model.addAttribute("view",this.petStore.getItem(itemId));
		return "Item";
	}
	@RequestMapping("/shop/viewAuctionItem.do")
	public String handleRequest3(HttpServletRequest request, 
			@RequestParam("itemId") String itemId,
			ModelMap model) throws Exception {
		Item item = this.petStore.getAuctionItem(itemId);
		model.put("item", item);
		model.put("product", item.getProduct());
		
//		System.out.println("viewItem.do");
		UserSession userSession = (UserSession)WebUtils.getSessionAttribute(request, "userSession");
//		System.out.println("��������:"+userSession);
		if(userSession != null){
			String state = ""+userSession.getAccount().getState();
//			System.out.println("gdgdstate:"+state);
			if(userSession.getAccount().getUsername() != null){
				String username = userSession.getAccount().getUsername();
//				System.out.println("username:"+username);
				this.petStore.updateCountView(itemId, username);//��ȸ�� ����
				model.addAttribute("likyn",this.petStore.selectIfLikeQ(itemId, username));
			}
		}
		else if(userSession == null)
			this.petStore.updateCountView(itemId);//��ȸ�� ����
		
		model.addAttribute("view",this.petStore.getItem(itemId));
		return "AuctionItem";
	}	
	@RequestMapping("/shop/likeUpdate.do")
	public String handleRequest2(HttpServletRequest request, 
			@RequestParam("itemId") String itemId,
			ModelMap model) throws Exception {
		
//		System.out.println("likeUpdate");
		UserSession userSession = (UserSession)WebUtils.getSessionAttribute(request, "userSession");
		String username = userSession.getAccount().getUsername();
//		System.out.println("username:"+username);
		this.petStore.updateCountLike(itemId, username);
		
		return "redirect:/shop/viewItem.do?itemId="+itemId;
	}
	
	//������ �κ� activity, activity.jsp �߰�, �̹��� �߰�
	@RequestMapping("/shop/myActivity.do")
	public String myActivity(HttpServletRequest request, 
			ModelMap model) throws Exception {
		
//		System.out.println("myActivity");
		UserSession userSession = (UserSession)WebUtils.getSessionAttribute(request, "userSession");
		String username = userSession.getAccount().getUsername();
//		System.out.println("username:"+username);
		//this.petStore.updateCountLike(itemId, username);
		model.addAttribute("resultRecentList",this.petStore.selectMyRecentList(username));	
		return "Activity";
	}
	@RequestMapping("/shop/myLikeActivity.do")
	public String myLikeActivity(HttpServletRequest request, 
			ModelMap model) throws Exception {
//		System.out.println("myLikeActivity");
		UserSession userSession = (UserSession)WebUtils.getSessionAttribute(request, "userSession");
		String username = userSession.getAccount().getUsername();
//		System.out.println("username:"+username);
		//this.petStore.updateCountLike(itemId, username);
		model.addAttribute("resultRecentList",this.petStore.selectMyLikeList(username));	
		return "Activity2";
	}
	
	@RequestMapping("/shop/DeletemyActivity.do")
	public String DeletemyActivity(HttpServletRequest request, 
			@RequestParam("itemId") String itemId, ModelMap model) throws Exception {	
//		System.out.println("DeletemyActivity");
		UserSession userSession = (UserSession)WebUtils.getSessionAttribute(request, "userSession");
		String username = userSession.getAccount().getUsername();
//		System.out.println("username:"+username);
		this.petStore.deleteMyRecent(itemId, username);	
		return "redirect:/shop/myActivity.do";
	}
	@RequestMapping("/shop/DeletemyLikeActivity.do")
	public String DeletemyLikeActivity(HttpServletRequest request, 
			@RequestParam("itemId") String itemId, ModelMap model) throws Exception {	
//		System.out.println("DeletemyLikeActivity");
		UserSession userSession = (UserSession)WebUtils.getSessionAttribute(request, "userSession");
		String username = userSession.getAccount().getUsername();
//		System.out.println("username:"+username);
		this.petStore.deleteMyLike(itemId, username);	
		return "redirect:/shop/myLikeActivity.do";
	}

}
