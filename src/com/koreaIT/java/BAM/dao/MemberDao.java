package com.koreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.java.BAM.dto.Member;

public class MemberDao extends Dao{
	private List<Member> members;
	
	public MemberDao() {
		this.members = new ArrayList<>();
	}
	
	public void add(Member member) {
		members.add(member);
		lastId++;
	}

	public Member matched_member(String loginID) {
		for (Member member : members) {
			if (loginID.equals(member.loginID)) {
				return member;
			}
		}
		return null;
	}
	
	public boolean loginIdDupChk(String loginID) {
		Member member = matched_member(loginID);
		
		if (member == null) {
			return true;
		}
		return false;
	}

	public String getWriterName(int memberid) {
		for(Member member : members) {
			if(memberid == member.id) {
				return member.name;
			}
		}
		return null;
	}

	public List<Member> getMatchedMembers(String searchBits) {
		
		if(searchBits.length() > 0) {
			List<Member> matched_members = new ArrayList<>();
			
			for(Member member : members) {
				
				if (member.name.contains(searchBits)) {
					matched_members.add(member);
				}
				return matched_members;
			}
		}
		return members;
	}
}
