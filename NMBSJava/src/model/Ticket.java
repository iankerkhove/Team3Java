package model;

import java.time.Instant;
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((routeID == null) ? 0 : routeID.hashCode());
		result = prime * result + ((ticketID == null) ? 0 : ticketID.hashCode());
		result = prime * result + ((typeTicketID == null) ? 0 : typeTicketID.hashCode());
		result = prime * result + ((validFrom == null) ? 0 : validFrom.hashCode());
		result = prime * result + ((validUntil == null) ? 0 : validUntil.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		}
		else if (!date.equals(other.date))
			return false;
		if (routeID == null) {
			if (other.routeID != null)
				return false;
		}
		else if (!routeID.equals(other.routeID))
			return false;
		if (ticketID == null) {
			if (other.ticketID != null)
				return false;
		}
		else if (!ticketID.equals(other.ticketID))
			return false;
		if (typeTicketID == null) {
			if (other.typeTicketID != null)
				return false;
		}
		else if (!typeTicketID.equals(other.typeTicketID))
			return false;
		if (validFrom == null) {
			if (other.validFrom != null)
				return false;
		}
		else if (!validFrom.equals(other.validFrom))
			return false;
		if (validUntil == null) {
			if (other.validUntil != null)
				return false;
		}
		else if (!validUntil.equals(other.validUntil))
			return false;
		return true;
	}

}