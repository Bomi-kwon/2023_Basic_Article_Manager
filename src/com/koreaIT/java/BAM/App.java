package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.MemberController;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class App {
	private List<Article> articles;
	private List<Member> members;

	App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void run() {
		System.out.println("== 프로그램 시작 ==");

		makeTestData();

		Scanner sc = new Scanner(System.in);
		
		MemberController membercontroller = new MemberController(members, sc);
		ArticleController articlecontroller = new ArticleController(articles, sc);
		
		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				break;
			} else if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
			} else if (cmd.length() > 0) {
				String[] cmdBits = cmd.split(" ");
				if (cmdBits[0].equals("article")) {
					articlecontroller.run(cmd);
				}
				if (cmdBits[0].equals("member")) {
					membercontroller.run(cmd);
				}
			}
			
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}

			
		}

		System.out.println("== 프로그램 끝 ==");
		sc.close();
	}

	

	private void makeTestData() {

		articles.add(new Article(1, Util.getDate(), "제목1", "내용1", 10));
		articles.add(new Article(2, Util.getDate(), "제목2", "내용2", 20));
		articles.add(new Article(3, Util.getDate(), "제목3", "내용3", 30));

		System.out.println("게시물 테스트 데이터를 생성합니다.");
	}
}
