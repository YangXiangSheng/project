package com.MaikeDun.bean;

public class Buy {
	private int id;
	private String b_id;
	private String b_name;
	private String number;
	private String price;
	private String date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getB_id() {
		return b_id;
	}
	public void setB_id(String b_id) {
		this.b_id = b_id;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Buy [id=" + id + ", b_id=" + b_id + ", b_name=" + b_name + ", number=" + number + ", price=" + price
				+ ", date=" + date + "]";
	}
	
	
}
