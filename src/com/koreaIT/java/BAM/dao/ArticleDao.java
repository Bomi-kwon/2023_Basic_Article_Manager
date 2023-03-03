package com.koreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.java.BAM.dto.Article;

public class ArticleDao extends Dao{
	public List<Article> articles;
	
	public ArticleDao() {
		this.articles = new ArrayList<>();
	}

	public void add(Article article) {
		articles.add(article);
		lastId++;
	}

	public List<Article> getMatchedArticles(String searchBits) {
		
		if (searchBits != null) {

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
}
