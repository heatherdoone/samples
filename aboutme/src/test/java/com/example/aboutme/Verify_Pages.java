package com.example.aboutme;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.aboutme.articles.Article;
import com.example.aboutme.pages.Page;
import com.example.aboutme.pages.PageManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Verify_Pages {
	final private Logger logger = LoggerFactory.getLogger( getClass() );

	ObjectMapper jsonMapper = new ObjectMapper();

	PageManager pageManager = new PageManager( jsonMapper );

	@Test
	public void testAddFindPage ()
			throws Exception {
		logger.info( "Testing Create new Page" );

		ObjectNode pageObject = jsonMapper.createObjectNode();

		pageObject.put( "title", "WAGS Tournament" );

		Page wagsPage = jsonMapper.readValue(
			pageObject.toString(),
			Page.class );

		logger.info( "pageObject: {}\n Page: {}",
			pageObject,
			wagsPage );

		assertThat(
			wagsPage.getTitle() )
				.as( "Page Name" )
				.isEqualTo( "WAGS Tournament" );

		pageManager.addPage( pageObject );

		assertThat(
			pageManager.findPage( "fred" ).isPresent() )
				.isEqualTo( false );

		assertThat(
			pageManager.findPage( "WAGS Tournament" ).isPresent() )
				.isEqualTo( true );

		assertThat(
			pageManager.findAllMatchingPages( "WAGS Tournament" ) )
				.allMatch( page -> page.getTitle().equals( "WAGS Tournament" ) );

	}

	@Test
	public void testDuplicatePage ()
			throws Exception {
		logger.info( "Testing Duplicate Pages" );

		ObjectNode pageObject = jsonMapper.createObjectNode();

		pageObject.put( "title", "WAGS Tournament" );

		Page wagsPage = jsonMapper.readValue(
			pageObject.toString(),
			Page.class );

		logger.info( "pageObject: {}\n Page: {}",
			pageObject,
			wagsPage );

		assertThat(
			wagsPage.getTitle() )
				.as( "Page Name" )
				.isEqualTo( "WAGS Tournament" );

		pageManager.addPage( pageObject );

		ObjectNode duplicatePageObject = jsonMapper.createObjectNode();

		duplicatePageObject.put( "title", "WAGS Tournament" );

		Page duplicateNamePage = jsonMapper.readValue(
			duplicatePageObject.toString(),
			Page.class );

		logger.info( "pageObject: {}\n Page: {}",
			duplicatePageObject,
			duplicateNamePage );

		pageManager.addPage( duplicatePageObject );

		logger.info( "pageList contents:  {} ", pageManager.toString() );

		assertThat(
			pageManager.findPage( "fred" ).isPresent() )
				.isEqualTo( false );

		assertThat(
			pageManager.findPage( "WAGS Tournament" ).isPresent() )
				.isEqualTo( true );

		assertThat(
			pageManager.findAllMatchingPages( "WAGS Tournament" ) )
				.allMatch( page -> page.getTitle().equals( "WAGS Tournament" ) );

		assertThat(
			pageManager.findAllMatchingPages( "WAGS Tournament" ) )
				.hasSize( 2 );

		pageManager.removePage( duplicatePageObject );

		assertThat(
			pageManager.findPage( "WAGS Tournament" ).isPresent() )
				.isEqualTo( true );

		assertThat(
			pageManager.findAllMatchingPages( "WAGS Tournament" ) )
				.hasSize( 1 );

	}

	@Test
	public void testPageArticles ()
			throws Exception {
		logger.info( "Testing Adding Removing and Updating Articles" );

		ObjectNode pageObject = jsonMapper.createObjectNode();

		pageObject.put( "title", "WAGS Tournament" );

		Page wagsPage = jsonMapper.readValue(
			pageObject.toString(),
			Page.class );

		logger.info( "pageObject: {}\n Page: {}",
			pageObject,
			wagsPage );

		assertThat(
			wagsPage.getTitle() )
				.as( "Page Name" )
				.isEqualTo( "WAGS Tournament" );

		pageManager.addPage( pageObject );

		ObjectNode articleObject = jsonMapper.createObjectNode();

		articleObject.put( "identifier", "WAGS Tournament" );
		articleObject.put( "contents", "Scoreboard: Wins Losses" );
		ArrayNode imageArray = articleObject.putArray( "images" );
		imageArray.add( "image1" );

		Article wagArticle = jsonMapper.readValue(
			articleObject.toString(),
			Article.class );

		logger.info( "articleObject: {}\n Page: {}",
			articleObject,
			wagArticle );
		
		wagsPage.addArticle( wagArticle );
		
		logger.info( "pageList contents:  {} ", pageManager.toString() );
		logger.info( "wagsPage contents:  {} ", wagsPage.toString() );
		logger.info( "wagArticle contents:  {} ", wagArticle.toString() );

		assertThat(
			pageManager.findPage( "fred" ).isPresent() )
				.isEqualTo( false );

		assertThat(
			pageManager.findPage( "WAGS Tournament" ).isPresent() )
				.isEqualTo( true );

		assertThat(
			pageManager.findAllMatchingPages( "WAGS Tournament" ) )
				.allMatch( page -> page.getTitle().equals( "WAGS Tournament" ) );

		assertThat(
			pageManager.findAllMatchingPages( "WAGS Tournament" ) )
				.hasSize( 1 );



	}

}
