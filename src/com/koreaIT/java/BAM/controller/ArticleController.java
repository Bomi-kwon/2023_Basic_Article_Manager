package com.koreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.util.Util;

public class ArticleController {

	List<Article> articles;
	Scanner sc;
	int lastarticleid;

	public ArticleController(List<Article> articles, Scanner sc) {
		this.articles = articles;
		this.sc = sc;
		lastarticleid = 3;
	}
	
	public void run(String cmd) {
		String[] cmdBits = cmd.split(" ");
		if (cmdBits[1].equals("list")) {
			this.showlist(cmd);
		}
		if (cmdBits[1].equals("write")) {
			this.dowrite();
		}
		if (cmdBits[1].equals("modify")) {
			this.domodify(cmd);
		}
		if (cmdBits[1].equals("detail")) {
			this.showdetail(cmd);
		}
		if (cmdBits[1].equals("delete")) {
			this.dodelete(cmd);
		}
	}
	
	public void showlist(String cmd) {
		if (articles.size() == 0) {
			System.out.println("게시물이 존재하지 않습니다.");
			return;
		}

		String searchBits = cmd.substring("article list".length()).trim();
		List<Article> matched_articles = new ArrayList<>(articles);

		if (searchBits.length() > 0) {

			matched_articles.clear();

			for (Article article : articles) {
				if (article.title.contains(searchBits)) {
					matched_articles.add(article);
				}
			}
			if (matched_articles.size() == 0) {
				System.out.printf("%s가 포함된 게시물이 없습니다.\n", searchBits);
				return;
			}
		}
		System.out.println("번호	|	제목	|	날짜	  |	조회수");
		Collections.reverse(matched_articles);
		for (Article article : matched_articles) {
			System.out.printf("%d	|	%s	|	%s|	%d\n", article.id, article.title, article.regDate.substring(5, 16),
					article.Hit);
		}
	}

	public void dowrite() {
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

	public void domodify(String cmd) {
		String[] cmdBits = cmd.split(" ");
		int searchID = Integer.parseInt(cmdBits[2]);

		Article foundarticle = matched_article(searchID);

		if (foundarticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchID);
			return;
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

	public void showdetail(String cmd) {
		String[] cmdBits = cmd.split(" ");
		int searchID = Integer.parseInt(cmdBits[2]);

		Article foundarticle = matched_article(searchID);

		if (foundarticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchID);
			return;
		}

		foundarticle.increaseHit();

		System.out.printf("번호 : %d\n", foundarticle.id);
		System.out.printf("날짜 : %s\n", foundarticle.regDate.substring(0, 10));
		System.out.printf("제목 : %s\n", foundarticle.title);
		System.out.printf("내용 : %s\n", foundarticle.body);
		System.out.printf("조회수 : %d\n", foundarticle.Hit);
	}

	public void dodelete(String cmd) {
		String[] cmdBits = cmd.split(" ");
		int searchID = Integer.parseInt(cmdBits[2]);

		Article foundarticle = matched_article(searchID);

		if (foundarticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchID);
			return;
		}

		articles.remove(articles.indexOf(foundarticle));

		System.out.printf("%d번 게시물이 삭제되었습니다.\n", searchID);
	}

	private Article matched_article(int searchID) {
		for (Article article : articles) {
			if (searchID == article.id) {
				return article;
			}
		}
		return null;
	}



	

}
