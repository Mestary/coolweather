package com.coolweather.app.model;

import java.util.ArrayList;
import java.util.List;

import com.coolweather.app.db.CoolWeatherOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {
	
	//数据库名
	public static final String DB_NAME="coolweather";
	
	//数据库版本
	public static final int VERSION=1;
	
	private static CoolWeatherDB coolWeatherDB;
	
	private SQLiteDatabase db;
	
	//构造私有方法
	private CoolWeatherDB(Context context){
		CoolWeatherOpenHelper dbHelper=new CoolWeatherOpenHelper(context,
				DB_NAME,null,VERSION);
		db=dbHelper.getWritableDatabase();
		
	}
	
	public synchronized static CoolWeatherDB getinstance(Context context){
		if(coolWeatherDB==null){
			coolWeatherDB=new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}
	
	//将province实例存储到数据库
	public void saveProvince(Province province){
		if (province !=null){
			ContentValues values=new ContentValues();
			values.put("province_name", province.getProvinceNmae());
			values.put("province_code", province.getProvinceCode());
			db.insert("province", null, values);
		}
		
	}
	
	//从数据库读取全国所有省份信息
	public List<Province> loadProvinces(){
		List<Province> list=new ArrayList<Province>();
		Cursor cursor=db.query("Province", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Province province=new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceNmae(cursor.getString(cursor.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				list.add(province);
			} while (cursor.moveToNext());
		}
		return list;
	}
	
	//将City实例存储到数据库
	
	public void saveCity(City city){
		if(city !=null){
			ContentValues values=new ContentValues();
			values.put("city_name", city.getcityNmae());
			values.put("city_code", city.getcityCode());
			values.put("province_id", city.getprovinceId());
			db.insert("City", null, values);
		}
		
	}
	
	//从数据库读取某省份下的城市信息
	
	public List<City> loadCities(int provinceId){
		List<City> list=new ArrayList<City>();
		Cursor cursor=db.query("City", null, "province_id=?", new String[]{String.valueOf("provinceId")}, null, null, null);
		if (cursor.moveToFirst()){
			do{
				City city=new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setcityNmae(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setcityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setprovinceId(provinceId);
				list.add(city);
				
			} while (cursor.moveToNext());
		}
		return list;
	}
	
	//将County实例存储到数据库
	public void saveCounty(County county)
	{
		if (county !=null){
			ContentValues values=new ContentValues();
			values.put("county_name", county.getcountyNmae());
			values.put("county_code", county.getcountyCode());
			values.put("city_id", county.getcityid());
			values.put("id", county.getId());
		}
	}
	
	//从数据库读取某城市下的所有的县信息
	
	public List<County> loadCounties(int cityId){
		List<County> list=new ArrayList<County>();
		Cursor cursor=db.query("County",null,"city_id=?", new String[]{String.valueOf("cityId")},null,null,null);
		if(cursor.moveToFirst()){
			do{
				County county=new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setcountyNmae(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setcountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
				county.setcityid(cursor.getInt(cursor.getColumnIndex("city_id")));
				
			}while (cursor.moveToNext());
		}
		return list;
	}
}
