package com.coolweather.app.model;

public class County {
	private int id;
	private String countyName;
	private String countyCode;
	private int cityid;
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id=id;
	}
	
	public String getcountyNmae()
	{
		return countyName;
	}
	
	public void setcountyNmae(String countyName)
	{
		this.countyName=countyName;
	}
	
	public String getcountyCode()
	{
		return countyCode;
	}
	
	public void setcountyCode(String countyCode)
	{
		this.countyCode=countyCode;
	}
	
	public int getcityid ()
	{
		return cityid;
	}
	
	public void setcityid(int cityid)
	{
		this.cityid=cityid;
	}
}
