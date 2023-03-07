package com.koreaIT.java.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.service.MemberService;
import com.koreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	public Scanner sc;
	public String cmd;
	private MemberService memberService;

	public MemberController(Scanner sc) {
		this.sc = sc;
		this.memberService = Container.memberService;
	}

	public void run(String cmd, String methodname) {
		this.cmd = cmd;
		switch (methodname) {
		case "join":
			this.dojoin();
			break;
		case "login":
			this.dologin();
			break;
		case "logout":
			this.dologout();
			break;
		case "profile":
			this.showprofile();
			break;
		case "list":
			this.showlist();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	

	private void dojoin() {

		String loginID = null;
		String loginPW = null;
		String name = null;
		int id = memberService.getLastId();

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginID = sc.nextLine().trim();
			if (loginID.length() == 0) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}

			if (memberService.loginIdDupChk(loginID) == false) {
				System.out.printf("%s 는 이미 사용중인 아이디 입니다.\n", loginID);
				continue;
			}

			System.out.printf("%s 는 사용 가능한 아이디입니다.\n", loginID);
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

		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();
			if (name.length() == 0) {
				System.out.println("이름을 입력해주세요.");
				continue;
			}
			break;
		}
		String regDate = Util.getDate();

		Member member = new Member(id, regDate, loginID, loginPW, name);
		memberService.add(member);
		System.out.printf("환영합니다. %s 회원의 가입이 완료되었습니다.\n", name);
	}

	private void dologin() {

		while (true) {
			System.out.printf("로그인 아이디 : ");
			String loginID = sc.nextLine().trim();
			if (loginID.length() == 0) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}

			foundmember = memberService.getMemberbyId(loginID);

			if (!islogined()) {
				System.out.println("존재하지 않는 아이디입니다.");
				continue;
			}
			break;
		}

		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			String loginPW = sc.nextLine().trim();
			if (loginPW.length() == 0) {
				System.out.println("비밀번호를 입력해주세요.");
				continue;
			}
			if (loginPW.equals(foundmember.loginPW) == false) {
				System.out.println("비밀번호를 확인해주세요.");
				continue;
			}
			System.out.printf("%s님 환영합니다. 로그인 성공!!\n", foundmember.name);
			break;
		}
	}

	private void dologout() {
		System.out.printf("%s님 로그아웃되었습니다.\n", foundmember.name);
		foundmember = null;
	}

	private void showprofile() {

		System.out.println("== 내 정보 ==");
		System.out.printf("로그인 아이디 : %s \n", foundmember.loginID);
		System.out.printf("이름 : %s \n", foundmember.name);

	}
	
	private void showlist() {
		if (!foundmember.loginID.equals("admin")) {
			System.out.println("회원 조회 권한은 관리자에게만 있습니다.");
			return;
		}
		
		String searchBits = cmd.substring("member list".length()).trim();
		List<Member> matched_members = memberService.getMatchedMembers(searchBits);
		
		if (matched_members.size() == 0) {
			System.out.println("회원 정보가 없습니다.");
			return;
		}
		System.out.println("== 회원 정보 ==");
		System.out.println("번호	|	아이디	|	이름	|	가입날짜");
		for (int i = matched_members.size() - 1 ; i >= 0 ; i--) {
			
			Member member = matched_members.get(i);
			
			System.out.printf("%d	|	%s	|	%s	|	%s\n",member.id, member.loginID, member.loginPW, member.regDate.substring(5, 16));
		}
		
		
		
	}
	
	public void makeTestData() {

		memberService
				.add(new Member(memberService.getLastId(), Util.getDate(), "admin", "123", "짱구"));
		memberService
				.add(new Member(memberService.getLastId(), Util.getDate(), "test1", "456", "철수"));
		memberService
				.add(new Member(memberService.getLastId(), Util.getDate(), "test2", "789", "훈이"));

		System.out.println("회원 테스트 데이터를 생성합니다.");
	}

}
