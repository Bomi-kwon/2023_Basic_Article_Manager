package com.koreaIT.java.BAM.service;

import java.util.List;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Article;

public class ArticleService {

	public List<Article> getMatchedArticles(String searchBits) {
		return Container.articleDao.getMatchedArticles(searchBits);
	}

}
