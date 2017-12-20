package com.wisdomgarden;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class ShoppingCarTest {

	@Test
	public void case1() {
		
		List<Discount> discounts = new ArrayList<Discount>();
		List<Product> products = new ArrayList<Product>();
		
		Coupon coupon = new Coupon("2014.3.2",1000,200);
		String payday = "2013.11.11";
		Discount d = new Discount("2013.11.11","0.7",ProductType.electronic);
		discounts.add(d);
		
		Product ipad = new Product("iPad",new BigDecimal("2399.00"), 1);
		Product displayer = new Product("displayer",new BigDecimal("1799.00"), 1);
		Product beer = new Product("beer",new BigDecimal("25.00"), 12);
		Product bread = new Product("bread",new BigDecimal("9.00"), 5);
		products.add(ipad);
		products.add(displayer);
		products.add(beer);
		products.add(bread);
		
		assertEquals(new BigDecimal("3083.60"), ShoppingCar.caculate(discounts, products, payday, coupon));
	}
	
	@Test
	public void case2() {
		
		List<Discount> discounts = new ArrayList<Discount>();
		List<Product> products = new ArrayList<Product>();
		Coupon coupon = new Coupon("2014.3.2",1000,200);
		String payday = "2013.11.11";
		
		Product vegetable = new Product("vegetable",new BigDecimal("5.98"), 3);
		Product paper = new Product("paper",new BigDecimal("3.20"), 8);
		products.add(vegetable);
		products.add(paper);
		
		payday = "2014.01.01";
		
		assertEquals(new BigDecimal("43.54"), ShoppingCar.caculate(discounts, products, payday, coupon));
	}
	
	@Test
	public void case3() {
		
		BigDecimal[] expect = {new BigDecimal("3083.60"),new BigDecimal("43.54"),new BigDecimal("3061.10")};
		
		assertArrayEquals(expect,ShoppingCar.getShoppingCarInfo().toArray());
	}
}
