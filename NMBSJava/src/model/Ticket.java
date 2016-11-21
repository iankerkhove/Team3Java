package model;

import java.util.Date;

public class Ticket {
	// members
	private int routeID;
	private Date date;
	private double price;
	private Date validFrom;
	private Date validUntil;
	// enumeratie
	public enum ComfortClass{
		First, Second
	}
	private ComfortClass comfortClass;
	
	//getters en setters
	private int ticketID;
	public int getTicketID() {
		return ticketID;
	}
	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}
	public int getRouteID() {
		return routeID;
	}
	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}
	public ComfortClass getComfortClass() {
		return comfortClass;
	}
	public void setComfortClass(ComfortClass comfortClass) {
		this.comfortClass = comfortClass;
	}
	public Ticket(int routeID, Date date, double price, Date validFrom, Date validUntil, ComfortClass comfortClass) {
		super();
		this.routeID = routeID;
		this.date = date;
		this.price = price;
		this.validFrom = validFrom;
		this.validUntil = validUntil;
		this.comfortClass = comfortClass;
	}
	
	//functies en methoden
	
	
}
