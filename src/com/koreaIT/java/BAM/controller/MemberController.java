package com.koreaIT.java.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class MemberController {
	
	List<Member> members;
	Scanner sc;
	int lastmemberid;
	
	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
		lastmemberid = 0;
	}

	public void dojoin() {
		String loginID = null;
		String loginPW = null;
		String name = null;
		int id = lastmemberid + 1;
		lastmemberid = id;
		
		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginID = sc.nextLine().trim();
			if (loginID.length() == 0) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}
			boolean isloginableID = true;
			for (Member member : members) {
				if (loginID.equals(member.loginID)) {
					System.out.println("해당 아이디는 이미 존재합니다.");
					isloginableID = false;
				}
			}
			if (isloginableID == false) {
				continue;
			}
			break;
		}
		
		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPW = sc.nextLine().trim();
			if (loginPW.length() == 0) {
				System.out.println("비밀번호를 입력해주세요.");
				continue;
			}
			System.out.printf("로그인 비밀번호 확인 : ");
			String loginPW_check = sc.nextLine();
			if (loginPW_check.equals(loginPW) == false) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			break;
		}
		
		while(true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();
			if (name.length() == 0) {
				System.out.println("이름을 입력해주세요.");
				continue;
			}
			break;
		}
		String regDate = Util.getDate();

		id++;
		Member member = new Member(id, regDate, loginID, loginPW, name);
		members.add(member);
		System.out.printf("환영합니다. %s 회원의 가입이 완료되었습니다.\n", name);
		
	}

}
