package com.koreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class ArticleController extends Controller{

	private List<Article> articles;
	private int lastarticleid;
	public Scanner sc;
	public String cmd;

	public ArticleController(Scanner sc) {
		this.articles = Container.articleDao.articles;
		this.sc = sc;
		this.lastarticleid = 3;
	}
	
	public void run(String cmd, String methodname) {
		this.cmd = cmd;
	
		switch(methodname) {
		case "list":
			this.showlist();
			break;
		case "detail":
			this.showdetail();
			break;
		case "write":
			this.dowrite();
			break;
		case "modify":
			this.domodify();
			break;
		case "delete":
			this.dodelete();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}
	
	private void showlist() {
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
		System.out.println("번호	|제목	|날짜	    |작성자	|조회수");
		Collections.reverse(matched_articles);
		for (Article article : matched_articles) {
			
			String writerName = null;
			
			List<Member> members = Container.memberDao.members;
			
			for(Member member : members) {
				if(article.memberid == member.id) {
					writerName = member.name;
					break;
				}
			}
			
			System.out.printf("%d	|%s	|%s|%s	|%d\n", article.id, article.title, article.regDate.substring(5, 16), writerName,
					article.Hit);
		}
	}

	private void dowrite() {
		
		int id = lastarticleid + 1;
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		String regDate = Util.getDate();

		Article article = new Article(foundmember.id, id, regDate, title, body);
		articles.add(article);

		lastarticleid = id;
		System.out.printf("%d번 글이 생성되었습니다.\n", id);
	}

	private void domodify() {
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int searchID = Integer.parseInt(cmdBits[2]);

		Article foundarticle = matched_article(searchID);

		if (foundarticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchID);
			return;
		}
		
		if (foundarticle.memberid != foundmember.id) {
			System.out.println("게시물 수정 권한이 없습니다.");
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

	private void showdetail() {
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
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
		System.out.printf("작성자ID : %d\n", foundarticle.memberid);
	}

	private void dodelete() {
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int searchID = Integer.parseInt(cmdBits[2]);

		Article foundarticle = matched_article(searchID);

		if (foundarticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchID);
			return;
		}
		
		if (foundarticle.memberid != foundmember.id) {
			System.out.println("게시물 삭제 권한이 없습니다.");
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
	
	public void makeTestData() {

		articles.add(new Article(1, 1, Util.getDate(), "제목1", "내용1", 10));
		articles.add(new Article(1, 2, Util.getDate(), "제목2", "내용2", 20));
		articles.add(new Article(2, 3, Util.getDate(), "제목3", "내용3", 30));

		System.out.println("게시물 테스트 데이터를 생성합니다.");
	}

}
