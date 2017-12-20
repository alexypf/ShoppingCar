package com.wisdomgarden;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;



public class UtilsTest {
	
	@Test
	public void isValidCouponTest() {
		assertThat(Utils.isValidCoupon("2014.3.2 1000 200"), is(true));
		assertThat(Utils.isValidCoupon("2014.3.2 -1000 200"), is(false));
		assertThat(Utils.isValidCoupon("2014.3.2 | 1000 | abc"), is(false));
	}
	
	
	@Test
	public void isValidProductTest() {
		assertThat(Utils.isValidProduct("1 * iPad : 2399.00"), is(true));
		assertThat(Utils.isValidProduct("a * iPad : 2399.00"), is(false));
		assertThat(Utils.isValidProduct("a *  : 2399.00"), is(false));
	}
	
	@Test
	public void isValidDiscountTest() {
		assertThat(Utils.isValidDiscount("2013.11.11 | 0.7 | electronic"), is(true));
		assertThat(Utils.isValidDiscount("2013.11.11 | 2.3 | electronic"), is(false));
		assertThat(Utils.isValidDiscount("2013.11.11 * 0.7 - electronic"), is(false));
	}
	
	@Test
	public void isValidPaydayTest() {
		assertThat(Utils.isValidPayday("2014.03.14"), is(true));
		assertThat(Utils.isValidPayday("20131138"), is(false));
		assertThat(Utils.isValidPayday("2014-3-14"), is(false));
	}
	
	@Test
	public void isElectronicTest() {
		assertThat(Utils.isElectronic("iPad"),is(true));
		assertThat(Utils.isElectronic("bread"),is(false));
		assertThat(Utils.isElectronic("cookie"),is(false));
	}
	
	@Test
	public void isFoodTest() {
		assertThat(Utils.isFood("iPad"),is(false));
		assertThat(Utils.isFood("bread"),is(true));
		assertThat(Utils.isFood("cookie"),is(true));
	}
	
	@Test
	public void isWineTest() {
		assertThat(Utils.isWine("iPad"),is(false));
		assertThat(Utils.isWine("bread"),is(false));
		assertThat(Utils.isWine("vodka"),is(true));
	}
	
}
