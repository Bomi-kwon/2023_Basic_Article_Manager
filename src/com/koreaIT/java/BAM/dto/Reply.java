package com.koreaIT.java.BAM.dto;

public class Reply extends Dto {
	
	public int articleId;
	public String content;
	public int memberId;
	
	public Reply(int id, String regDate, int articleId, String content, int memberId){
		this.id = id;
		this.regDate = regDate;
		this.articleId = articleId;
		this.content = content;
		this.memberId = memberId;
	}
}
