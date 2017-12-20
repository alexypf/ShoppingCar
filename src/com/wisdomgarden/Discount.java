package com.wisdomgarden;

public class Discount {

	private String date;
	private String discountNum;
	private ProductType type;
	
	
	public Discount(String date, String discountNum, ProductType type) {
		super();
		this.date = date;
		this.discountNum = discountNum;
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDiscountNum() {
		return discountNum;
	}
	public void setDiscountNum(String discountNum) {
		this.discountNum = discountNum;
	}
	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		this.type = type;
	}
	
	
}
