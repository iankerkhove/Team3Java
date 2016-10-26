package model;

public class Discount {
	//datamembers
	private int discountID;
	private String name;
	private double amount;
	
	//getters en setters
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
	
	//constructor
	public Discount(int discountID, String name, double amount) {
		super();
		this.discountID = discountID;
		this.name = name;
		this.amount = amount;
	}
	
	//functies en methoden
	
	
}
