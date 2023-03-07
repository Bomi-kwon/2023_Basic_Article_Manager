package com.koreaIT.java.BAM.service;

import java.util.List;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dao.ArticleDao;
import com.koreaIT.java.BAM.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}

	public List<Article> getMatchedArticles(String searchBits) {
		return articleDao.getMatchedArticles(searchBits);
	}

	public int getLastId() {
		return articleDao.getLastId();
	}

	public void add(Article article) {
		articleDao.add(article);
	}

	public Article getArticleById(int searchID) {
		
		return articleDao.matched_article(searchID);
	}

	public void remove(Article foundarticle) {
		articleDao.remove(foundarticle);
	}

	public void articleModify(Article foundarticle, String title, String body, String regDate) {
		articleDao.articleModify(foundarticle, title, body, regDate);
	}

	public List<Article> getArticleByMemberId(int id) {
		return articleDao.getArticleByMemberId(id);
	}

}
