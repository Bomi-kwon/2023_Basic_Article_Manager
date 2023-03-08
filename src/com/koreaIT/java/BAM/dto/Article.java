package com.koreaIT.java.BAM.dto;

public class Article  extends Dto{
	public String title;
	public String body;
	public int Hit;
	public int memberId;
	public Reply reply;
	
	public Article(int memberID, int id, String regDate, String title, String body) {
		this(memberID, id, regDate, title, body, 0, null);
	}
	
	public Article(int memberID, int id, String regDate, String title, String body, int Hit, Reply reply) {
		this.memberId = memberID;
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.Hit = Hit;
		this.reply = reply;
	}

	public void increaseHit() {
		this.Hit++;
	}
}