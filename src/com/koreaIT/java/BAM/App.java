package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.util.Util;

public class App {
	private List<Article> articles;

	App() {
		articles = new ArrayList<>();
	}

	public void run() {
		System.out.println("== 프로그램 시작 ==");

		makeTestData();

		Scanner sc = new Scanner(System.in);
		int lastarticleid = 3;

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				break;
			}

			else if (cmd.startsWith("article list")) {
				
				if (articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				String searchBits = cmd.substring("article list".length()).trim();
				List<Article> matched_articles = articles;

				if (searchBits.length() > 0) {
					
					matched_articles = new ArrayList<>();
					
					for (Article article : articles) {
						if (article.title.contains(searchBits)) {
							matched_articles.add(article);
						}
					}
					if (matched_articles.size() == 0) {
						System.out.printf("%s가 포함된 게시물이 없습니다.\n",searchBits);
						continue;
					}
				}
				System.out.println("번호	|	제목	|	날짜	  |	조회수");
				Collections.reverse(matched_articles);
				for (Article article : matched_articles) {
					System.out.printf("%d	|	%s	|	%s|	%d\n", article.id, article.title,
							article.regDate.substring(5, 16), article.Hit);
				}

			}

			else if (cmd.equals("article write")) {
				int id = lastarticleid + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				String regDate = Util.getDate();

				Article article = new Article(id, regDate, title, body);
				articles.add(article);

				lastarticleid = id;
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
			}

			else if (cmd.startsWith("article modify ")) {
				String[] cmdBits = cmd.split(" ");
				int searchID = Integer.parseInt(cmdBits[2]);

				Article foundarticle = matched_article(searchID);

				if (foundarticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchID);
					continue;
				}

				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine();
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine();
				String regDate = Util.getDate();

				foundarticle.title = title;
				foundarticle.body = body;
				foundarticle.regDate = regDate;

				System.out.printf("%d번 글이 수정되었습니다.\n", searchID);
			}

			else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");
				int searchID = Integer.parseInt(cmdBits[2]);

				Article foundarticle = matched_article(searchID);

				if (foundarticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchID);
					continue;
				}

//				foundarticle.Hit++;
				foundarticle.increaseHit();

				System.out.printf("번호 : %d\n", foundarticle.id);
				System.out.printf("날짜 : %s\n", foundarticle.regDate.substring(0, 10));
				System.out.printf("제목 : %s\n", foundarticle.title);
				System.out.printf("내용 : %s\n", foundarticle.body);
				System.out.printf("조회수 : %d\n", foundarticle.Hit);
			} else if (cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");
				int searchID = Integer.parseInt(cmdBits[2]);

				Article foundarticle = matched_article(searchID);

				if (foundarticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchID);
					continue;
				}

				articles.remove(articles.indexOf(foundarticle));

				System.out.printf("%d번 게시물이 삭제되었습니다.\n", searchID);
			} else if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}

		System.out.println("== 프로그램 끝 ==");
		sc.close();
	}

	private Article matched_article(int searchID) {
		for (Article article : articles) {
			if (searchID == article.id) {
				return article;
			}
		}
		return null;
	}

	private void makeTestData() {

		articles.add(new Article(1, Util.getDate(), "제목1", "내용1", 10));
		articles.add(new Article(2, Util.getDate(), "제목2", "내용2", 20));
		articles.add(new Article(3, Util.getDate(), "제목3", "내용3", 30));

		System.out.println("게시물 테스트 데이터를 생성합니다.");
	}
}
