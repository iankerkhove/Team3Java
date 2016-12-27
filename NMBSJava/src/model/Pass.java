package model;

import java.time.Instant;
import java.util.UUID;

public class Pass
{
	private UUID passID;
	private UUID typePassID;
	private String date;
	private String startDate;
	private int comfortClass;
	private TypePass typePass;
	private long lastUpdated;
	
	public Pass()
	{}

	public Pass(UUID typePassID, String date, String startDate, int comfortClass)
	{
		this.passID = UUID.randomUUID();
		this.typePassID = typePassID;
		this.date = date;
		this.startDate = startDate;
		this.comfortClass = comfortClass;
		this.lastUpdated = Instant.now().getEpochSecond();
	}

	public UUID getPassID()
	{
		return passID;
	}

	public void setPassID(UUID passID)
	{
		this.passID = passID;
	}

	public UUID getTypePassID()
	{
		return typePassID;
	}

	public void setTypePassID(UUID typePassID)
	{
		this.typePassID = typePassID;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String d)
	{
		this.date = d;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	public int getComfortClass()
	{
		return comfortClass;
	}

	public void setComfortClass(int comfortClass)
	{
		this.comfortClass = comfortClass;
	}

	public TypePass getTypePass()
	{
		return typePass;
	}

	public void setTypePass(TypePass typePass)
	{
		this.typePass = typePass;
	}

	public long getLastUpdated()
	{
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated)
	{
		this.lastUpdated = lastUpdated;
	}

	public void update()
	{
		this.lastUpdated = Instant.now().getEpochSecond();
	}

}
