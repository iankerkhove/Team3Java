package model;

public class Discount {
	private int discountID;
	private String name;
	private double amount;
	
	public Discount(String name, double amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public int getDiscountID() {
		return discountID;
	}
	public void setDiscountID(int discountID) {
		this.discountID = discountID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
