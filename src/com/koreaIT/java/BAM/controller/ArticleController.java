package com.koreaIT.java.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.util.Util;

public class ArticleController extends Controller{

	public Scanner sc;
	public String cmd;

	public ArticleController(Scanner sc) {
		this.sc = sc;
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

		String searchBits = cmd.substring("article list".length()).trim();
		List<Article> matched_articles = Container.articleService.getMatchedArticles(searchBits);

		if (matched_articles.size() == 0) {
			System.out.println("게시글이 없습니다.");
			return;
		}
		
		System.out.println("번호	|제목	|날짜	    |작성자	|조회수");
		for (int i = matched_articles.size()-1 ; i >= 0 ; i--) {
			
			Article article = matched_articles.get(i);
			
			String writerName = Container.memberService.getWriterName(article.memberid);
			
			System.out.printf("%d	|%s	|%s|%s	|%d\n", article.id, article.title, article.regDate.substring(5, 16), writerName,
					article.Hit);
		}
	}

	private void dowrite() {
		
		int id = Container.articleService.getLastId();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		String regDate = Util.getDate();

		Article article = new Article(foundmember.id, id, regDate, title, body);
		
		Container.articleService.add(article);

		System.out.printf("%d번 글이 생성되었습니다.\n", id);
	}

	private void domodify() {
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int searchID = Integer.parseInt(cmdBits[2]);

		Article foundarticle = Container.articleService.getArticleById(searchID);

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

		Container.articleService.articleModify(foundarticle, title, body, regDate);

		System.out.printf("%d번 글이 수정되었습니다.\n", searchID);
	}

	private void showdetail() {
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int searchID = Integer.parseInt(cmdBits[2]);

		Article foundarticle = Container.articleService.getArticleById(searchID);

		if (foundarticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchID);
			return;
		}

		foundarticle.increaseHit();
		
		String writerName = Container.memberService.getWriterName(foundarticle.memberid);
		
		System.out.printf("번호 : %d\n", foundarticle.id);
		System.out.printf("날짜 : %s\n", foundarticle.regDate.substring(0, 10));
		System.out.printf("제목 : %s\n", foundarticle.title);
		System.out.printf("내용 : %s\n", foundarticle.body);
		System.out.printf("조회수 : %d\n", foundarticle.Hit);
		System.out.printf("작성자 : %s\n", writerName);
	}

	private void dodelete() {
		String[] cmdBits = cmd.split(" ");
		
		if(cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int searchID = Integer.parseInt(cmdBits[2]);

		Article foundarticle = Container.articleService.getArticleById(searchID);

		if (foundarticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", searchID);
			return;
		}
		
		if (foundarticle.memberid != foundmember.id) {
			System.out.println("게시물 삭제 권한이 없습니다.");
			return;
		}
		Container.articleService.remove(foundarticle);

		System.out.printf("%d번 게시물이 삭제되었습니다.\n", searchID);
	}

	
	
	public void makeTestData() {

		Container.articleService.add(new Article(1, Container.articleService.getLastId(), Util.getDate(), "제목1", "내용1", 10));
		Container.articleService.add(new Article(1, Container.articleService.getLastId(), Util.getDate(), "제목2", "내용2", 20));
		Container.articleService.add(new Article(2, Container.articleService.getLastId(), Util.getDate(), "제목3", "내용3", 30));

		System.out.println("게시물 테스트 데이터를 생성합니다.");
	}

}
