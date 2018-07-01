package com.example.jpetstore.controller;

import java.io.Serializable;

import com.example.jpetstore.domain.Item;

//ItemForm : ������ ������ �Է� �޾� ������ ��ü 
@SuppressWarnings("serial")
public class AuctionItemForm implements Serializable {
	
	private final Item item = new Item();
//	private boolean newItem;
	
	public Item getItem() {
		return item;
	}
	
//	public ItemForm(Item item) {
//		this.item = item;
//		this.newItem = false;
//	}
//	
//	public ItemForm() {
//		this.item = new Item();
//		this.newItem = true;
//	}
//	

//	public boolean isNewItem() {
//		return newItem;
//	}
	

}
