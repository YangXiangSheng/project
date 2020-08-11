package com.MaikeDun.bean;

public class Order {
	private int o_id;
	private String s_id;
	private String o_number;
	private String o_time;
	private String photo;
	private String publish_time;
	private String c_id;//¿Í»§id
	private int flag;
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getO_number() {
		return o_number;
	}
	public void setO_number(String o_number) {
		this.o_number = o_number;
	}
	public String getO_time() {
		return o_time;
	}
	public void setO_time(String o_time) {
		this.o_time = o_time;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Order [o_id=" + o_id + ", s_id=" + s_id + ", o_number=" + o_number + ", o_time=" + o_time + ", photo="
				+ photo + ", publish_time=" + publish_time + ", c_id=" + c_id + ", flag=" + flag + "]";
	}
}
