package com.coolweather.app.model;

public class City {
	private int id;
	private String cityName;
	private String cityCode;
	private int provinceId;
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id=id;
	}
	
	public String getcityNmae()
	{
		return cityName;
	}
	
	public void setcityNmae(String cityName)
	{
		this.cityName=cityName;
	}
	
	public String getcityCode()
	{
		return cityCode;
	}
	
	public void setcityCode(String cityCode)
	{
		this.cityCode=cityCode;
	}
	
	public int getprovinceId ()
	{
		return provinceId;
	}
	
	public void setprovinceId(int provinceId)
	{
		this.provinceId=provinceId;
	}
}
