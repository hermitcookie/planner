package com.example.jpetstore.controller;

import java.io.Serializable;

import com.example.jpetstore.domain.Item;

@SuppressWarnings("serial")
public class EditItemForm implements Serializable {
   
   private Item item = new Item();
//   private boolean newItem;
   
   public Item getItem() {
      return item;
   }
   
   public EditItemForm() {
      
   }
   
   public EditItemForm(Item item) {
      this.item = item;
      
   }
//   public ItemForm(Item item) {
//      this.item = item;
//      System.out.println(item.getItemId());   
//   }
   
//   public ItemForm(Item item) {
//      this.item = item;
//      this.newItem = false;
//   }
//   
//   public ItemForm() {
//      this.item = new Item();
//      this.newItem = true;
//   }
//   

//   public boolean isNewItem() {
//      return newItem;
//   }
   

}