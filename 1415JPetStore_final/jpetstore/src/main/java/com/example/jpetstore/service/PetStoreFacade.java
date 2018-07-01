package com.example.jpetstore.service;

import java.util.Date;
import java.util.List;

import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Bid;
import com.example.jpetstore.domain.BlackList;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.Order;
import com.example.jpetstore.domain.Product;

/**
 * JPetStore's central business interface.
 *
 * @author Juergen Hoeller
 * @since 30.11.2003
 */
public interface PetStoreFacade {

	List<BlackList> getBlackList();
	
	Account getAccount(String username);

	Account getAccount(String username, String password);

	void insertAccount(Account account);

	void updateAccount(Account account);

	List<String> getUsernameList();


	List<Category> getCategoryList();

	Category getCategory(String categoryId);
	
	List<Product> getProductListByCategory(String categoryId);

	List<Product> searchProductList(String keywords);

	Product getProduct(String productId);


	List<Item> getItemListByProduct(String productId);

	Item getItem(String itemId); 
	Item getAuctionItem(String itemId); //0624 ����
	
	
	boolean isItemInStock(String itemId);
	//Added method for inserting item
	void insertItem(Item item);
	void auctionInsertItem(Item item);
	//Added method for viewing list of my selling items
	List<Item> getSellingItemListBySellerUsername(String username);
	//0624 ����
	List<Item> getSellingItemList();
	//Remove selling item
	void deleteItem(String itemId);
	void deleteAuctionItem(String itemId);
	//Edit Selling Item
	void updateItem(Item item);

	void insertOrder(Order order);

	Order getOrder(int orderId);

	List<Order> getOrdersByUsername(String username);
	
	void updatePoint(String username, int point);
	
	int getUserPoint(Account account);
	//!!!!!!!!!!!!!!!!0622 myActivity�����߰�
	void updateCountView(String itemId, String username);
	void updateCountLike(String itemId, String username);

	List<Category> selectBestCategoryList(String categoryId);
	List<Category> selectBestList();

	void updateCountView(String itemId);
	
	List<Category> selectMyRecentList(String username);
	List<Category> selectMyLikeList(String username);
	
	void deleteMyRecent(String itemId, String username);
	void deleteMyLike(String itemId, String username);
	
	boolean selectIfLikeQ(String itemId, String username);
	
	List<Item> getSellingAuctionItemListBySellerUsername(String username);
	
	void insertBid(Bid bid);

	List<Bid> getMyBidItems(String username);
	
	void AuctionScheduler(Date closingTime);
	
}