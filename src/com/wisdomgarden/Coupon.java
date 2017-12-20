package com.wisdomgarden;

public class Coupon {

	
	private String deadline;
	private int total;
	private int cutoff;
	
	public Coupon() {}
	
	public Coupon(String deadline, int total, int cutoff) {
		super();
		this.deadline = deadline;
		this.total = total;
		this.cutoff = cutoff;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCutoff() {
		return cutoff;
	}
	public void setCutoff(int cutoff) {
		this.cutoff = cutoff;
	}
	
	
}
