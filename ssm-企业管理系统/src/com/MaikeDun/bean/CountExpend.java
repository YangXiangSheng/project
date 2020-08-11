package com.MaikeDun.bean;

import java.sql.Date;

public class CountExpend {
	private Date date;
	private int month;
	private int year;
	private double expenditure;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(double expenditure) {
		this.expenditure = expenditure;
	}
	public CountExpend(Date date, int month, int year, double expenditure) {
		super();
		this.date = date;
		this.month = month;
		this.year = year;
		this.expenditure = expenditure;
	}
	public CountExpend() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
