package com.example.jpetstore.controller;
import java.io.Serializable;

import com.example.jpetstore.domain.Bid;
import com.example.jpetstore.domain.Item;

@SuppressWarnings("serial")
public class bidForm {
	private Bid bid = new Bid();
	private Item item = new Item();
//	private boolean newItem;
	
	public Bid getBid() {
		return bid;
	}
	public bidForm (){
		
	}
	
}
