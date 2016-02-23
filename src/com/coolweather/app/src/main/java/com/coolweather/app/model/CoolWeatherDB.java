package com.coolweather.app.model;

import java.util.ArrayList;
import java.util.List;

import com.coolweather.app.db.CoolWeatherOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {
	
	//���ݿ���
	public static final String DB_NAME="coolweather";
	
	//���ݿ�汾
	public static final int VERSION=1;
	
	private static CoolWeatherDB coolWeatherDB;
	
	private SQLiteDatabase db;
	
	//����˽�з���
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
	
	//��provinceʵ���洢�����ݿ�
	public void saveProvince(Province province){
		if (province !=null){
			ContentValues values=new ContentValues();
			values.put("province_name", province.getProvinceNmae());
			values.put("province_code", province.getProvinceCode());
			db.insert("province", null, values);
		}
		
	}
	
	//�����ݿ��ȡȫ������ʡ����Ϣ
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
	
	//��Cityʵ���洢�����ݿ�
	
	public void saveCity(City city){
		if(city !=null){
			ContentValues values=new ContentValues();
			values.put("city_name", city.getcityNmae());
			values.put("city_code", city.getcityCode());
			values.put("province_id", city.getprovinceId());
			db.insert("City", null, values);
		}
		
	}
	
	//�����ݿ��ȡĳʡ���µĳ�����Ϣ
	
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
	
	//��Countyʵ���洢�����ݿ�
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
	
	//�����ݿ��ȡĳ�����µ����е�����Ϣ
	
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