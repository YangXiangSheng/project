package com.MaikeDun.bean;

public class Client {
	private String c_id;
	private String c_name;
	private String phone;
	private String address;
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Client [c_id=" + c_id + ", c_name=" + c_name + ", phone=" + phone + ", address=" + address
				+ "]";
	}
}
