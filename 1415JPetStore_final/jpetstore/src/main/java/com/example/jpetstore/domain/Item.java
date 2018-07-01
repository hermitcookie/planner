package com.example.jpetstore.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
public class Item implements Serializable {
  /* Private Fields */
  private String itemId;
  private String productId;
  private double listPrice;
  private double unitCost;
  private int supplierId;
  private String status;
  private String attribute1;
  private String attribute2;
  private String attribute3;
  private String attribute4;
  private String attribute5;
  private Product product;
  private int quantity;
  private String countlike;
  private String countview;
  private String sellerUsername;
  private String kind;

  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
  private Date startDate;
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
  private Date endDate;
  private double startPrice;
  private double highestPrice;
  private String highestUser;
  private String winningBid;
  private String aucStatus;

  public String getKind() {
	return kind;
}
public void setKind(String kind) {
	this.kind = kind;
}
  public String getAucStatus() {
	return aucStatus;
}
public void setAucStatus(String aucStatus) {
	this.aucStatus = aucStatus;
}
public String getCountlike() {
	return countlike;
}
public void setCountlike(String countlike) {
	this.countlike = countlike;
}
public String getCountview() {
	return countview;
}
public void setCountview(String countview) {
	this.countview = countview;
}
public Date getStartDate() {
	return startDate;
}
public void setStartDate(Date startDate) {
	this.startDate = startDate;
}
public Date getEndDate() {
	return endDate;
}
public void setEndDate(Date endDate) {
	this.endDate = endDate;
}
public double getStartPrice() {
	return startPrice;
}
public void setStartPrice(double startPrice) {
	this.startPrice = startPrice;
}
public double getHighestPrice() {
	return highestPrice;
}
public void setHighestPrice(double highestPrice) {
	this.highestPrice = highestPrice;
}
public String getHighestUser() {
	return highestUser;
}
public void setHighestUser(String highestUser) {
	this.highestUser = highestUser;
}
public String getWinningBid() {
	return winningBid;
}
public void setWinningBid(String winningBid) {
	this.winningBid = winningBid;
}
/* JavaBeans Properties */
  public String getItemId() { return itemId; }
  public void setItemId(String itemId) { this.itemId = itemId.trim(); }

  public int getQuantity() { return quantity; }
  public void setQuantity(int quantity) { this.quantity = quantity; }

  public Product getProduct() { return product; }
  public void setProduct(Product product) { this.product = product; }

  public String getProductId() { return productId; }
  public void setProductId(String productId) { this.productId = productId; }

  public int getSupplierId() { return supplierId; }
  public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

  public double getListPrice() { return listPrice; }
  public void setListPrice(double listPrice) { this.listPrice = listPrice; }

  public double getUnitCost() { return unitCost; }
  public void setUnitCost(double unitCost) { this.unitCost = unitCost; }

  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }

  public String getAttribute1() { return attribute1; }
  public void setAttribute1(String attribute1) { this.attribute1 = attribute1; }
  public String getAttribute2() { return attribute2; }
  public void setAttribute2(String attribute2) { this.attribute2 = attribute2; }
  public String getAttribute3() { return attribute3; }
  public void setAttribute3(String attribute3) { this.attribute3 = attribute3; }
  public String getAttribute4() { return attribute4; }
  public void setAttribute4(String attribute4) { this.attribute4 = attribute4; }
  public String getAttribute5() { return attribute5; }
  public void setAttribute5(String attribute5) { this.attribute5 = attribute5; }
  
public String toString() {
    return "(" + getItemId().trim() + "-" + getProductId().trim() + ")";
  }
  

public String getSellerUsername() {
	return sellerUsername;
}
public void setSellerUsername(String sellerUsername) {
	this.sellerUsername = sellerUsername;
}
  public void initNewItem(Account account) {
	  sellerUsername = account.getUsername();
  }
}