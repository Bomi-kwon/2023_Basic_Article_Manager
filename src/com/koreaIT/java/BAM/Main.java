package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		int lastarticleid = 0;
		
		List<Article> articles = new ArrayList<>();
		
		while(true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();
			
			if(cmd.equals("exit")) {
				break;
			}
			else if(cmd.equals("article list")) {
				
				if (articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				System.out.println("번호	|	제목");
				for(int i = articles.size()-1 ; i >= 0 ; i--) {
					Article article = articles.get(i);
					System.out.printf("%d	|	%s\n",article.id,article.title);
				}
			}
			else if(cmd.equals("article write")) {
				int id = lastarticleid + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				Article article = new Article(id, "2023-02-15 14:14:30",title, body);
				articles.add(article);
				
				lastarticleid = id;
				System.out.printf("%d번 글이 생성되었습니다.\n",id);
			}
			else if(cmd.startsWith("article detail")) {
				String[] cmdBits = cmd.split(" ");
				int searchID = Integer.parseInt(cmdBits[2]);
				Article foundarticle = null;
				for (int i = 0 ; i < articles.size() ; i++) {
					Article article = articles.get(i);
					if (searchID == article.id) {
						foundarticle = article;
						break;
					}
				}
				if (foundarticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n",searchID);
					continue;
				}
				System.out.printf("번호 : %d\n",foundarticle.id);
				System.out.printf("날짜 : %s\n",foundarticle.regDate);
				System.out.printf("제목 : %s\n",foundarticle.title);
				System.out.printf("내용 : %s\n",foundarticle.body);
			}
			else if(cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
			}
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}
		
		System.out.println("== 프로그램 끝 ==");
		sc.close();
	}
}

class Article {
	int id;
	String regDate;
	String title;
	String body;
	
	public Article(int id, String regDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
	}
}