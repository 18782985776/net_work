package com.jobnew.farm.entity;

public class DistrictModel {
	private String name;
	private String areaId;
	
	public DistrictModel() {
		super();
	}

	public DistrictModel(String name, String areaId) {
		super();
		this.name = name;
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Override
	public String toString() {
		return "DistrictModel [name=" + name + ", areaId=" + areaId + "]";
	}

}
