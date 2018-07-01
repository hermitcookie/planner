package com.example.jpetstore.dao.mybatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.jpetstore.dao.ItemDao;
import com.example.jpetstore.dao.mybatis.mapper.ItemMapper;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.LineItem;
import com.example.jpetstore.domain.Order;

@Repository
public class MybatisItemDao implements ItemDao {
	@Autowired
	private ItemMapper itemMapper;
	
	public void updateQuantity(Order order) throws DataAccessException {
		for (int i = 0; i < order.getLineItems().size(); i++) {
			LineItem lineItem = (LineItem) order.getLineItems().get(i);
			String itemId = lineItem.getItemId();
			Integer increment = new Integer(lineItem.getQuantity());
			Map<String, Object> param = new HashMap<String, Object>(2);
			param.put("itemId", itemId);
			param.put("increment", increment);
			itemMapper.updateInventoryQuantity(param);
		}
	}

	public boolean isItemInStock(String itemId) throws DataAccessException {
		return (itemMapper.getInventoryQuantity(itemId) > 0);
	}

	public List<Item> getItemListByProduct(String productId) 
			throws DataAccessException {
		return itemMapper.getItemListByProduct(productId);
	}

	public Item getItem(String itemId) throws DataAccessException {
		return itemMapper.getItem(itemId); 
	}
	//0624수정
	public Item getAuctionItem(String itemId) throws DataAccessException {
		return itemMapper.getAuctionItem(itemId); 
	}
	
	//Added methods
	public void insertItem(Item item) throws DataAccessException {
		itemMapper.insertItem(item);
	}
	public void insertAuctionItem(Item item) throws DataAccessException {
		System.out.println("insertAuctionItem MYBATISITEMDAO");
		itemMapper.insertAuctionItem(item);
	}

	@Override
	public void updateQuantityForInsertItem(Item item) throws DataAccessException {
		itemMapper.updateQuantityForInsertItem(item);
		
	}

	@Override
	public List<Item> getSellingItemListBySellerUsername(String username) throws DataAccessException {
		// TODO Auto-generated method stub
		return itemMapper.getSellingItemListBySellerUsername(username);
	}
	@Override//0624 수정
	public List<Item> getSellingItemList() throws DataAccessException {
		// TODO Auto-generated method stub
		return itemMapper.getSellingItemList();
	}
	
	@Override
	public void deleteItem(String itemId) throws DataAccessException {
		// TODO Auto-generated method stub
		itemMapper.deleteItem(itemId);
		
	}

	@Override
	public void deleteItemInventory(String itemId) throws DataAccessException {
		// TODO Auto-generated method stub
		itemMapper.deleteItemInventory(itemId);
	}
	
	@Override
	public void deleteItemMyActivity(String itemId) throws DataAccessException {
		itemMapper.deleteItemMyActivity(itemId);
	}
	
	//0624
	@Override
	public void deleteAuctionItem(String itemId) throws DataAccessException {
		// TODO Auto-generated method stub
		itemMapper.deleteAuctionItem(itemId);
		
	}

	
	@Override
	public void updateItem(Item item) {
		// TODO Auto-generated method stub
		itemMapper.updateItem(item);
	}
	
	/*��ȸ�� ���ƿ� ��� */
	@Override
	public void updateCountView(String itemId) throws DataAccessException {
		// TODO Auto-generated method stub
		itemMapper.updateCountView(itemId);
	}

	@Override
	public void insertCountActivity(String itemId, String username) {
		// TODO Auto-generated method stub
		itemMapper.insertCountActivity(itemId, username);
	}

	@Override
	public void updateCountLike(String itemId) throws DataAccessException {
		// TODO Auto-generated method stub
		itemMapper.updateCountLike(itemId);
	}

	@Override
	public void insertCountLikeActivity(String itemId, String username) {
		// TODO Auto-generated method stub
		itemMapper.insertCountLikeActivity(itemId, username);
	}
	
	/*0622 recent activity 여부 */
	@Override
	public boolean selectIfRecent(String itemId, String username) {
		System.out.println("selectIfRecent MybatisItemDAO");
		return itemMapper.selectIfRecent(itemId, username);
	}
	@Override
	public boolean selectIfLike(String itemId, String username) {
		System.out.println("selectIfLike MybatisItemDAO");
		return itemMapper.selectIfLike(itemId, username);
	}
	
	@Override
	public List<Item> getSellingAuctionItemListBySellerUsername(String username) {
		// TODO Auto-generated method stub
		return itemMapper.getSellingAuctionItemListBySellerUsername(username);
	}
	
	public List<String> getAuctionItemIdAtClose(Date closeTime){
		return itemMapper.getAuctionItemAtClose(closeTime);
	}
	
	public void closeAuction(Date curTime) {
		itemMapper.closeAuction(curTime);
		System.out.println("aftercloseauction실행전");
		itemMapper.afterCloseAuction();
		System.out.println("aftercloseauction실행후");
	}
	
	public String findHighestUser(String itemId) {
		return itemMapper.findHighestUser(itemId);
	}
	
	
}

