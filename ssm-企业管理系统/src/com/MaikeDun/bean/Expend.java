package com.MaikeDun.bean;

import java.sql.Date;

public class Expend {
	private Date date;
	private double expenditure;
	private String information;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(double expenditure) {
		this.expenditure = expenditure;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public Expend(Date date, double expenditure, String information) {
		super();
		this.date = date;
		this.expenditure = expenditure;
		this.information = information;
	}
	public Expend() {
		super();
		// TODO Auto-generated constructor stub
	}

}
