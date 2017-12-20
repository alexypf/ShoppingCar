package com.wisdomgarden;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCar {

	
public static List<BigDecimal> getShoppingCarInfo() {
		
		List<Discount> discounts = new ArrayList<Discount>();
		List<Product> products = new ArrayList<Product>();
		String payday = "";
		Coupon coupon = null;
		BigDecimal result = new BigDecimal(0);
		List<BigDecimal> resultList = new ArrayList<BigDecimal>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("src\\com\\wisdomgarden\\testCases.txt")));
			String line = "";
			while((line=br.readLine())!=null && line.length()>0 || "".equals(line)&&(line=br.readLine())!=null) {
				if(Utils.isValidDiscount(line)) {
					String[] array = line.split("\\s\\|\\s");
					String date = array[0];
					String discount = array[1];
					String type = array[2];
					System.out.println(date + " | " + discount + " | " + type);
					
					Discount d = new Discount(date,discount,ProductType.valueOf(type));
					discounts.add(d);
				}else if(Utils.isValidProduct(line)) {
					int num = Integer.valueOf(line.substring(0, line.indexOf(" * ")));
					BigDecimal price = new BigDecimal(line.substring(line.indexOf(" : ") + 3));
					String name = line.substring(line.indexOf(" * ") + 3, line.indexOf(" : "));
					System.out.println(num + " * " + name + " : " + price);
					Product p = new Product(name,price,num);
					products.add(p);
				}else if(Utils.isValidPayday(line)) {
					payday = line;
					System.out.println(line);
				}else if(Utils.isValidCoupon(line)) {
					String[] array = line.split(" ");
					String deadline = array[0];
					int total = Integer.valueOf(array[1]);
					int cutoff = Integer.valueOf(array[2]);
					System.out.println(line);
					coupon = new Coupon(deadline, total, cutoff);
				}else if(line.contains("===")) {
					System.out.println(line);
					result = caculate(discounts, products, payday, coupon);
					System.out.println(result);
					System.out.println();
					resultList.add(result);
					result = new BigDecimal(0);
					discounts = new ArrayList<Discount>();
					products = new ArrayList<Product>();
					coupon = new Coupon();
				}else if(line==null || "".equals(line)){
					continue;
				}else {
					System.out.println(line + " is invalid!");
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("can not find test file!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("no test case in test file!");
			e.printStackTrace();
		}finally {
			try {
				if(br!=null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

public static BigDecimal caculate(List<Discount> discounts, List<Product> products, String payday, Coupon coupon) {
	BigDecimal result = new BigDecimal(0);
	BigDecimal elecPay = new BigDecimal(0);
	BigDecimal foodPay = new BigDecimal(0);
	BigDecimal commPay = new BigDecimal(0);
	BigDecimal winePay= new BigDecimal(0);
	
	for(Product p:products) {
		BigDecimal price = p.getPrice();
		int number = p.getNumber();
		if(Utils.isElectronic(p.getName())) {
			elecPay = elecPay.add(price.multiply(new BigDecimal(number)));
		}else if(Utils.isFood(p.getName())) {
			foodPay = foodPay.add(price.multiply(new BigDecimal(number)));
		}else if(Utils.isCommodity(p.getName())) {
			commPay = commPay.add(price.multiply(new BigDecimal(number)));
		}else if(Utils.isWine(p.getName())) {
			winePay = winePay.add(price.multiply(new BigDecimal(number)));
		}
	}
	
	if(discounts!=null && discounts.size()>0) {
		for(Discount d : discounts) {
			if (payday.equals(d.getDate())) {
				if (d.getType().name().equals(ProductType.electronic.name())) {
					elecPay = elecPay.multiply(new BigDecimal(d.getDiscountNum()));
				}else if(d.getType().name().equals(ProductType.food.name())) {
					foodPay = foodPay.multiply(new BigDecimal(d.getDiscountNum()));
				}else if(d.getType().name().equals(ProductType.commodity.name())) {
					commPay = commPay.multiply(new BigDecimal(d.getDiscountNum()));
				}else if(d.getType().name().equals(ProductType.wine.name())) {
					winePay = winePay.multiply(new BigDecimal(d.getDiscountNum()));
				}
			}
		}
	}
	
	result = result.add(elecPay).add(foodPay).add(commPay).add(winePay);
	
	if(coupon!=null && coupon.getDeadline()!=null) {
		Date deadline = Utils.string2Date(coupon.getDeadline());
		Date payDate = Utils.string2Date(payday);
		if(deadline.after(payDate)) {
			if(result.compareTo(new BigDecimal(coupon.getTotal()))>0) {
				result = result.subtract(new BigDecimal(coupon.getCutoff()));
			}
		}else {
			System.out.println("your coupon is expired");
		}
	}
	
	return result.setScale(2, BigDecimal.ROUND_HALF_UP);
}
	
}
