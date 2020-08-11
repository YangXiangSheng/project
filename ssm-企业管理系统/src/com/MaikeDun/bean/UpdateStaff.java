package com.MaikeDun.bean;

public class UpdateStaff {
	private Integer staffOldId;
	private Integer staffNewId;
	private String staffName;
	private String sex;
	private String phone;
	private String email;
	private String idcard;
	private String department;
	private String position;
	public Integer getStaffOldId() {
		return staffOldId;
	}
	public void setStaffOldId(Integer staffOldId) {
		this.staffOldId = staffOldId;
	}
	public Integer getStaffNewId() {
		return staffNewId;
	}
	public void setStaffNewId(Integer staffNewId) {
		this.staffNewId = staffNewId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public UpdateStaff( Integer staffNewId, String staffName, String sex, String phone, String email,
			String idcard, String department, String position,Integer staffOldId) {
		super();
		this.staffOldId = staffOldId;
		this.staffNewId = staffNewId;
		this.staffName = staffName;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.idcard = idcard;
		this.department = department;
		this.position = position;
	}
	public UpdateStaff() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
