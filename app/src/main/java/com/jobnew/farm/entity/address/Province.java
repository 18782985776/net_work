package com.jobnew.farm.entity.address;
/** 
* @author 曾雪强 E-mail: 
* @version 创建时间：2017年7月3日 下午12:06:09 
* 请写详细说明
*/

import com.jobnew.farm.entity.CityModel;

import java.util.List;

public class Province {
	private Integer id;
	private String name;
	private List<City> citys;

	public Province(String name, List<City> cityList) {
		super();
		this.name = name;
		this.citys = cityList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getCitys() {
		return citys;
	}

	public void setCitys(List<City> citys) {
		this.citys = citys;
	}
	@Override
	public String toString() {
		return "ProvinceModel [name=" + name + ", cityList=" + citys + "]";
	}
}
