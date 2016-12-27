package model;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Ticket {
	// members
	private UUID routeID;
	private Date date;
	private double price;
	private Date validFrom;
	private Date validUntil;
	private UUID ticketID;
	private long unixTimestamp;
	// enumeratie
	public enum ComfortClass{
		First, Second
	}
	private ComfortClass comfortClass;
	
	//getters en setters
	
	public UUID getTicketID() {
		return ticketID;
	}
	public long getUnixTimestamp() {
		return unixTimestamp;
	}
	public void update() {
		this.unixTimestamp = Instant.now().getEpochSecond();
	}
	/*public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}*/
	public UUID getRouteID() {
		return routeID;
	}
	/*public void setRouteID(int routeID) {
		this.routeID = routeID;
	}*/
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
	public Ticket(UUID routeID, Date date, double price, Date validFrom, Date validUntil, ComfortClass comfortClass) {
		super();
		this.date = date;
		this.price = price;
		this.validFrom = validFrom;
		this.validUntil = validUntil;
		this.comfortClass = comfortClass;
		this.routeID = UUID.randomUUID();
		this.unixTimestamp = Instant.now().getEpochSecond();
	}
	
	//functies en methoden
	
	
}
