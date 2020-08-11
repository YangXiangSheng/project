package com.MaikeDun.bean;

import java.sql.Date;
public class Achievement {
	private Date date;
	private double turnover;
	private String information;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getTurnover() {
		return turnover;
	}
	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public Achievement(Date date, double turnover, String information) {
		super();
		this.date = date;
		this.turnover = turnover;
		this.information = information;
	}
	public Achievement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
