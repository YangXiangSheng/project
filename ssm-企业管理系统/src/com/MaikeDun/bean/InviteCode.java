package com.MaikeDun.bean;

public class InviteCode {
	private String code;
	private String profession;
	private String flag;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "InviteCode [code=" + code + ", profession=" + profession + ", flag=" + flag + "]";
	}
	

}
