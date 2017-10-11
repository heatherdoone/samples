package com.example.aboutme.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aboutme.articles.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class PageManager {
	final private Logger logger = LoggerFactory.getLogger( getClass() );

	ObjectMapper jsonMapper;

	@Autowired
	public PageManager( ObjectMapper jsonMapper ) {
		this.jsonMapper = jsonMapper;
		logger.info( "Creating new PageManager and jsonMapper" );

	}

	List<Page> pageList = new ArrayList<Page>();

	public void addPage ( ObjectNode pageDefiniton )
			throws Exception {

		Page page = jsonMapper.readValue( pageDefiniton.toString(), Page.class );
		pageList.add( page );
	}

	public void addPageWithArticle ( ObjectNode pageDefiniton, ObjectNode articleDefiniton )
			throws Exception {

		Page page = jsonMapper.readValue( pageDefiniton.toString(), Page.class );
		Article article = jsonMapper.readValue( articleDefiniton.toString(), Article.class );
		page.addArticle( article );
		pageList.add( page );
	}

	public void removePage ( ObjectNode pageDefiniton )
			throws Exception {

		Page page = jsonMapper.readValue( pageDefiniton.toString(), Page.class );
		pageList.remove( page );
	}

	public void updatePage ( ObjectNode pageDefiniton )
			throws Exception {

		removePage( pageDefiniton );
		addPage( pageDefiniton );
	}

	public Optional<Page> findPage ( String title ) {

		Optional<Page> firstPage = pageList
			.stream()
			.filter( page -> page
				.getTitle()
				.equalsIgnoreCase( title ) )
			.distinct()
			.findFirst();

		return firstPage;

	}

	public List<Page> findAllMatchingPages ( String title ) {

		List<Page> pages = pageList
			.stream()
			.filter( page -> page
				.getTitle()
				.equalsIgnoreCase( title ) )
			.collect( Collectors.toList() );

		return pages;

	}

	public void addArticle ( Page page, ObjectNode articleDefiniton )
			throws Exception {

		Article article = jsonMapper.readValue( articleDefiniton.toString(), Article.class );
		page.addArticle( article );

	}

	public void removeArticle ( Page page, ObjectNode articleDefiniton )
			throws Exception {

		// add new Article with title
		Article article = jsonMapper.readValue( articleDefiniton.toString(), Article.class );
		page.removeArticle( article );

	}

	@Override
	public String toString () {
		return "PageManager [pageList=" + pageList + "]";
	}
}
