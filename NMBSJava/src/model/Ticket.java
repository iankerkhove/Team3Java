package model;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Ticket {
	// members
	private UUID ticketID;
	private UUID routeID;
	private Route route;
	private UUID typeTicketID;
	private TypeTicket typeTicket;
	private String date;
	private String validFrom;
	private String validUntil;
	private long lastUpdated;

	// enumeratie
	public enum ComfortClass {
		First, Second
	}

	public Ticket() {
	}

	public Ticket(UUID routeID, UUID typeTicketID, String date, String validFrom, String validUntil) {
		this.ticketID = UUID.randomUUID();
		this.routeID = routeID;
		this.typeTicketID = typeTicketID;
		this.date = date;
		this.validFrom = validFrom;
		this.validUntil = validUntil;
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	public void update() {
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	public UUID getTicketID() {
		return ticketID;
	}

	public void setTicketID(UUID ticketID) {
		this.ticketID = ticketID;
	}

	public UUID getRouteID() {
		return routeID;
	}

	public void setRouteID(UUID routeID) {
		this.routeID = routeID;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public UUID getTypeTicketID() {
		return typeTicketID;
	}

	public void setTypeTicketID(UUID typeTicketID) {
		this.typeTicketID = typeTicketID;
	}

	public TypeTicket getTypeTicket() {
		return typeTicket;
	}

	public void setTypeTicket(TypeTicket typeTicket) {
		this.typeTicket = typeTicket;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(String validUntil) {
		this.validUntil = validUntil;
	}

	public long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
