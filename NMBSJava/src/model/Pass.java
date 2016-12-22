package model;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class Pass {
	private UUID passID;
	private UUID typePassID;
	private Date date;
	private Date startDate;
	private int comfortClass;
	private long unixTimestamp;
	private TypePass typePass;
	public Pass(UUID typePassID, Date date, Date startDate, int comfortClass) {
		super();
		this.passID = UUID.randomUUID();
		this.typePassID = typePassID;
		this.date = date;
		this.startDate = startDate;
		this.comfortClass = comfortClass;
		this.unixTimestamp = Instant.now().getEpochSecond();
	}
	public Pass() {
		
	}
	public UUID getPassID() {
		return passID;
	}
	public void setPassID(UUID passID) {
		this.passID = passID;
	}
	public UUID getTypePassID() {
		return typePassID;
	}
	public void setTypePassID(UUID typePassID) {
		this.typePassID = typePassID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getComfortClass() {
		return comfortClass;
	}
	public void setComfortClass(int comfortClass) {
		this.comfortClass = comfortClass;
	}
	public void setUnixTimestamp(long unixTimestamp) {
		this.unixTimestamp = unixTimestamp;
	}
	public long getUnixTimestamp() {
		return unixTimestamp;
	}

	public void update() {
		this.unixTimestamp = Instant.now().getEpochSecond();
	}
	public void setLastUpdated(long unixTimestamp){
		this.unixTimestamp = unixTimestamp;
	}
	public void setTypePass(TypePass t) {
		this.typePass = t;
	}
	public TypePass getTypePass(){
		return this.typePass;
	}
	
}
