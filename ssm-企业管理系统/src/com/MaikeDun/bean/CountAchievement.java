package com.MaikeDun.bean;

import java.sql.Date;

public class CountAchievement {
	private Date date;
	private int month;
	private int year;
	private double turnover;
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
	public double getTurnover() {
		return turnover;
	}
	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}
	public CountAchievement(Date date, int month, int year, double turnover) {
		super();
		this.date = date;
		this.month = month;
		this.year = year;
		this.turnover = turnover;
	}
	public CountAchievement() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
