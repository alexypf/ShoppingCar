package com.wisdomgarden;

import java.math.BigDecimal;

public class Product {

	private String name;
	private BigDecimal price;
	private int number;
	
	public Product(String name, BigDecimal price, int number) {
		super();
		this.name = name;
		this.price = price;
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	
}
	