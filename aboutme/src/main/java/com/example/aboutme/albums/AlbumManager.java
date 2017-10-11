package com.example.aboutme.albums;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class AlbumManager {

	ObjectMapper jsonMapper;

	@Autowired
	public AlbumManager( ObjectMapper jsonMapper ) {
		this.jsonMapper = jsonMapper;

	}

	List<Album> albumList = new ArrayList<Album>();

	public void addAlbum ( ObjectNode albumDefiniton )
			throws Exception {

		Album album = jsonMapper
			.readValue( albumDefiniton.toString(),
				Album.class );

		albumList.add( album );

	}

	public void updateAlbum ( ObjectNode albumDefiniton, ObjectNode modifiedAlbumDefinition )
			throws Exception {

		Album album = jsonMapper.readValue(
			albumDefiniton.toString(),
			Album.class );

		albumList.remove( album );
		Album modAlbum = jsonMapper.readValue(
			modifiedAlbumDefinition.toString(),
			Album.class );

		albumList.add( modAlbum );

	}

	public void removeAlbum ( ObjectNode albumDefiniton )
			throws Exception {

		Album album = jsonMapper.readValue( 
			albumDefiniton.toString(), 
			Album.class );
		
		
		albumList.remove( album );

	}

	public Optional<Album> findFirstMatch ( String title ) {

		Optional<Album> firstAlbum = albumList
			.stream()
			.filter( album -> album
				.getTitle()
				.equalsIgnoreCase( title ) )
			.distinct()
			.findFirst();

		return firstAlbum;

	}

	public List<Album> getAlbumList () {

		return albumList;

	}

	public List<Album> findAllAlbums ( String title ) {

		List<Album> albums = albumList
			.stream()
			.filter( album -> album
				.getTitle()
				.equalsIgnoreCase( title ) )
			.collect( Collectors.toList() );

		return albums;

	}
}
