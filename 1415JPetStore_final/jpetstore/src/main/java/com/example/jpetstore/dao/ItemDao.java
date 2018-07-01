package com.example.jpetstore.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.Order;

public interface ItemDao {

  public void updateQuantity(Order order) throws DataAccessException;

  boolean isItemInStock(String itemId) throws DataAccessException;

  List<Item> getItemListByProduct(String productId) throws DataAccessException;

  Item getItem(String itemId) throws DataAccessException; 
  Item getAuctionItem(String itemId) throws DataAccessException;

  void insertItem(Item item) throws DataAccessException;
  void insertAuctionItem(Item item) throws DataAccessException;
  
  void updateQuantityForInsertItem(Item item) throws DataAccessException;

  List<Item> getSellingItemListBySellerUsername(String username) throws DataAccessException;
//0624 수정
  List<Item> getSellingItemList() throws DataAccessException;

  public void deleteItem(String itemId) throws DataAccessException;
  public void deleteItemInventory(String itemId) throws DataAccessException;
  public void deleteItemMyActivity(String itemId) throws DataAccessException;
  public void deleteAuctionItem(String itemId) throws DataAccessException;
  
  
  public void updateItem(Item item);
  
  /*��ȸ�� ���ƿ� ���ε�*/
  public void updateCountView(String itemId) throws DataAccessException;
  public void insertCountActivity(String itemId, String username);

  public void updateCountLike(String itemId) throws DataAccessException;
  public void insertCountLikeActivity(String itemId, String username);
  /*��ȸ�� ���ƿ� ���ε�*/
  
  /*0622 recent activity 여부 */
  public boolean selectIfRecent(String itemId, String username) throws DataAccessException;
  public boolean selectIfLike(String itemId, String username) throws DataAccessException;
  
  public List<Item> getSellingAuctionItemListBySellerUsername(String username);
  
  public List<String> getAuctionItemIdAtClose(Date closeTime);

  public void closeAuction(Date curTime);
  
  String findHighestUser(String itemId);
}
