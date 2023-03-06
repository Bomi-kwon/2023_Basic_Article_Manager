package com.koreaIT.java.BAM.service;

import java.util.List;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Article;

public class ArticleService {

	public List<Article> getMatchedArticles(String searchBits) {
		return Container.articleDao.getMatchedArticles(searchBits);
	}

	public int getLastId() {
		return Container.articleDao.getLastId();
	}

	public void add(Article article) {
		Container.articleDao.add(article);
	}

	public Article getArticleById(int searchID) {
		
		return Container.articleDao.matched_article(searchID);
	}

	public void remove(Article foundarticle) {
		Container.articleDao.remove(foundarticle);
	}

	public void articleModify(Article foundarticle, String title, String body, String regDate) {
		Container.articleDao.articleModify(foundarticle, title, body, regDate);
	}

}
