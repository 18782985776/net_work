package com.jobnew.farm.entity.address;

import com.jobnew.farm.entity.DistrictModel;

import java.util.List;

/**
 * @author 曾雪强 E-mail:
 * @version 创建时间：2017年7月3日 下午12:06:58 请写详细说明
 */
public class City {
	private Integer id;
	private String name;
	private List<County> countys;
	public City(String name, List<County> districtList) {
		super();
		this.name = name;
		this.countys = districtList;
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

	public List<County> getCountys() {
		return countys;
	}

	public void setCountys(List<County> countys) {
		this.countys = countys;
	}
	@Override
	public String toString() {
		return "CityModel [name=" + name + ", districtList=" + countys
				+ "]";
	}
}
