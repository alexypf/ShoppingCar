package com.wisdomgarden;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	
	public static boolean isValidProduct(String line) {
		boolean result = false;
		Properties property = getConfigProperties();
		String prodRegex = property.getProperty("productRegex");
		Pattern pattern = Pattern.compile(prodRegex);
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			result = true;
		}
		return result;
	}
	
	public static boolean isValidDiscount(String line) {
		boolean result = false;
		Properties property = getConfigProperties();
		String discountRegex = property.getProperty("discountRegex");
		Pattern pattern = Pattern.compile(discountRegex);
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			result = true;
		}
		return result;
	}
	
	public static boolean isValidCoupon(String line) {
		boolean result = false;
		Properties property = getConfigProperties();
		String couponRegex = property.getProperty("couponRegex");
		Pattern pattern = Pattern.compile(couponRegex);
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			result = true;
		}
		return result;
	}
	
	public static boolean isValidPayday(String line) {
		boolean result = false;
		Properties property = getConfigProperties();
		String paydayRegex = property.getProperty("paydayRegex");
		Pattern pattern = Pattern.compile(paydayRegex);
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			result = true;
		}
		return result;
	}
	
	
	public static Date string2Date(String s) {
		Date result = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		try {
			result = format.parse(s);
		} catch (ParseException e) {
			System.out.println("date string is invalid!");
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean isElectronic(String name) {
		boolean result = false;
		Properties property = getConfigProperties();
		String products = property.getProperty("electronic");
		if(products!=null && products.contains(name)) {
			result = true;
		}
		return result;
	}
	
	public static boolean isFood(String name) {
		boolean result = false;
		Properties property = getConfigProperties();
		String products = property.getProperty("food");
		if(products!=null && products.contains(name)) {
			result = true;
		}
		return result;
	}
	
	public static boolean isCommodity(String name) {
		boolean result = false;
		Properties property = getConfigProperties();
		String products = property.getProperty("commodity");
		if(products!=null && products.contains(name)) {
			result = true;
		}
		return result;
	}
	
	public static boolean isWine(String name) {
		boolean result = false;
		Properties property = getConfigProperties();
		String products = property.getProperty("wine");
		if(products!=null && products.contains(name)) {
			result = true;
		}
		return result;
	}
	
	public static Properties getConfigProperties() {
		Properties property = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("src\\com\\wisdomgarden\\config.properties");
			property.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("no config.properties found!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis!=null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return property;
	}
	
}
