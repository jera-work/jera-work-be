package com.lawencon.candidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_city")
public class City extends BaseEntity{

	@Column(name = "city_code", unique = true, nullable = false, length = 8)
	private String cityCode;
	
	@Column(name = "city_name", nullable = false, length = 30)
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
