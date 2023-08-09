package com.lawencon.candidate.model;

import com.lawencon.base.BaseEntity;

public class City extends BaseEntity{
	private String cityCode;
	private String cityName;
	
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
