package com.example.aboutme.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.aboutme.articles.Article;

public class Page {

	public String title;
	List<Article> articleList = new ArrayList<Article>();

	public String getTitle () {
		return title;
	}

	public List<Article> getArticleList () {
		return articleList;
	}

	public void addArticle ( Article article )
			throws Exception {

		articleList.add( article );

	}

	public void removeArticle ( Article article )
			throws Exception {

		articleList.remove( article );

	}

	public void updateArticle ( Article article )
			throws Exception {

		Article newArticle = article;
		
		removeArticle( article );
		addArticle( newArticle );

	}

	public Optional<Article> findArticle ( String identifier ) {

		Optional<Article> firstArticle = articleList
			.stream()
			.filter( article -> article
				.getIdentifier()
				.equalsIgnoreCase( identifier ) )
			.distinct()
			.findFirst();

		return firstArticle;

	}

	public List<Article> findAllArticles () {

		List<Article> articles = articleList
			.stream()
			.collect( Collectors.toList() );

		return articles;

	}

	@Override
	public String toString () {
		return "Page [title=" + title + ", articleList=" + articleList + "]";
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articleList == null) ? 0 : articleList.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals ( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Page other = (Page) obj;
		if ( articleList == null ) {
			if ( other.articleList != null )
				return false;
		} else if ( !articleList.equals( other.articleList ) )
			return false;
		if ( title == null ) {
			if ( other.title != null )
				return false;
		} else if ( !title.equals( other.title ) )
			return false;
		return true;
	}

}
