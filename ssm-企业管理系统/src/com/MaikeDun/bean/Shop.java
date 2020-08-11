package com.MaikeDun.bean;

public class Shop {
	private String s_id;
	private String s_name;
	private String number;
	private String price;
	private String photo;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Shop [s_id=" + s_id + ", s_name=" + s_name + ", number=" + number + ", price=" + price + ", photo="
				+ photo + "]";
	}

	
	

}
