/*
 *    Copyright 2010-2013 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.example.jpetstore.dao.mybatis.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.jpetstore.domain.Item;

/**
 * @author Eduardo Macarron
 *
 */
public interface ItemMapper {

  void updateInventoryQuantity(Map<String, Object> param);

  int getInventoryQuantity(String itemId);

  List<Item> getItemListByProduct(String productId);

  Item getItem(String itemId); 
  Item getAuctionItem(String itemId);  //0624수정
  
  boolean isItemInStock(String itemId);

  void insertItem(Item item);
  void insertAuctionItem(Item item);
  
  void updateQuantityForInsertItem(Item item);
  
  List<Item> getSellingItemListBySellerUsername(String username);
  List<Item> getSellingItemList();//0624 수정

  void deleteItem(String itemId);
  void deleteItemInventory(String itemId);
  void deleteItemMyActivity(String itemId);
  void deleteAuctionItem(String itemId);//0624
  
  void updateItem(Item item);

  /*���ƿ�, ��ȸ�� ������Ʈ ��� */
  void updateCountView(String itemId);
  void insertCountActivity(String itemId, String username);
  
  void updateCountLike(String itemId);
  void insertCountLikeActivity(String itemId, String username);
  /*���ƿ�, ��ȸ�� ������Ʈ ��� */
  
  /*0622 수정 select if recent */
  boolean selectIfRecent(String itemId, String username);
  boolean selectIfLike(String itemId, String username);
  
  void closeAuction(Date closingTime);
  void afterCloseAuction();
  
  List<Item> getSellingAuctionItemListBySellerUsername(String username);
  
  List<String> getAuctionItemAtClose(Date closeTime);
  
  String findHighestUser(String itemId);
  
}
