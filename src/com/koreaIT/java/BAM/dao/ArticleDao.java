package com.koreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.java.BAM.dto.Article;

public class ArticleDao extends Dao{
	private List<Article> articles;
	
	public ArticleDao() {
		this.articles = new ArrayList<>();
	}

	public void add(Article article) {
		articles.add(article);
		lastId++;
	}

	public List<Article> getMatchedArticles(String searchBits) {
		
		if (searchBits.length() > 0) {

			List<Article> matched_articles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchBits)) {
					matched_articles.add(article);
				}
			}
			return matched_articles;
		}
		return articles;
	}
	
	public Article matched_article(int searchID) {
		for (Article article : articles) {
			if (searchID == article.id) {
				return article;
			}
		}
		return null;
	}

	public void remove(Article foundarticle) {
		articles.remove(foundarticle);
	}

	public void articleModify(Article foundarticle, String title, String body, String regDate) {
		foundarticle.title = title;
		foundarticle.body = body;
		foundarticle.regDate = regDate;
		
	}

	public List<Article> getArticleByMemberId(int id) {
		
		List<Article> matched_articles = new ArrayList<>();
		
		for (Article article : articles) {
			if (id == article.memberId) {
				matched_articles.add(article);
			}
		}
		return matched_articles;
	}
}
