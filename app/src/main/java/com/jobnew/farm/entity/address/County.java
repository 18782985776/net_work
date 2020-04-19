package com.jobnew.farm.entity.address;

/**
 * @author 曾雪强 E-mail:
 * @version 创建时间：2017年7月3日 下午12:09:05 请写详细说明
 */
public class County {
	private Integer id;
	private String name;
	private Integer zipCode;
	private String mergerName;

	public County(String name, String areaId) {
		super();
		this.name = name;
		this.id = Integer.valueOf(areaId);
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

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getMergerName() {
		return mergerName;
	}

	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
	}
	@Override
	public String toString() {
		return "DistrictModel [name=" + name + ", areaId=" + id + "]";
	}
}
