package com.koreaIT.java.BAM.service;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Member;

public class MemberService {

	public int getLastId() {
		return Container.memberDao.getLastId();
	}

	public void add(Member member) {
		Container.memberDao.add(member);
	}

	public Member getMemberbyId(String loginID) {
		return Container.memberDao.matched_member(loginID);
	}

	public boolean loginIdDupChk(String loginID) {
		return Container.memberDao.loginIdDupChk(loginID);
	}

	public String getWriterName(int memberid) {
		// TODO Auto-generated method stub
		return Container.memberDao.getWriterName(memberid);
	}

}
