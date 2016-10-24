package model;

import java.util.Date;

public class Ticket {
	
	private int routeID;
	private Date date;
	private double price;
	private Date validFrom;
	private Date validUntil;
	public enum ComfortClass{
		First, Second
	}
	private ComfortClass comfortClass;
	
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
	
	
}
