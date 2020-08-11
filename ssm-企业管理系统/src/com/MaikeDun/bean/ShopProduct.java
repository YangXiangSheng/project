package com.MaikeDun.bean;

public class ShopProduct {
	private String s_id;
	private String s_name;
	private String number;//生产数量
	private String date;//生产日期
	private String id;
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ShopProduct [s_id=" + s_id + ", s_name=" + s_name + ", number=" + number + ", date=" + date + ", id="
				+ id + "]";
	}

}
