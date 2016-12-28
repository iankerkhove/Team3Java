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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + comfortClass;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((passID == null) ? 0 : passID.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((typePassID == null) ? 0 : typePassID.hashCode());
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
		Pass other = (Pass) obj;
		if (comfortClass != other.comfortClass)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		}
		else if (!date.equals(other.date))
			return false;
		if (passID == null) {
			if (other.passID != null)
				return false;
		}
		else if (!passID.equals(other.passID))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		}
		else if (!startDate.equals(other.startDate))
			return false;
		if (typePassID == null) {
			if (other.typePassID != null)
				return false;
		}
		else if (!typePassID.equals(other.typePassID))
			return false;
		return true;
	}

}
