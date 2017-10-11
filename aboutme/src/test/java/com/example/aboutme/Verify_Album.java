package com.example.aboutme;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.aboutme.albums.Album;
import com.example.aboutme.albums.AlbumManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Verify_Album {
	final private Logger logger = LoggerFactory.getLogger( getClass() );

	ObjectMapper jsonMapper = new ObjectMapper();

	AlbumManager albumManager = new AlbumManager( jsonMapper );

	@Test
	public void verify_album_list ()
			throws Exception {
		logger.info( "Testing albums" );

		ObjectNode albumObject = jsonMapper.createObjectNode();

		albumObject.put( "title", "taylor" );
		albumObject.put( "description", "Age 5" );

		Album tayAlbum = jsonMapper.readValue(
			albumObject.toString(),
			Album.class );

		logger.info( "albumObject: {}\n Album: {}",
			albumObject,
			tayAlbum );

		assertThat(
			tayAlbum.getTitle() )
				.as( "Album Title" )
				.isEqualTo( "taylor" );

		albumManager.addAlbum( albumObject );

		assertThat(
			albumManager.findFirstMatch( "fred" ).isPresent() )
				.isEqualTo( false );

		assertThat(
			albumManager.findFirstMatch( "taylor" ).isPresent() )
				.isEqualTo( true );
		
	}

}