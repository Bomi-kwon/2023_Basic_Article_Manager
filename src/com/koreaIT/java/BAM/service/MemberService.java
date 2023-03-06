package com.koreaIT.java.BAM.service;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dao.MemberDao;
import com.koreaIT.java.BAM.dto.Member;

public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = Container.memberDao;
	}

	public int getLastId() {
		return memberDao.getLastId();
	}

	public void add(Member member) {
		memberDao.add(member);
	}

	public Member getMemberbyId(String loginID) {
		return memberDao.matched_member(loginID);
	}

	public boolean loginIdDupChk(String loginID) {
		return memberDao.loginIdDupChk(loginID);
	}

	public String getWriterName(int memberid) {
		return memberDao.getWriterName(memberid);
	}

}
