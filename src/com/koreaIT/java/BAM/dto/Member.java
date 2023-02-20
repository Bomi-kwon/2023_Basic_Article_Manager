package com.koreaIT.java.BAM.dto;

public class Member {
	int memberid;
	String regDate;
	String loginID;
	String loginPW;
	String name;

	public Member(int memberid, String regDate, String loginID, String loginPW, String name) {
		this.memberid = memberid;
		this.regDate = regDate;
		this.loginID = loginID;
		this.loginPW = loginPW;
		this.name = name;
	}
}
