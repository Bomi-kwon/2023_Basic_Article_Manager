package com.koreaIT.java.BAM.dto;

public class Member extends Dto{
	public String loginID;
	public String loginPW;
	public String name;

	public Member(int id, String regDate, String loginID, String loginPW, String name) {
		this.id = id;
		this.regDate = regDate;
		this.loginID = loginID;
		this.loginPW = loginPW;
		this.name = name;
	}
}
